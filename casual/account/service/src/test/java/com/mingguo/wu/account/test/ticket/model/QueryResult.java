package com.mingguo.wu.account.test.ticket.model;

import com.mingguo.wu.account.test.ticket.model.BookItem;
import lombok.Data;

import java.util.List;

/**
 * Created by mingguo.wu on 2015/11/26.
 */
@Data
public class QueryResult {
    private String validateMessagesShowId;//
    private String status;//": true,
    private String httpstatus;
    List<BookItem> data;
}
