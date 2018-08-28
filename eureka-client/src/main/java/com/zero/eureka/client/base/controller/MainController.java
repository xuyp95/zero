package com.zero.eureka.client.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 布局页面显示controller
 * @Author:xuyp
 * @Date:2018/8/26 11:15
 */
@Controller
public class MainController {

    /**
     * 显示当前模块的基础页面
     * @param modelAndView
     * @return
     */
    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
