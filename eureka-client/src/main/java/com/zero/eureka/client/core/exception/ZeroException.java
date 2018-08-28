package com.zero.eureka.client.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常，用来处理返回错误消息
 * @Author:xuyp
 * @Date:2018/8/26 9:55
 */
@Getter
@Setter
public class ZeroException extends RuntimeException {
    /**
     * 返回状态码
     */
    private String code;

    public ZeroException() {
        super();
    }

    public ZeroException(String code, String message) {
        super(message);
        this.code = code;
    }
}
