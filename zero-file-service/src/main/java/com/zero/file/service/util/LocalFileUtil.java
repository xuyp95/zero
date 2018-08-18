package com.zero.file.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * 本地文件操作类
 * @Author:xuyp
 * @Date:2018/8/9 0:07
 */
public class LocalFileUtil {

    private static final Logger log = LoggerFactory.getLogger(LocalFileUtil.class);

    /**
     * 保存文件(文件名统一唯一标识)
     * @param savePath 保存文件的路径
     * @param originalFileName 文件原始名称
     * @param fileContent 文件内容
     * @return
     */
    public static String saveFile(String savePath, String originalFileName, byte[] fileContent) {
        String filePath = null;
        OutputStream outputStream = null;
        try {
            File file = new File(savePath);
            // 是否需要创建对应路径的文件夹
            if (!file.exists()) {
                file.mkdir();
            }
            // 文件名采用唯一标识，原始文件名存储于业务表中
            String fileName = UUID.randomUUID().toString().replaceAll("-","");
            String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
            // 输出
            outputStream = new FileOutputStream(savePath + fileName + fileSuffix);
            outputStream.write(fileContent);
            filePath = savePath + fileName + fileSuffix;
        } catch (IOException e) {
            log.error("save file exception.", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    filePath = null;
                    log.error("close outputStream Exception", e);
                }
            }
        }
        return filePath;
    }

    /**
     * 保存文件到本地
     * @param filePath 文件路径
     * @param file 上传文件对象
     * @return
     */
    public static String saveFile(final String filePath, final MultipartFile file) throws IOException {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        File saveFile = new File(filePath);
        if (!saveFile.exists()) {
            saveFile.mkdir();
        }
        // 文件名采用唯一标识，原始文件名存储于业务表中
        final String fileOriginalName = file.getOriginalFilename();
        final String uuid = UUID.randomUUID().toString();
        String fileName = uuid.replaceAll("-","");
        String fileSuffix = fileOriginalName.substring(fileOriginalName.lastIndexOf('.'));
        inputStream = file.getInputStream();
        String fileNamePath = filePath + fileName + fileSuffix;
        outputStream = new FileOutputStream(fileNamePath);
        byte[] bytes = new byte[2048];
        while ((inputStream.read(bytes)) != -1) {
            outputStream.write(bytes);
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.flush();;
            outputStream.close();
        }
        return fileNamePath;
    }

    /**
     * 读取文件，并通过输出流输出
     * @param filePath
     * @param outputStream
     * @return
     */
    public static void readLocalFile(String filePath, OutputStream outputStream) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] content = new byte[2048];
            while((inputStream.read(content) != -1)) {
                outputStream.write(content);
            }
        } catch (FileNotFoundException e) {
            log.error("class: LocalFileUtil, method:readLocalFile, errorInfo: ", e);
        } catch (IOException e) {
            log.error("class: LocalFileUtil, method:readLocalFile, errorInfo: ", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("class: LocalFileUtil, method:readLocalFile, errorInfo: ", e);
                }
            }
        }
    }

    /**
     * 删除文件
     * @param path 文件路径
     * @return 返回删除结果
     */
    public static String deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        } else {
            return "文件不存在";
        }
        return null;
    }
}
