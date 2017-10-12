package com.fengyu.system.model;

import java.util.List;

/**
 * bootstrap-table 分页参数
 *  构造方法慎重删除, 其中有改动
 *  rows,total 是格式要求
 * Created by 韩峰 on 2016/8/15.
 */
public class BootPage<T> {


    /**
     * 结果集总计大小
     */
    private Long total;

    /**
     * 数据行(结果集)
     */
    private List<T> rows;

    /**
     * 分页大小
     */
    private int limit ;

    /**
     * 当前页码
     */
    private Integer pageNumber;

    /**
     * 排序列名
     */
    private String sortName ;

    /**
     * 排序方式
     */
    private String sortOrder ="desc" ;

    /**
     * 查询字符串
     * @return
     */
    private String searchText;

    /*
     * 构造方法,装入分页结果集
     * @param limit         分页大小
     * @param pageNumber    当前页码
     * @param searchText    查询字符串
     * @param sortOrder     排序方式
     * @param sortName      排序列名
     * @param page          查询结果集(Pageable查询的结果)
     */
    // public BootPage (int limit,int pageNumber,String searchText,String sortOrder,String sortName
    //     ,Page page) {
    //     this.setTotal(page.getTotalElements());
    //     this.setRows(page.getContent());
    //     this.setSearchText(searchText);
    //     this.setLimit(limit);
    //     this.setPageNumber(pageNumber);
    //     this.setSortOrder(sortOrder);
    //     this.setSortName(sortName);
    // }


    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        //spring data jpa 分页类pageable 分页参数是0开始
        if(pageNumber > 0){
            pageNumber = pageNumber - 1;
        }
        this.pageNumber = pageNumber;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
