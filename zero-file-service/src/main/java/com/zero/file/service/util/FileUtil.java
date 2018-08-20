package com.zero.file.service.util;

import com.zero.file.service.model.ZeroFileInfo;
import com.zero.file.service.service.FileConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;

/** 文件工具类，文件上传、读取等功能
 * @Author:xuyp
 * @Date:2018/8/18 15:52
 */
@Component
public class FileUtil {

    @Autowired
    private FileConfigService fileConfigService;


    /**
     * 上传文件保存操作
     * @param file 上传文件对象
     * @param filePath 文件保存路径
     * @return
     */
    public String saveFile(MultipartFile file, String way) throws IOException {
        String ret;
        // 根据不同方式选择不同的处理
        way = StringUtils.isNotBlank(way) ? way : "ERROR";
        Way saveWay = Way.valueOf(way);
        switch (saveWay) {
            case SMB:
                String smbUrl = fileConfigService.getSmbUrl();
                ret = SMBFileUtil.uplaodFile(smbUrl, file);
                break;
            case LOCAL:
                String localFilePath = fileConfigService.getLocalPath();
                ret = LocalFileUtil.saveFile(localFilePath, file);
                break;
            default:
                return Way.ERROR.toString();
        }
        return ret;
    }

    /**
     * 读取文件
     */
    public void readFile(ZeroFileInfo fileInfo, OutputStream outputStream) {
        String way = fileInfo.getSaveFileWay();
        way = StringUtils.isNotBlank(way) ? way : "ERROR";
        Way saveWay = Way.valueOf(way);
        switch (saveWay) {
            case SMB:
                SMBFileUtil.readSmbFile(fileInfo.getPath(), outputStream);
                break;
            case LOCAL:
                LocalFileUtil.readLocalFile(fileInfo.getPath(), outputStream);
                break;
            default:
                break;
        }
    }

    /**
     * 删除文件
     * @param filePath 文件保存路径
     */
    public void deleteFile(ZeroFileInfo zeroFileInfo) {
        String way = zeroFileInfo.getSaveFileWay();
        way = StringUtils.isNotBlank(way) ? way : "ERROR";
        Way saveWay = Way.valueOf(way);
        switch (saveWay) {
            case SMB:
                SMBFileUtil.deleteFile(zeroFileInfo.getPath());
                break;
            case LOCAL:
                LocalFileUtil.deleteFile(zeroFileInfo.getPath());
                break;
            case ERROR:
                break;
        }
    }

    /**
     * 保存文件的方式
     */
    public enum Way {
        LOCAL,
        SMB,
        ERROR
    }
}
