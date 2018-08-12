package com.zero.file.service.service.impl;

import com.zero.file.service.model.ZeroFileGroup;
import com.zero.file.service.model.ZeroFileInfo;
import com.zero.file.service.repository.ZeroFileInfoRepository;
import com.zero.file.service.service.ZeroFileInfoService;
import com.zero.file.service.util.LocalFileUtil;
import com.zero.file.service.vo.TableVO;
import com.zero.file.service.vo.ZeroFileInfoVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件信息 Service
 * @Author:xuyp
 * @Date:2018/8/6 22:59
 */
@Service
public class ZeroFileInfoServiceImpl implements ZeroFileInfoService {

    private static final Logger log = LoggerFactory.getLogger(ZeroFileInfoServiceImpl.class);

    @Autowired
    private ZeroFileInfoRepository fileInfoRepository;

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
        fileInfo.setOriginalFileName(file.getOriginalFilename());
        fileInfo.setPath(filePath);
        fileInfo.setFileGroup(group);
        fileInfo.setSaveFileWay("local");
        fileInfoRepository.save(fileInfo);
    }

    @Override
    public TableVO getAllByPage(TableVO tableVO, ZeroFileInfoVO fileInfoVO) {
        // 解析条件
        if  (StringUtils.isBlank(tableVO.getOrderField())) {
            tableVO.setOrderField("createTime");
        }
        Sort.Direction direction = Sort.Direction.DESC;
        if (tableVO.getOrder() != null && "ASC".equals(tableVO.getOrder().toUpperCase())) {
            direction = Sort.Direction.ASC;
        }
        Sort sort = new Sort(direction, tableVO.getOrderField());
        Pageable pageable = PageRequest.of(tableVO.getPageNo(), tableVO.getPageSize(), sort);
        // 查询
        Page<ZeroFileInfo> page = fileInfoRepository.findAll((root, query, cb) -> {
            return null;
        }, pageable);
        // 解析查询结果
        List<ZeroFileInfoVO> voList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(page.getContent())) {
            page.getContent().stream().forEach(a -> {
                ZeroFileInfoVO vo = new ZeroFileInfoVO();
                try {
                    BeanUtils.copyProperties(vo, a);
                    voList.add(vo);
                } catch (IllegalAccessException e) {
                    log.error("", e);
                } catch (InvocationTargetException e) {
                    log.error("", e);
                }
            });
        }
        // 返回结果处理
        tableVO.setRows(voList);
        tableVO.setTotal(page.getTotalElements());

        return tableVO;
    }
}
