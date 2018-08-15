package com.zero.file.service.service.impl;

import com.zero.file.service.model.ZeroFileGroup;
import com.zero.file.service.repository.ZeroFileGroupRepository;
import com.zero.file.service.service.ZeroFileGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 文件组Service
 * @Author:xuyp
 * @Date:2018/8/6 23:00
 */
@Service
public class ZeroFileGroupServiceImpl implements ZeroFileGroupService {

    @Autowired
    private ZeroFileGroupRepository zeroFileGroupRepository;

    @Override
    public ZeroFileGroup findOne(String groupKey) {
        ZeroFileGroup group = new ZeroFileGroup(groupKey);
        group.setCreateTime(null);
        group.setUpdateTime(null);
        Optional optional = zeroFileGroupRepository.findOne(Example.of(group));
        if (optional.isPresent()) {
            return (ZeroFileGroup) optional.get();
        }
        return null;
    }

    @Override
    public void delete(ZeroFileGroup fileGroup) {
        zeroFileGroupRepository.delete(fileGroup);
    }

    @Override
    public ZeroFileGroup save(ZeroFileGroup group) {
        return zeroFileGroupRepository.save(group);
    }
}
