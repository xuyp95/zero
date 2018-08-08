package com.zero.file.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author:xuyp
 * @Date:2018/8/6 22:33
 */
@Controller
public class ZeroFileController {

    /**
     * 显示上传页面
     * @param modelAndView
     * @return
     */
    @GetMapping("filePage")
    public ModelAndView fileUploadPage(ModelAndView modelAndView) {
        modelAndView.setViewName("file/index");
        return modelAndView;
    }
}
