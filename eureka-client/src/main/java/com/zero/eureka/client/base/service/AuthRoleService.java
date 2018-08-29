package com.zero.eureka.client.base.service;

import com.zero.eureka.client.base.model.AuthRole;
import com.zero.eureka.client.base.vo.AuthRoleVO;
import com.zero.eureka.client.core.vo.PageVO;

import java.util.Map;

/**
 * 角色业务处理
 * @Author:xuyp
 * @Date:2018/8/29 0:08
 */
public interface AuthRoleService {

    /**
     * 保存对象
     * @param authRole
     */
    AuthRoleVO save(AuthRoleVO authRoleVO);

    /**
     * 分页查询列表数据
     * @param page
     * @return
     */
    PageVO<AuthRoleVO> queryPage(PageVO<AuthRoleVO> page);

    /**
     * 编辑数据处理
     * @param id
     * @return
     */
    Map<String,Object> edit(String id);

    /**
     * 根据ids删除对象
     * @param ids
     */
    void delete(String ids);
}
