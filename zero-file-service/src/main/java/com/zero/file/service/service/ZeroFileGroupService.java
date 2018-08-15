package com.zero.file.service.service;

import com.zero.file.service.model.ZeroFileGroup;

/**
 * 文件组
 * @Author:xuyp
 * @Date:2018/8/6 23:00
 */
public interface ZeroFileGroupService {
    /**
     * 根据key 查找唯一对象
     * @param groupKey
     * @return
     */
    ZeroFileGroup findOne(String groupKey);

    /**
     * 删除对象
     * @param fileGroup
     */
    void delete(ZeroFileGroup fileGroup);

    /**
     * 保存对象
     * @param group
     * @return
     */
    ZeroFileGroup save(ZeroFileGroup group);
}
