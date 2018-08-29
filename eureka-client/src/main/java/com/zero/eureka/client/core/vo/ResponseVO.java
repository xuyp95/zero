package com.zero.eureka.client.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应前端的VO
 * @Author:xuyp
 * @Date:2018/8/27 22:34
 */
@Data
@AllArgsConstructor
public class ResponseVO {

    private String code;

    private String message;

    private Object data;

    public ResponseVO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseVO() {
        this.code = "200";
        this.message = "success";
    }
}
