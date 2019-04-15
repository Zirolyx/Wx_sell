package com.xmcc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class MainController {

    @GetMapping("test1")
    public String test(){
        log.info("演示信息");
        return "测试成功！";
    }
}
