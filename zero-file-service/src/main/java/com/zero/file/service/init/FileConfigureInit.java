package com.zero.file.service.init;

import com.zero.file.service.model.FileConfig;
import com.zero.file.service.service.FileConfigService;
import com.zero.file.service.util.FileConstantUtil;
import com.zero.file.service.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置文件初始化数据
 * @Author:xuyp
 * @Date:2018/8/18 17:11
 */
@Component
public class FileConfigureInit implements ApplicationRunner {

    @Autowired
    private FileConfigService fileConfigService;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<FileConfig> configs = new ArrayList<>();

        // 初始化上传文件方式为LOCAL
        String way = fileConfigService.getWay();
        if (StringUtils.isBlank(way)) {
            FileConfig wayConfig = new FileConfig();
            wayConfig.setConfigKey("way");
            wayConfig.setConfigValue(FileUtil.Way.LOCAL.toString());
            wayConfig.setCreateTime(LocalDateTime.now());
            wayConfig.setUpdateTime(LocalDateTime.now());
            configs.add(wayConfig);
        }
        // 初始化上传文件路径为 D:/fileservice/
        String localPath = fileConfigService.getLocalPath();
        if (StringUtils.isBlank(localPath)) {
            FileConfig pathConfig = new FileConfig();
            pathConfig.setConfigKey(FileConstantUtil.LOCAL_PATH);
            pathConfig.setConfigValue("D:/fileService/");
            pathConfig.setCreateTime(LocalDateTime.now());
            pathConfig.setUpdateTime(LocalDateTime.now());
            configs.add(pathConfig);
        }
        fileConfigService.save(configs);
    }
}
