package org.cronhub.managesystem.modules.task.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.cronhub.managesystem.commons.dao.bean.Task;
import org.cronhub.managesystem.modules.task.dao.ITaskDao;

import java.util.Date;

public class PageAddAction extends ActionSupport {
	private ITaskDao dao;
	private Task task;
	public void setDao(ITaskDao dao) {
		this.dao = dao;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public String submitAddForm(){
		task.setUpdate_time(new Date());
		String user_id = (String)ActionContext.getContext().getSession().get("user_id");
		task.setUser_id(user_id);
		this.dao.insert(task);
		return SUCCESS;
	}

}
