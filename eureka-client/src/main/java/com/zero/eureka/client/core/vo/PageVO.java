package com.zero.eureka.client.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查询时接收相关参数
 * @Author:xuyp
 * @Date:2018/6/20 23:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<VO> {

    /**
     * 页长
     */
    private Integer pageSize;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 排序字段
     */
    private String sortProperty;

    /**
     * 排序类型
     */
    private String sortType;

    /**
     * 查询列表总数
     */
    private Long total;

    /**
     * 列表数据
     */
    private List<?> rows;

    private VO vo;
}
