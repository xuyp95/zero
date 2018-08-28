package com.zero.eureka.client.base.repository;

import com.zero.eureka.client.base.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * AuthUser 数据处理
 * @Author:xuyp
 * @Date:2018/8/27 23:15
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, String>, JpaSpecificationExecutor<AuthUser> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM auth_user WHERE id in (?1)", nativeQuery = true)
    void deleteByIds(String ids);

    @Transactional
    @Modifying
    @Query("DELETE FROM AuthUser u WHERE u.id in (?1)")
    void deleteByIds(Collection<String> ids);
}
