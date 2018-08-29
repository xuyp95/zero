package com.zero.eureka.client.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author:xuyp
 * @Date:2018/8/29 22:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRoleVO {

    /**
     * 主键
     */
    private String id;

    /**
     * 角色名
     */
    @NotNull(message = "角色名称不能为空")
    @NotBlank(message = "角色名称不能为空")
    @Length(min = 6, max = 128, message = "角色名称长度必须在{min}-{max}之间")
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
