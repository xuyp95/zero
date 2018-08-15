package com.zero.file.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文件配置Controller
 * @Author:xuyp
 * @Date:2018/8/12 23:57
 */
@Controller
@RequestMapping("configure")
public class FileConfigController {

    @GetMapping("")
    public String configure() {
        return "configure/page";
    }
}
