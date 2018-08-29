package com.zero.eureka.client.base.service.impl;

import com.zero.eureka.client.base.model.AuthRole;
import com.zero.eureka.client.base.repository.AuthRoleRepository;
import com.zero.eureka.client.base.service.AuthRoleService;
import com.zero.eureka.client.base.vo.AuthRoleVO;
import com.zero.eureka.client.core.exception.ZeroException;
import com.zero.eureka.client.core.vo.PageVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 角色业务
 * @Author:xuyp
 * @Date:2018/8/29 0:09
 */
@Service
@Transactional
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private AuthRoleRepository roleRepository;

    @Override
    public AuthRoleVO save(AuthRoleVO authRoleVO) {
        AuthRole authRole = new AuthRole();
        authRole.setId(authRoleVO.getId());
        authRole.setName(authRoleVO.getName());
        authRole.setDescription(authRoleVO.getDescription());
        authRole.setCreateTime(LocalDateTime.now());
        authRole.setUpdateTime(LocalDateTime.now());
        authRole = roleRepository.save(authRole);
        BeanUtils.copyProperties(authRole, authRoleVO);
        return authRoleVO;
    }

    @Override
    public PageVO<AuthRoleVO> queryPage(PageVO<AuthRoleVO> page) {

        // 根据更新时间来排序
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(page.getPageNo(), page.getPageSize(), sort);
        // 条件分页查询
        Page<AuthRole>  retPage = roleRepository.findAll((root, query, cb) -> {
            if (page.getVo() != null && StringUtils.isNotBlank(page.getVo().getName())) {
                return cb.like(root.get("name"), page.getVo().getName());
            }
            return null;
        }, pageable);
        // 解析结果集
        List<AuthRoleVO> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(retPage.getContent())) {
            retPage.getContent().forEach(a -> {
                AuthRoleVO vo = new AuthRoleVO();
                BeanUtils.copyProperties(a, vo);
                voList.add(vo);
            });
        }
        // 处理返回值
        PageVO<AuthRoleVO> pageVO = new PageVO<>();
        pageVO.setRows(voList);
        pageVO.setTotal((long)voList.size());

        return pageVO;
    }

    @Override
    public Map<String, Object> edit(String id) {
        Map<String, Object> paramMap = new HashMap<>();
        AuthRoleVO vo = new AuthRoleVO();
        if (StringUtils.isNotBlank(id)) {
            Optional<AuthRole> optional = roleRepository.findById(id);
            if (optional.isPresent()) {
                BeanUtils.copyProperties(optional.get(), vo);
            }
        }
        paramMap.put("authRole", vo);
        return paramMap;
    }

    @Override
    public void delete(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++) {
                String id = idArr[i];
                // 为什么不直接调用 repository.deleteById(id), 因为该方法如果不存在对象会抛出异常
                Optional<AuthRole> optional = roleRepository.findById(id);
                if (optional.isPresent()) {
                    roleRepository.delete(optional.get());
                }
            }
        } else {
            throw new ZeroException("400", "删除对象不存在");
        }
    }
}
