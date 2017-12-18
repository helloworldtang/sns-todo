package com.tangcheng.zhiban.sns.todo.domain.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@NoArgsConstructor
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

}
