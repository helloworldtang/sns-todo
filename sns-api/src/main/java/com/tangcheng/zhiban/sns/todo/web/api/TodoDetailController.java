package com.tangcheng.zhiban.sns.todo.web.api;

import com.tangcheng.zhiban.sns.todo.domain.global.GlobalCode;
import com.tangcheng.zhiban.sns.todo.domain.global.PageData;
import com.tangcheng.zhiban.sns.todo.domain.global.ResultData;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailListReqVO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailReqVO;
import com.tangcheng.zhiban.sns.todo.domain.res.TodoDetailResVO;
import com.tangcheng.zhiban.sns.todo.service.biz.TodoDetailService;
import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by tangcheng on 8/26/2017.
 */
@Api(tags = "TodoContent", description = "todoContent")
@RestController
@RequestMapping(ApiVersion.API_V1 + "/todo")
public class TodoDetailController {

    @Autowired
    private TodoDetailService todoDetailService;

    @PostMapping
    public ResultData<Long> save(@Valid TodoDetailReqVO todoDetailReqVO) {
        return new ResultData<Long>(todoDetailService.save(todoDetailReqVO));
    }

    @PutMapping("{id}")
    public ResultData<String> finish(@PathVariable Long id) {
        todoDetailService.finish(id);
        return new ResultData<>(GlobalCode.SUCCESS);
    }

    @GetMapping
    public ResultData<PageData<TodoDetailResVO>> list(TodoDetailListReqVO todoDetailListReqVO) {
        PageData<TodoDetailResVO> pageData = todoDetailService.list(todoDetailListReqVO);
        return new ResultData<>(pageData);
    }


}
