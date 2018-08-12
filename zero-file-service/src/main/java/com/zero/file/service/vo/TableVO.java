package com.zero.file.service.vo;

import java.util.List;

/**
 * 使用bootstrap-table返回值格式是固定的，所以新设计vo来配合
 * @Author:xuyp
 * @Date:2018/6/12 23:48
 */
public class TableVO {

    /**
     * 分页查询页数
     */
    private Integer pageNo;

    /**
     * 分页查询页长
     */
    private Integer pageSize;

    /**
     * 分页查询，排序类型。desc, asc
     */
    private String order;

    /**
     * 分页查询字段
     */
    private String orderField;

    /**
     * 查询列表总数
     */
    private Long total;

    /**
     * 列表数据
     */
    private List<?> rows;

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return this.rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderField() {
        return this.orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    @Override
    public String toString() {
        return "ListVO{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
