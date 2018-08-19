package com.zero.file.service.service.impl;

import com.zero.file.service.model.ZeroFileGroup;
import com.zero.file.service.model.ZeroFileInfo;
import com.zero.file.service.repository.ZeroFileInfoRepository;
import com.zero.file.service.service.FileConfigService;
import com.zero.file.service.service.ZeroFileGroupService;
import com.zero.file.service.service.ZeroFileInfoService;
import com.zero.file.service.util.FileUtil;
import com.zero.file.service.util.LocalFileUtil;
import com.zero.file.service.vo.ResultMessage;
import com.zero.file.service.vo.TableVO;
import com.zero.file.service.vo.ZeroFileInfoVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private ZeroFileGroupService zeroFileGroupService;
    @Autowired
    private FileConfigService fileConfigService;
    @Autowired
    private FileUtil fileUtil;

    @Override
    public void saveFile(MultipartFile file, String groupKey) {
        // 保存文件
        String filePath = null;
        // 根据不同方式选择不同的处理
        String way = fileConfigService.getWay();
        try {
            filePath = fileUtil.saveFile(file, way);
        } catch (IOException e) {
            log.error("", e);
            throw new RuntimeException("save file error");
        }

        // 保存文件信息
        ZeroFileGroup group = zeroFileGroupService.findOne(groupKey);
        if (group == null) {
            group = zeroFileGroupService.save(new ZeroFileGroup(groupKey));
        }
        ZeroFileInfo fileInfo = new ZeroFileInfo();
        fileInfo.setOriginalFileName(file.getOriginalFilename());
        fileInfo.setPath(filePath);
        fileInfo.setFileGroup(group);
        fileInfo.setSaveFileWay(way);
        fileInfoRepository.save(fileInfo);
    }

    @Override
    public void readFile(String fileId, OutputStream outputStream) {
        if (StringUtils.isNotBlank(fileId)) {
            ZeroFileInfo fileInfo = fileInfoRepository.findById(fileId).get();
            fileUtil.readFile(fileInfo, outputStream);
        } else {
            throw new RuntimeException("文件对象不存在");
        }
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

    @Override
    public ResultMessage deleteFile(String fileId) {

        if (StringUtils.isNotBlank(fileId)) {
            Optional<ZeroFileInfo> optional = fileInfoRepository.findById(fileId);
            if (optional.isPresent()) {
                ZeroFileInfo fileInfo = optional.get();
                // 删除文件
                fileUtil.deleteFile(fileInfo);
                // 判断当前的文件组是不是只剩下当前文件，是的话也一起删除
                List<ZeroFileInfo> fileInfos = fileInfo.getFileGroup().getFileInfo();
                if (fileInfos != null && fileInfos.size() <= 1 && fileId.equals(fileInfos.get(0).getId())) {
                    zeroFileGroupService.delete(fileInfo.getFileGroup());
                }
                // 删除记录
                fileInfoRepository.delete(fileInfo);
                return ResultMessage.success();
            }
        }
        return ResultMessage.fail("id为空");
    }

    @Override
    public void download(ZeroFileInfo fileInfo, OutputStream outputStream) {
        fileUtil.readFile(fileInfo, outputStream);
    }

    @Override
    public ZeroFileInfo findById(String fileId) {
        Optional<ZeroFileInfo> optional = fileInfoRepository.findById(fileId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
