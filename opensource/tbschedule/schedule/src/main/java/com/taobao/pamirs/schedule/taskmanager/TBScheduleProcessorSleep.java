package com.taobao.pamirs.schedule.taskmanager;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.pamirs.schedule.IScheduleTaskDeal;
import com.taobao.pamirs.schedule.IScheduleTaskDealMulti;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;

/**
 * 任务调度器，在TBScheduleManager的管理下实现多线程数据处理
 * @author xuannan
 *
 * @param <T>
 */
class TBScheduleProcessorSleep<T> implements IScheduleProcessor,Runnable {
	
	private static transient Logger logger = LoggerFactory.getLogger(TBScheduleProcessorSleep.class);
	final  LockObject   m_lockObject = new LockObject();
	List<Thread> threadList =  new CopyOnWriteArrayList<Thread>();
	/**
	 * 任务管理器
	 */
	protected TBScheduleManager scheduleManager;
	/**
	 * 任务类型
	 */
	ScheduleTaskType taskTypeInfo;
	
	/**
	 * 任务处理的接口类
	 */
	protected IScheduleTaskDeal<T> taskDealBean;
		
	/**
	 * 当前任务队列的版本号
	 */
	protected long taskListVersion = 0;
	final Object lockVersionObject = new Object();
	final Object lockRunningList = new Object();

	protected List<T> taskList = new CopyOnWriteArrayList<T>();

	/**
	 * 是否可以批处理
	 */
	boolean isMutilTask = false;
	
	/**
	 * 是否已经获得终止调度信号
	 */
	boolean isStopSchedule = false;// 用户停止队列调度
	boolean isSleeping = false;
	
	StatisticsInfo statisticsInfo;
	/**
	 * 创建一个调度处理器 
	 * @param aManager
	 * @param aTaskDealBean
	 * @param aStatisticsInfo
	 * @throws Exception
	 */
	public TBScheduleProcessorSleep(TBScheduleManager aManager,
			IScheduleTaskDeal<T> aTaskDealBean,	StatisticsInfo aStatisticsInfo) throws Exception {
		this.scheduleManager = aManager;
		this.statisticsInfo = aStatisticsInfo;
		this.taskTypeInfo = this.scheduleManager.getTaskTypeInfo();
		this.taskDealBean = aTaskDealBean;
		if (this.taskDealBean instanceof IScheduleTaskDealSingle<?>) {
			if (taskTypeInfo.getExecuteNumber() > 1) {
				taskTypeInfo.setExecuteNumber(1);
			}
			isMutilTask = false;
		} else {
			isMutilTask = true;
		}
		if (taskTypeInfo.getFetchDataNumber() < taskTypeInfo.getThreadNumber() * 10) {
			logger.warn("???????��???????????????????��??????????????fetchnum?? >= ?????????threadnum?? *?????????????10?? ");
		}
		for (int i = 0; i < taskTypeInfo.getThreadNumber(); i++) {
			this.startThread(i);
		}
	}

	/**
	 * ???????????????????????????????????????????????????????????????????
	 * @throws Exception
	 */
	public void stopSchedule() throws Exception {
		// ?????????????,?????????????????????????????????????
		this.isStopSchedule = true;
		//???????��????????,?????????????��????????????
		this.taskList.clear();
	}

	private void startThread(int index) {
		Thread thread = new Thread(this);
		threadList.add(thread);
		String threadName = this.scheduleManager.getScheduleServer().getTaskType()+"-" 
				+ this.scheduleManager.getCurrentSerialNumber() + "-exe"
				+ index;
		thread.setName(threadName);
		thread.start();
	}

	   public synchronized Object getScheduleTaskId() {
		     if (this.taskList.size() > 0)
		    	 return this.taskList.remove(0);  // ????????
		     return null;
		   }

		   public synchronized Object[] getScheduleTaskIdMulti() {
		       if (this.taskList.size() == 0){
		         return null;
		       }
		       int size = taskList.size() > taskTypeInfo.getExecuteNumber() ? taskTypeInfo.getExecuteNumber()
						: taskList.size();
		       
		       Object[] result = null;
		       if(size >0){
		    	   result =(Object[])Array.newInstance(this.taskList.get(0).getClass(),size);
		       }
		       for(int i=0;i<size;i++){
		      	 result[i] = this.taskList.remove(0);  // ????????
		       }
		       return result;
		   }

	public void clearAllHasFetchData() {
		this.taskList.clear();
	}
	public boolean isDealFinishAllData() {
		return this.taskList.size() == 0 ;
	}
	
