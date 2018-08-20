package com.zero.file.service.service;

import com.zero.file.service.model.FileConfig;
import com.zero.file.service.vo.FileConfigureVO;
import com.zero.file.service.vo.ResultMessage;

import java.util.Collection;

/**
 * 文件服务配置
 * @Author:xuyp
 * @Date:2018/8/12 23:56
 */
public interface FileConfigService {

    /**
     * 保存对象
     * @param configs 待保存对象集合
     */
    void save(Collection<FileConfig> configs);

    /**
     * 保存对象
     * @param config 待保存对象
     * @return
     */
    FileConfig save(FileConfig config);

    /**
     * 获取当前文件保存的方式
     * @return
     */
    String getWay();

    /**
     * 查询获得本地保存路径
     * @return
     */
    String getLocalPath();

    /**
     * 查询获得SmbUrl
     * @return
     */
    String getSmbUrl();

    /**
     * 保存配置参数
     * @param configureVO
     */
    ResultMessage save(FileConfigureVO configureVO);

    /**
     * get config value
     * @return
     */
    FileConfigureVO getConfig();
}
