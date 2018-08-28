package com.zero.eureka.client.core.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

/**
 * 结合数据校验 @Validated 和 @Controller 两个注解的作用
 * @Author:xuyp
 * @Date:2018/8/26 10:40
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@Validated
public @interface ValidateController {
    @AliasFor(
            annotation = Controller.class
    )
    String value() default "";
}
