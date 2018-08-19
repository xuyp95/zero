package com.zero.file.service.controller;

import com.zero.file.service.model.ZeroFileInfo;
import com.zero.file.service.service.ZeroFileInfoService;
import com.zero.file.service.vo.ResultMessage;
import com.zero.file.service.vo.TableVO;
import com.zero.file.service.vo.ZeroFileInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Table;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @Author:xuyp
 * @Date:2018/8/6 22:33
 */
@Controller
@RequestMapping("file")
public class ZeroFileController {

    private static final Logger log = LoggerFactory.getLogger(ZeroFileController.class);

    @Autowired
    private ZeroFileInfoService zeroFileInfoService;

    /**
     * 文件列表页面
     * @return
     */
    @GetMapping("fileList")
    public String fileList() {
        return "file/fileList";
    }

    @PostMapping("getAllByPage")
    @ResponseBody
    public TableVO getAllByPage(TableVO tableVO, ZeroFileInfoVO fileInfoVO) {

        TableVO retVO = zeroFileInfoService.getAllByPage(tableVO, fileInfoVO);
        return retVO;
    }

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

    /**
     * 读取文件流
     * @param fileId
     * @param response
     */
    @GetMapping("readFile/{fileId}")
    public void readFile(@PathVariable String fileId, HttpServletResponse response) {
        try {
            zeroFileInfoService.readFile(fileId, response.getOutputStream());
        } catch (IOException e) {
            log.error("", e);
            throw new RuntimeException("IOException");
        }
    }

    @GetMapping("deleteFile")
    @ResponseBody
    public ResultMessage deleteFile(String fileId) {
        return zeroFileInfoService.deleteFile(fileId);
    }

    @GetMapping("fileExists/{fileId}")
    @ResponseBody
    public ResultMessage fileExists(@PathVariable String fileId) {
        return null;
    }

    @GetMapping("download/{fileId}")
    public void downloadFile(@PathVariable String fileId, HttpServletResponse response) {
        // response 相关设置必须要在读取文件流之前
        response.setContentType("application/force-download");// 设置成强制下载，避免浏览器进行预览
        ZeroFileInfo fileInfo = zeroFileInfoService.findById(fileId);
        if (fileInfo != null) {
            String fileName = fileInfo.getOriginalFileName();
            try {
                response.addHeader("Content-Disposition",
                        "attachment;fileName=\"" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + "\"");
                zeroFileInfoService.download(fileInfo, response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
