package com.tangcheng.zhiban.sns.todo.service.biz;

import com.tangcheng.zhiban.sns.todo.domain.global.BizError;
import com.tangcheng.zhiban.sns.todo.domain.global.PageData;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailListReqVO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailReqVO;
import com.tangcheng.zhiban.sns.todo.domain.res.TodoDetailResVO;

/**
 * Created by tangcheng on 8/26/2017.
 */
public interface TodoDetailService {
    Long save(TodoDetailReqVO todoDetailReqVO);

    void finish(Long id);

    PageData<TodoDetailResVO> list(TodoDetailListReqVO todoDetailListReqVO);

}
