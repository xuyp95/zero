package com.zero.file.service.service.impl;

import com.zero.file.service.model.FileConfig;
import com.zero.file.service.repository.FileConfigRepository;
import com.zero.file.service.service.FileConfigService;
import com.zero.file.service.util.FileConstantUtil;
import com.zero.file.service.vo.FileConfigureVO;
import com.zero.file.service.vo.ResultMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 文件服务的相关参数
 *
 * @Author:xuyp
 * @Date:2018/8/12 23:56
 */
@Transactional
@Service
public class FileConfigServiceImpl implements FileConfigService {

    @Autowired
    private FileConfigRepository fileConfigRepository;

    @Override
    public void save(Collection<FileConfig> configs) {
        fileConfigRepository.saveAll(configs);
    }

    @Override
    public FileConfig save(FileConfig config) {
        return fileConfigRepository.save(config);
    }

    @Override
    public String getWay() {
        Optional<FileConfig> optional = fileConfigRepository.findOne((root, query, cb) -> cb.equal(root.get(FileConstantUtil.CONFIG_KEY), FileConstantUtil.WAY));
        if (optional.isPresent()) {
            return optional.get().getConfigValue();
        }
        return null;
    }

    @Override
    public String getLocalPath() {
        Optional<FileConfig> optional = fileConfigRepository.findOne((root, query, cb) -> cb.equal(root.get(FileConstantUtil.CONFIG_KEY), FileConstantUtil.LOCAL_PATH));
        if (optional.isPresent()) {
            return optional.get().getConfigValue();
        }
        return null;
    }

    @Override
    public String getSmbUrl() {
        Optional<FileConfig> optional = fileConfigRepository.findOne((root, query, cb) -> cb.equal(root.get(FileConstantUtil.CONFIG_KEY), FileConstantUtil.SMB_URL));
        if (optional.isPresent()) {
            return optional.get().getConfigKey();
        }
        return null;
    }

    @Override
    public ResultMessage save(FileConfigureVO configureVO) {

        // 处理各个参数，并以key-value的形式保存到数据库
        List<FileConfig> configList = new ArrayList<>(10);
        // 上传文件的方式
        valueToModel(FileConstantUtil.WAY, configureVO.getUploadWay(), configList);
        // 本地上传文件地址
        valueToModel(FileConstantUtil.LOCAL_PATH, configureVO.getLocalfilePath(), configList);
        // smb文件上传地址
        valueToModel(FileConstantUtil.SMB_URL, configureVO.getSmbFilePath(), configList);

        fileConfigRepository.saveAll(configList);
        return ResultMessage.success();
    }

    @Override
    public FileConfigureVO getConfig() {
        FileConfigureVO vo = new FileConfigureVO();
        List<FileConfig> configList = fileConfigRepository.findAll();
        configList.forEach(a -> modelToVo(vo, a));
        return vo;
    }

    /**
     * 将参数值转成Model对象
     *
     * @param key
     * @param value
     * @return
     */
    private void valueToModel(String key, String value, List<FileConfig> configList) {
        if (StringUtils.isNotBlank(value)) {
            FileConfig config;
            Optional<FileConfig> optional = fileConfigRepository.findOne((root, query, cb) -> cb.equal(root.get(FileConstantUtil.CONFIG_KEY), key));
            if (optional.isPresent()) {
                config = optional.get();
                config.setConfigValue(value);
            } else {
                config = new FileConfig();
                config.setCreateTime(LocalDateTime.now());
                config.setUpdateTime(LocalDateTime.now());
                config.setConfigKey(key);
                config.setConfigValue(value);
            }
            configList.add(config);
        }
    }

    /**
     * 将model对象转成VO返回
     *
     * @param vo
     * @param config
     */
    private void modelToVo(FileConfigureVO vo, FileConfig config) {
        switch (config.getConfigKey()) {
            case FileConstantUtil.WAY:
                vo.setUploadWay(config.getConfigValue());
                break;
            case FileConstantUtil.LOCAL_PATH:
                vo.setLocalfilePath(config.getConfigValue());
                break;
            case FileConstantUtil.SMB_URL:
                vo.setSmbFilePath(config.getConfigValue());
                break;
        }
    }
}
