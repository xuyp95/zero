package com.zero.eureka.client.base.repository;

import com.zero.eureka.client.base.model.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 角色数据库交互操作
 * @Author:xuyp
 * @Date:2018/8/29 0:06
 */
public interface AuthRoleRepository extends JpaRepository<AuthRole, String>,
        JpaSpecificationExecutor<AuthRole> {
}
