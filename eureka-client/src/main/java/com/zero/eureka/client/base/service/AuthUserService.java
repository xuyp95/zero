package com.zero.eureka.client.base.service;

import com.zero.eureka.client.base.vo.AuthUserVO;
import com.zero.eureka.client.core.vo.PageVO;

/**
 * @Author:xuyp
 * @Date:2018/8/27 22:56
 */
public interface AuthUserService {

    /**
     * 分页查询
     * @param page
     * @return
     */
    PageVO queryByPage(PageVO<AuthUserVO> page);

    /**
     * 保存对象
     * @param authUserVO
     */
    void save(AuthUserVO authUserVO);

    /**
     * 根据id查找对象
     * @param id
     * @return
     */
    AuthUserVO findById(String id);

    /**
     * 根据id删除对象
     * @param ids
     */
    void delete(String ids);
}