	public boolean isSleeping(){
    	return this.isSleeping;
    }
	protected int loadScheduleData() {
		try {
           //???????????????????????????
			if (this.taskTypeInfo.getSleepTimeInterval() > 0) {
				if(logger.isTraceEnabled()){
					logger.trace("???????????????????" + this.taskTypeInfo.getSleepTimeInterval());
				}
				this.isSleeping = true;
			    Thread.sleep(taskTypeInfo.getSleepTimeInterval());
			    this.isSleeping = false;
			    
				if(logger.isTraceEnabled()){
					logger.trace("?????????????????????");
				}
			}
			
			List<TaskItemDefine> taskItems = this.scheduleManager.getCurrentScheduleTaskItemList();
			// ???????????????????????????????????????��???
			if (taskItems.size() > 0) {
				List<TaskItemDefine> tmpTaskList= new ArrayList<TaskItemDefine>();
				synchronized(taskItems){
					for (TaskItemDefine taskItemDefine : taskItems) {
						tmpTaskList.add(taskItemDefine);
					}
				}
				List<T> tmpList = this.taskDealBean.selectTasks(
						taskTypeInfo.getTaskParameter(),
						scheduleManager.getScheduleServer().getOwnSign(),
						this.scheduleManager.getTaskItemCount(), tmpTaskList,
						taskTypeInfo.getFetchDataNumber());
				scheduleManager.getScheduleServer().setLastFetchDataTime(new Timestamp(scheduleManager.scheduleCenter.getSystemTime()));
				if(tmpList != null){
				   this.taskList.addAll(tmpList);
				}
			} else {
				if(logger.isTraceEnabled()){
					   logger.trace("??��???????????????????");
				}
			}
			addFetchNum(taskList.size(),"TBScheduleProcessor.loadScheduleData");
			return this.taskList.size();
		} catch (Throwable ex) {
			logger.error("Get tasks error.", ex);
		}
		return 0;
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public void run(){
	      try {
	        long startTime =0;
	        while(true){
	          this.m_lockObject.addThread();
	          Object executeTask;
	          while (true) {
	            if(this.isStopSchedule == true){//?????��???
	              this.m_lockObject.realseThread();
	              this.m_lockObject.notifyOtherThread();//?????��????????
				  synchronized (this.threadList) {			
					  this.threadList.remove(Thread.currentThread());
					  if(this.threadList.size()==0){
							this.scheduleManager.unRegisterScheduleServer();
					  }
				  }
				  return;
	            }
	            
	            //???????????
	            if(this.isMutilTask == false){
	              executeTask = this.getScheduleTaskId();
	            }else{
	              executeTask = this.getScheduleTaskIdMulti();
	            }
	            
	            if(executeTask == null){
	              break;
	            }
	            
	            try {//???????????
	              startTime =scheduleManager.scheduleCenter.getSystemTime();
	              if (this.isMutilTask == false) {
						if (((IScheduleTaskDealSingle) this.taskDealBean).execute(executeTask,scheduleManager.getScheduleServer().getOwnSign()) == true) {
							addSuccessNum(1, scheduleManager.scheduleCenter.getSystemTime()
									- startTime,
									"com.taobao.pamirs.schedule.TBScheduleProcessorSleep.run");
						} else {
							addFailNum(1, scheduleManager.scheduleCenter.getSystemTime()
									- startTime,
									"com.taobao.pamirs.schedule.TBScheduleProcessorSleep.run");
						}
					} else {
						if (((IScheduleTaskDealMulti) this.taskDealBean)
								.execute((Object[]) executeTask,scheduleManager.getScheduleServer().getOwnSign()) == true) {
							addSuccessNum(((Object[]) executeTask).length,scheduleManager.scheduleCenter.getSystemTime()
									- startTime,
									"com.taobao.pamirs.schedule.TBScheduleProcessorSleep.run");
						} else {
							addFailNum(((Object[]) executeTask).length,scheduleManager.scheduleCenter.getSystemTime()
									- startTime,
									"com.taobao.pamirs.schedule.TBScheduleProcessorSleep.run");
						}
					} 
	            }catch (Throwable ex) {
					if (this.isMutilTask == false) {
						addFailNum(1,scheduleManager.scheduleCenter.getSystemTime()- startTime,
								"TBScheduleProcessor.run");
					} else {
						addFailNum(((Object[]) executeTask).length, scheduleManager.scheduleCenter.getSystemTime()
								- startTime,
								"TBScheduleProcessor.run");
					}
					logger.warn("Task :" + executeTask + " ???????", ex);				
	            }
	          }
	          //????????????��??????????????
	            if(logger.isTraceEnabled()){
				   logger.trace(Thread.currentThread().getName() +"????????????????:" +this.m_lockObject.count());
			    }
				if (this.m_lockObject.realseThreadButNotLast() == false) {
					int size = 0;
					Thread.currentThread().sleep(100);
					startTime =scheduleManager.scheduleCenter.getSystemTime();
					// ???????
					size = this.loadScheduleData();
					if (size > 0) {
						this.m_lockObject.notifyOtherThread();
					} else {
						//?��??????????????????????????
						if (this.isStopSchedule == false && this.scheduleManager.isContinueWhenData()== true ){						 
							if(logger.isTraceEnabled()){
								   logger.trace("????????????start sleep");
							}
							this.isSleeping = true;
						    Thread.currentThread().sleep(this.scheduleManager.getTaskTypeInfo().getSleepTimeNoData());
						    this.isSleeping = false;
						    
						    if(logger.isTraceEnabled()){
								   logger.trace("Sleep end");
							}
						}else{
							//???????????????????????��?????
							this.m_lockObject.notifyOtherThread();
						}
					}
					this.m_lockObject.realseThread();
				} else {// ????????????????????��??????????????????????????
					if(logger.isTraceEnabled()){
						   logger.trace("??????????????sleep");
					}
					this.m_lockObject.waitCurrentThread();
				}
	        }
	      }
	      catch (Throwable e) {
	    	  logger.error(e.getMessage(), e);
	      }
	    }

	public void addFetchNum(long num, String addr) {
		
        this.statisticsInfo.addFetchDataCount(1);
        this.statisticsInfo.addFetchDataNum(num);
	}

	public void addSuccessNum(long num, long spendTime, String addr) {
        this.statisticsInfo.addDealDataSucess(num);
        this.statisticsInfo.addDealSpendTime(spendTime);
	}

	public void addFailNum(long num, long spendTime, String addr) {
      this.statisticsInfo.addDealDataFail(num);
      this.statisticsInfo.addDealSpendTime(spendTime);
	}
}
