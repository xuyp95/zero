package com.zero.file.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:xuyp
 * @Date:2018/8/6 22:33
 */
@Controller
@RequestMapping("file")
public class ZeroFileController {

    @GetMapping("filePage")
    public String fileUploadPage() {
        return "";
    }
}
