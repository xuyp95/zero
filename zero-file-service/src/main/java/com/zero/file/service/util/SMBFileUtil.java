package com.zero.file.service.util;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.UUID;

/**
 * 基于windows的SMB共享文件的上传和读取
 * @Author:xuyp
 * @Date:2018/8/12 23:04
 */
public class SMBFileUtil {

    private static final Logger log = LoggerFactory.getLogger(SMBFileUtil.class);

    /**
     * 上传文件到远程共享文件夹
     * @return 远程共享文件夹路径
     * @param smbUrl 格式：smb://windows登录用户名:windows登录密码@电脑ip/文件夹/文件名"
     * @param file
     */
    public static String uplaodFile(String smbUrl, MultipartFile file) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            // 解析文件获取相关文件信息
            String originalFileName = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString().replaceAll("-","");
            String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
            // smb 连接
            SmbFile smbFile = new SmbFile(smbUrl);
//            smbFile.connect();
            if (!smbFile.exists()) {
                smbFile.mkdir();
            }
            String filePath = smbUrl + fileName + fileSuffix;
            outputStream = new SmbFileOutputStream(filePath);
            inputStream = file.getInputStream();
            byte[] bytes = new byte[2048];
            while ((inputStream.read(bytes) != -1)) {
                outputStream.write(bytes);
            }
            return filePath;
        } catch (MalformedURLException e) {
            log.error("SMB file upload error.", e);
        } catch (SmbException e) {
            log.error("SMB file upload error.", e);
        } catch (IOException e) {
            log.error("SMB file upload error.", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 读取远程文件的文件流
     * @param smbUrl smb文件路径
     * @param outputStream
     */
    public static void readSmbFile(String smbUrl, OutputStream outputStream) {

        InputStream inputStream = null;
        try {
            SmbFile smbFile = new SmbFile(smbUrl);
            if (!smbFile.exists()) {
                throw new RuntimeException("文件不存在");
            }
            inputStream = smbFile.getInputStream();
            byte[] bytes = new byte[2048];
            while (inputStream.read(bytes) != -1) {
                outputStream.write(bytes);
            }
        } catch (MalformedURLException e) {
            log.error("reade smb file to OuputStream Error.", e);
        } catch (SmbException e) {
            log.error("reade smb file to OuputStream Error.", e);
        } catch (IOException e) {
            log.error("reade smb file to OuputStream Error.", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }

    /**
     * 删除共享文件夹中的文件
     * @param smbUrl
     */
    public static String deleteFile(String smbUrl) {
        try {
            SmbFile smbFile = new SmbFile(smbUrl);
            if (smbFile.exists()) {
                smbFile.delete();
            } else {
                return "文件不存在";
            }
        } catch (MalformedURLException e) {
            log.error("delete smb file error.", e);
        } catch (SmbException e) {
            log.error("delete smb file error.", e);
        }
        return "success";
    }
}
