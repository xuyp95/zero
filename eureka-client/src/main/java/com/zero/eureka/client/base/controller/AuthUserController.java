package com.zero.eureka.client.base.controller;

import com.zero.eureka.client.base.model.AuthUser;
import com.zero.eureka.client.base.service.AuthUserService;
import com.zero.eureka.client.base.vo.AuthUserVO;
import com.zero.eureka.client.core.annotation.ValidateController;
import com.zero.eureka.client.core.vo.PageVO;
import com.zero.eureka.client.core.vo.ResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;

/**
 * 用户信息控制器
 * @Author:xuyp
 * @Date:2018/8/26 10:45
 */
@ValidateController
@RequestMapping("/user")
public class AuthUserController {

    private static final Logger log = LoggerFactory.getLogger(AuthUserController.class);

    @Autowired
    private AuthUserService authUserService;


    @PostMapping("test")
    public void test(@RequestBody @Validated AuthUserVO userVO) {
        log.info("request success. user info = {}", userVO.toString());
    }

    /**
     * 列表页面
     * @param modelAndView
     * @return
     */
    @GetMapping("list")
    public ModelAndView list(ModelAndView modelAndView) {
        modelAndView.setViewName("auth/user/listAuthUser");
        return modelAndView;
    }

    /**
     * 分页查询数据
     * @return
     */
    @PostMapping("queryPage")
    @ResponseBody
    public PageVO queryPage(PageVO<AuthUserVO> page) {
        return authUserService.queryByPage(page);
    }

    /**
     * 编辑页面
     * @param modelAndView
     * @return
     */
    @GetMapping("edit")
    public ModelAndView edit(String id, ModelAndView modelAndView) {
        AuthUserVO authUserVO = new AuthUserVO();
        if (StringUtils.isNotBlank(id)) {
            authUserVO = authUserService.findById(id);
        }
        modelAndView.setViewName("auth/user/editAuthUser");

        modelAndView.addObject("authUserVO", authUserVO);
        return modelAndView;
    }

    /**
     * 保存对象
     * @param authUserVO
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    public ResponseVO save(@Validated AuthUserVO authUserVO) {
        authUserService.save(authUserVO);
        return new ResponseVO("200", "success");
    }

    /**
     * 删除请求
     * @param ids
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public ResponseVO delete(String ids) {
        authUserService.delete(ids);
        return new ResponseVO("200", "success");
    }
}
