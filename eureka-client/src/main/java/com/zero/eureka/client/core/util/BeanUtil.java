package com.zero.eureka.client.core.util;

import org.springframework.beans.BeanUtils;

/**
 * @Author:xuyp
 * @Date:2018/8/29 22:58
 */
public class BeanUtil {

    /**
     * 复制属性
     * @param dest
     * @param orign
     */
    public static void copyProperties(Object dest, Object orign) {
        BeanUtils.copyProperties(orign, dest);
    }
}
