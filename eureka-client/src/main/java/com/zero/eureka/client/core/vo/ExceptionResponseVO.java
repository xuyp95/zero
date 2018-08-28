package com.zero.eureka.client.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 异常响应VO
 * @Author:xuyp
 * @Date:2018/8/26 9:47
 */
@Data // @Data 包含了GETTER SETTER toString
@NoArgsConstructor // 空参数的构造函数
@AllArgsConstructor // 全参的构造函数
public class ExceptionResponseVO {

    private String code;

    private String message;
}
