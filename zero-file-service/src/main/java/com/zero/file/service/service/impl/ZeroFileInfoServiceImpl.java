package com.zero.file.service.service.impl;

import com.zero.file.service.model.ZeroFileGroup;
import com.zero.file.service.model.ZeroFileInfo;
import com.zero.file.service.repository.ZeroFileInfoRepository;
import com.zero.file.service.service.ZeroFileInfoService;
import com.zero.file.service.util.LocalFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件信息 Service
 * @Author:xuyp
 * @Date:2018/8/6 22:59
 */
@Service
public class ZeroFileInfoServiceImpl implements ZeroFileInfoService {

    private static final Logger log = LoggerFactory.getLogger(ZeroFileInfoServiceImpl.class);

    @Autowired
    private ZeroFileInfoRepository zeroFileInfoRepository;

    @Override
    public void saveFile(MultipartFile file, String groupKey) {

        // 保存文件
        String filePath = null;
        try {
            filePath = LocalFileUtil.saveFile("D:/fileService/",file.getOriginalFilename(), file.getBytes());
        } catch (IOException e) {
            log.error("", e);
            throw new RuntimeException("save file error");
        }

        // 保存文件信息
        ZeroFileGroup group = new ZeroFileGroup(groupKey);
        ZeroFileInfo fileInfo = new ZeroFileInfo();
        fileInfo.setFileName(file.getOriginalFilename());
        fileInfo.setPath(filePath);
        fileInfo.setFileGroup(group);
        fileInfo.setSaveFileWay("local");
        zeroFileInfoRepository.save(fileInfo);
    }
}
