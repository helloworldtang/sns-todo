package com.tangcheng.zhiban.sns.todo.domain.req;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Created by tangcheng on 8/26/2017.
 *
 * @ApiModelProperty value值会显示在swagger Parameters 中的第三列Description
 * 效果如下图所示
 * http://images2017.cnblogs.com/blog/280044/201712/280044-20171218155806693-1915989979.png
 */
public class TodoDetailReqVO {

    private Long categoryId = 1L;

    @Min(1)
    @ApiModelProperty(required = true, value = "权重")
    private Integer weight = 1;

    @NotEmpty
    @Size(min = 2, max = 200)
    @ApiModelProperty(required = true, value = "摘要")
    private String digest;

    @Size(max = 1000)
    @ApiModelProperty(required = true, value = "详细内容")
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
