package com.zero.file.service.service.impl;

import com.zero.file.service.model.FileConfig;
import com.zero.file.service.repository.FileConfigRepository;
import com.zero.file.service.service.FileConfigService;
import com.zero.file.service.util.FileConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * 文件服务的相关参数
 * @Author:xuyp
 * @Date:2018/8/12 23:56
 */
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
        Optional<FileConfig> optional = fileConfigRepository.findOne((root, query, cb) -> {
            return cb.equal(root.get(FileConstantUtil.CONFIG_KEY), FileConstantUtil.WAY);
        });
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
}
