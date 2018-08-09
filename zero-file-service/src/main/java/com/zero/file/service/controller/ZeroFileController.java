package com.zero.file.service.controller;

import com.zero.file.service.service.ZeroFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * @Author:xuyp
 * @Date:2018/8/6 22:33
 */
@Controller
public class ZeroFileController {

    @Autowired
    private ZeroFileInfoService zeroFileInfoService;

    /**
     * 显示上传页面
     * @param modelAndView
     * @return
     */
    @GetMapping("filePage")
    public ModelAndView fileUploadPage(ModelAndView modelAndView) {
        modelAndView.setViewName("file/index");
        modelAndView.addObject("groupKey", UUID.randomUUID().toString());
        return modelAndView;
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("uploadFile")
    @ResponseBody
    public String uploadFile(MultipartFile file, String groupKey) {
        zeroFileInfoService.saveFile(file, groupKey);
        return "{\"code\":\"success\"}";
    }
}
