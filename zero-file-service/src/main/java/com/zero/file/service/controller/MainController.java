package com.zero.file.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主页控制器
 * @Author:xuyp
 * @Date:2018/8/10 0:30
 */
@Controller
public class MainController {

    /**
     * 跳转到管理页面
     * @param modelAndView
     * @return
     */
    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
