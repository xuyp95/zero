package com.zero.eureka.client.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户信息VO
 * @Author:xuyp
 * @Date:2018/8/26 10:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserVO implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 登录名
     */
    @NotBlank(message = "用户名/密码不能为空") // 会去除首尾空格
    @NotNull(message = "用户名/密码不能为空")
    @Length(min = 6, max = 128, message = "用户名长度必须在{min}-{max}之间")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "用户名/密码不能为空")
    @NotNull(message = "用户名/密码不能为空")
    @Length(min = 8, max = 128, message = "密码长度必须在{min}-{max}之间")
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 盐
     */
    private String salt;
}
