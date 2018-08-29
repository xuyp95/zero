package com.zero.eureka.client.base.init;

import com.zero.eureka.client.base.model.AuthRole;
import com.zero.eureka.client.base.model.AuthUser;
import com.zero.eureka.client.base.service.AuthPermissionService;
import com.zero.eureka.client.base.service.AuthRoleService;
import com.zero.eureka.client.base.service.AuthUserService;
import com.zero.eureka.client.base.vo.AuthUserVO;
import com.zero.eureka.client.core.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 权限相关数据初始化
 * @Author:xuyp
 * @Date:2018/8/29 0:02
 */
public class AuthInit implements ApplicationRunner {

    @Autowired
    private AuthUserService userService;
    @Autowired
    private AuthRoleService roleService;
    @Autowired
    private AuthPermissionService permissionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        // 默认创建角色
//        AuthRole authRole = new AuthRole();
//        authRole.setName("admin");
//        authRole.setDescription("管理员");
//        authRole.setCreateTime(LocalDateTime.now());
//        authRole.setUpdateTime(LocalDateTime.now());
//        authRole = roleService.save(authRole);
//        // 创建默认用户
//        AuthUserVO userVO = new AuthUserVO();
//        userVO.setUserName("admin");
//        userVO.setName("超级管理员");
//        userVO.setPassword("admin");
//        List<AuthRole> roleList = new ArrayList<>(1);
//        roleList.add(authRole);
////        user.setRoleList(roleList);
//        userService.save(userVO);
    }
}
