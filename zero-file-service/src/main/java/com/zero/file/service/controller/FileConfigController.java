package com.zero.file.service.controller;

import com.zero.file.service.service.FileConfigService;
import com.zero.file.service.vo.FileConfigureVO;
import com.zero.file.service.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 文件配置Controller
 * @Author:xuyp
 * @Date:2018/8/12 23:57
 */
@Controller
@RequestMapping("configure")
public class FileConfigController {

    @Autowired
    private FileConfigService fileConfigService;

    @GetMapping("")
    public ModelAndView configure(ModelAndView modelAndView) {
        modelAndView.setViewName("configure/page");

        FileConfigureVO configureVO = fileConfigService.getConfig();
        modelAndView.addObject("configVo", configureVO);
        return modelAndView;
    }

    @PostMapping("save")
    @ResponseBody
    public ResultMessage save(FileConfigureVO configureVO) {
        fileConfigService.save(configureVO);
        return ResultMessage.success();
    }
}
