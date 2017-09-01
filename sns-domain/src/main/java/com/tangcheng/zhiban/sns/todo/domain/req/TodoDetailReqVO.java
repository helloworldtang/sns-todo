package com.tangcheng.zhiban.sns.todo.domain.req;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by tangcheng on 8/26/2017.
 */
public class TodoDetailReqVO {

    private Long categoryId=1L;

    @Min(1)
    private Integer weight=1;

    @NotEmpty
    @Size(min = 2, max = 200)
    private String digest;

    @Size(max = 1000)
    private String content;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
