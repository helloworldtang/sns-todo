package com.tangcheng.zhiban.sns.todo.domain.global;

import java.util.List;

/**
 * Created by tangcheng on 8/26/2017.
 */
public class PageData<T> {
    private boolean hasNext = false;
    private List<T> items;
    private int total;

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
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
