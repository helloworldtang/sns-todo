package com.tangcheng.zhiban.sns.todo.domain.req;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by tangcheng on 8/26/2017.
 */
public class TodoDetailReqVO {

    @NotNull
    private Long type = 1L;

    @NotEmpty
    @Size(min = 2, max = 200)
    private String digest;

    @Size(max = 1000)
    private String content;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
