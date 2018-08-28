package com.zero.eureka.client.base.service.impl;

import com.netflix.discovery.converters.Auto;
import com.zero.eureka.client.base.model.AuthUser;
import com.zero.eureka.client.base.repository.AuthUserRepository;
import com.zero.eureka.client.base.service.AuthUserService;
import com.zero.eureka.client.base.vo.AuthUserVO;
import com.zero.eureka.client.core.exception.ZeroException;
import com.zero.eureka.client.core.util.MD5Util;
import com.zero.eureka.client.core.vo.PageVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 用户业务处理
 * @Author:xuyp
 * @Date:2018/8/27 22:57
 */
@Transactional
@Service
public class AuthUserServiceImpl implements AuthUserService {

    private static final Logger log = LoggerFactory.getLogger(AuthUserServiceImpl.class);

    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    public PageVO queryByPage(PageVO<AuthUserVO> page) {
        // 排序信息封装
        Sort sort = getSort(page);
        // 分页信息封装
        Pageable pageable = PageRequest.of(page.getPageNo(), page.getPageSize(), sort);
        // 查询
        Page<AuthUser> findPage = authUserRepository.findAll((root, query, cb) -> {
            AuthUserVO vo = page.getVo();
            if (vo != null && StringUtils.isNotBlank(vo.getName())) {
                return cb.equal(root.get("name"), vo.getName());
            }
            return null;
        }, pageable);
        // 处理响应数据
        List<AuthUserVO> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(findPage.getContent())) {
            findPage.forEach(a -> {
                AuthUserVO vo = new AuthUserVO();
                try {
                    BeanUtils.copyProperties(vo, a);
                    voList.add(vo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        }

        PageVO<AuthUserVO> resultPage = new PageVO<>();
        resultPage.setTotal((long)voList.size());
        resultPage.setRows(voList);
        return resultPage;
    }

    @Override
    public void save(AuthUserVO authUserVO) {
        AuthUser user = new AuthUser();
        try {
            BeanUtils.copyProperties(user, authUserVO);
            // 对密码进行加盐处理
            String pwd = authUserVO.getPassword();
            String salt = UUID.randomUUID().toString();
            String password = MD5Util.shiroEncrypt(pwd, salt);
            user.setPassword(password);
            user.setSalt(salt);
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            authUserRepository.save(user);
        } catch (IllegalAccessException e) {
            log.error("copy properties error", e);
            throw new ZeroException("400", "数据处理异常");
        } catch (InvocationTargetException e) {
            log.error("copy properties error", e);
            throw new ZeroException("400", "数据处理异常");
        }
    }

    @Override
    public AuthUserVO findById(String id) {
        AuthUserVO vo = new AuthUserVO();
        Optional<AuthUser> optional = authUserRepository.findById(id);
        if (optional.isPresent()) {
            try {
                BeanUtils.copyProperties(vo, optional.get());
            } catch (IllegalAccessException e) {
                log.error("", e);
//                throw new ZeroException("400", "读取数据异常");
            } catch (InvocationTargetException e) {
                log.error("", e);
//                throw new ZeroException("400", "读取数据异常");
            }
        }
        return vo;
    }

    @Override
    public void delete(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                Optional<AuthUser> optional = authUserRepository.findById(id);
                if (optional.isPresent()) {
                    authUserRepository.delete(optional.get());
                }
            }
        } else {
            throw new ZeroException("200", "id is null");
        }
    }


    /**
     * 获取查询的排序对象
     * @param page
     * @return
     */
    private Sort getSort(PageVO<AuthUserVO> page) {
        String sortFiledName = "createTime";
        Sort.Direction direction = Sort.Direction.DESC;
        if (StringUtils.isNotBlank(page.getSortProperty())) {
            sortFiledName = page.getSortProperty();
        }
        if (page.getSortType() != null && Sort.Direction.ASC.equals(Sort.Direction.valueOf(page.getSortType()))) {
            direction = Sort.Direction.ASC;
        }
        Sort sort = new Sort(direction, sortFiledName);
        return sort;
    }
}
