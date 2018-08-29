package com.zero.eureka.client.base.repository;

import com.zero.eureka.client.base.model.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 权限编码数据库交互操作
 * @Author:xuyp
 * @Date:2018/8/29 0:07
 */
public interface AuthPermissionRepository extends JpaRepository<AuthPermission, String>,
        JpaSpecificationExecutor<AuthPermission>{
}
