package com.tangcheng.zhiban.sns.todo.domain.global;

import com.tangcheng.zhiban.sns.todo.domain.req.PageVO;

import java.util.List;

/**
 * Created by tangcheng on 8/26/2017.
 */
public class PageData<T> {
    private Integer pageNum;
    private Integer pageSize;
    private boolean hasNext = false;
    private List<T> items;
    private long total;

    public PageData(List<T> detailResVOList, PageVO pageVO) {
        this.items = detailResVOList;
        this.pageNum = pageVO.getPageNum();
        this.pageSize = pageVO.getPageSize();
    }

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

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        if (items == null) {
            return;
        }
        this.items = items;
        if (items.size() == pageSize) {
            this.hasNext = true;
        }
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
