package com.mingguo.avarua.casual.account.test.ticket;

import com.google.gson.Gson;
import com.mingguo.avarua.casual.account.test.ticket.model.BookItem;
import com.mingguo.avarua.casual.account.test.ticket.model.QueryLeftNewDTO;
import com.mingguo.avarua.casual.account.test.ticket.model.QueryResult;
import com.mingguo.avarua.casual.account.test.ticket.util.HttpClientUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mingguo.wu on 2015/11/26.
 */
public class QueryMain {
    //"https://kyfw.12306.cn/otn/leftTicket/queryT?leftTicketDTO.train_date=2016-01-06&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=XCH&purpose_codes=ADULT";
    private static final Gson GSON = new Gson();
    private static final String PREFIX = "https://kyfw.12306.cn/otn/leftTicket/queryT?leftTicketDTO.train_date=";
    private static final String SUFFIX = "&leftTicketDTO.from_station=XCH&leftTicketDTO.to_station=BJP&purpose_codes=ADULT";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd mm:HH:ss");

    public static void main(String[] args) throws Exception{
        //循环查询余票接口
        String ticketDate = "2016-02-14";
        String queryUrl = PREFIX + ticketDate + SUFFIX;
        while(true) {
            String result = HttpClientUtil.execute(queryUrl, "UTF-8");
//            System.out.println(result);
            QueryResult queryResult = null;
            try {
                queryResult = GSON.fromJson(result, QueryResult.class);
            } catch (Exception e) {
                System.out.println("转化JSON出错！！！");
                continue;
            }

            if(queryResult != null && "true".equals(queryResult.getStatus()) && "200".equals(queryResult.getHttpstatus())) {
                //查询成功遍历结果集
                List<BookItem> bookItemList = queryResult.getData();
                if(null != bookItemList && bookItemList.size() > 0) {
                    for(BookItem bookItem : bookItemList) {
                        QueryLeftNewDTO queryLeftNewDTO = bookItem.getQueryLeftNewDTO();
                        String trainCode = queryLeftNewDTO.getStation_train_code();
                        if(trainCode.startsWith("Z") || trainCode.startsWith("T")) {//直达和特快
                            String yzNumber = queryLeftNewDTO.getYz_num();
                            String ywNumber = queryLeftNewDTO.getYw_num();
                            String wzNumber = queryLeftNewDTO.getWz_num();
                            if((!"--".equals(yzNumber) && !"无".equals(yzNumber) && !"*".equals(yzNumber))
                                    || (!"--".equals(ywNumber) && !"无".equals(ywNumber) && !"*".equals(ywNumber))
                                    /*|| (!"--".equals(wzNumber) && !"无".equals(wzNumber))*/ ) {

                                System.err.println(sdf.format(new Date()) + " 车次：" + trainCode + " 出发时间：" + queryLeftNewDTO.getArrive_time()
                                        + "\n硬座：" + yzNumber + "，  硬卧：" + ywNumber + "， 无座：" + wzNumber);
                            }
                        }
                    }
                }
            }
            Thread.sleep(3000); // sleepWhile
        }

    }
}
