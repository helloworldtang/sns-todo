package com.tangcheng.zhiban.sns.todo.domain.req;

/**
 * Created by tangcheng on 8/27/2017.
 */
public class TodoDetailListReqVO extends PageVO {
    private Boolean finished;

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
