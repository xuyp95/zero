package com.zero.eureka.client.base.controller;

import com.zero.eureka.client.base.service.AuthRoleService;
import com.zero.eureka.client.base.vo.AuthRoleVO;
import com.zero.eureka.client.core.annotation.ValidateController;
import com.zero.eureka.client.core.vo.PageVO;
import com.zero.eureka.client.core.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 角色管理
 * @Author:xuyp
 * @Date:2018/8/29 21:20
 */
@ValidateController
@RequestMapping("role")
public class AuthRoleController {

    @Autowired
    private AuthRoleService authRoleService;

    /**
     * 列表
     * @param modelAndView
     * @return
     */
    @GetMapping("list")
    public ModelAndView list(ModelAndView modelAndView) {
        modelAndView.setViewName("auth/role/listAuthRole");
        return modelAndView;
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("queryPage")
    @ResponseBody
    public PageVO<AuthRoleVO> queryPage(PageVO<AuthRoleVO> page) {
        PageVO<AuthRoleVO> pageVo = authRoleService.queryPage(page);
        return pageVo;
    }

    /**
     * 编辑
     * @param id
     * @param modelAndView
     * @return
     */
    @GetMapping("edit")
    public ModelAndView edit(String id, ModelAndView modelAndView) {
        Map<String, Object> paramMap = authRoleService.edit(id);
        modelAndView.addAllObjects(paramMap);
        modelAndView.setViewName("auth/role/editAuthRole");
        return modelAndView;
    }

    /**
     * 保存对象
     * @param authRoleVO
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    public ResponseVO save(@Validated AuthRoleVO authRoleVO) {
        authRoleService.save(authRoleVO);
        return new ResponseVO();
    }

    /**
     * 删除对象
     * @param ids
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public ResponseVO delete(String ids) {
        authRoleService.delete(ids);
        return new ResponseVO();
    }
}
