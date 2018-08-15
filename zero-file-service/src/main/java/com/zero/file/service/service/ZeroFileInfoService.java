package com.zero.file.service.service;

import com.zero.file.service.vo.ResultMessage;
import com.zero.file.service.vo.TableVO;
import com.zero.file.service.vo.ZeroFileInfoVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

/**
 * @Author:xuyp
 * @Date:2018/8/6 22:58
 */
public interface ZeroFileInfoService {

    /**
     * 保存文件信息以及文件
     * @param file
     * @param groupKey
     */
    void saveFile(MultipartFile file, String groupKey);

    /**
     * 读取文件到输出流中
     * @param fileId 文件Id
     * @param outputStream 输出流
     */
    void readFile(String fileId, OutputStream outputStream);

    /**
     * 分页查询，并返回VO对象
     * @param tableVO
     * @param fileInfoVO
     * @return
     */
    TableVO getAllByPage(TableVO tableVO, ZeroFileInfoVO fileInfoVO);

    /**
     * 删除文件
     * @param fileId 文件id
     * @return 返回消息体
     */
    ResultMessage deleteFile(String fileId);
}
