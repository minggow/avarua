package com.mingguo.avarua.infra.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wumingguo on 2015/10/2.
 */
@RestController
public class HcController {

    @RequestMapping("/hc/status.html")
    public String hc() {
        return "SUCCESS";
    }
}
