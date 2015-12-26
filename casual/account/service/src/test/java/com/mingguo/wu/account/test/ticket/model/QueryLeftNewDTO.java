package com.mingguo.wu.account.test.ticket.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by mingguo.wu on 2015/11/26.
 */
@Data
public class QueryLeftNewDTO {
    private String train_no;
    private String station_train_code;// G263,
    private String start_station_telecode;// VNP,
    private String start_station_name;// 北京南,
    private String end_station_telecode;// ENH,
    private String end_station_name;// 合肥南,
    private String from_station_telecode;// VNP,
    private String from_station_name;// 北京南,
    private String to_station_telecode;// UUH,
    private String to_station_name;// 徐州东,
    private String start_time;// 08;45,
    private String arrive_time;// 11;41,
    private String day_difference;// 0,
    private String train_class_name;// ,
    private String lishi;// 02;56,
    private String canWebBuy;// Y,
    private String lishiValue;// 176,
    private String yp_info;// O000000578M0000001289000000020,
    private String control_train_day;// 20301231,
    private String start_train_date;// 20160106,
    private String seat_feature;// O3M393,
    private String yp_ex;// O0M090,
    private String train_seat_feature;// 3,
    private String seat_types;// OM9,
    private String location_code;// P3,
    private String from_station_no;// 01,
    private String to_station_no;// 05,
    private String control_day;// 59,
    private String sale_time;// 1230,
    private String is_support_card;// 0,
    private String yz_num;// --,
    private String rz_num;// --,
    private String yw_num;// --,
    private String rw_num;// --,
    private String gr_num;// --,
    private String zy_num;// 有,
    private String ze_num;// 有,
    private String tz_num;// --,
    private String gg_num;// --,
    private String yb_num;// --,
    private String wz_num;// --,
    private String qt_num;// --,
    private String swz_num;// 20
}
