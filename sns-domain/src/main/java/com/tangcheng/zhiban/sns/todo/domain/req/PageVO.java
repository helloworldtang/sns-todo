package com.tangcheng.zhiban.sns.todo.domain.req;

/**
 * Created by tangcheng on 8/27/2017.
 */
public class PageVO {
    private Integer pageNum = 1;
    private Integer pageSize = 20;
    private Boolean needTotal = false;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getNeedTotal() {
        return needTotal;
    }

    public void setNeedTotal(Boolean needTotal) {
        this.needTotal = needTotal;
    }
}
