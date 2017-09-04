package com.tangcheng.zhiban.sns.todo.service.biz;

import com.tangcheng.zhiban.sns.todo.domain.global.PageData;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailListReqVO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailReqVO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoSearchReqVO;
import com.tangcheng.zhiban.sns.todo.domain.res.TodoDetailResVO;

import java.util.List;

/**
 * Created by tangcheng on 8/26/2017.
 */
public interface TodoDetailService {
    Long save(TodoDetailReqVO todoDetailReqVO);

    void finish(Long id);

    PageData<TodoDetailResVO> list(TodoDetailListReqVO todoDetailListReqVO);

    List<TodoDetailResVO> listWeb(TodoDetailListReqVO todoDetailListReqVO);

    TodoDetailResVO get(Long id);

    void update(Long todoId, TodoDetailReqVO todoDetailReqVO);

    void remove(Long id);

    List<TodoDetailResVO> search(TodoSearchReqVO searchReqVO);
}
