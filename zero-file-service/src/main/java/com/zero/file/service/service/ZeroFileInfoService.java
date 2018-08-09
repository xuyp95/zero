package com.zero.file.service.service;

import org.springframework.web.multipart.MultipartFile; /**
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
}
