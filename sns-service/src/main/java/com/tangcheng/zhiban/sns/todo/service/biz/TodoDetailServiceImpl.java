package com.tangcheng.zhiban.sns.todo.service.biz;

import com.tangcheng.zhiban.sns.todo.dao.biz.TodoDetailRepository;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tangcheng on 8/26/2017.
 */
@Service
public class TodoDetailServiceImpl implements TodoDetailService {

    @Autowired
    private TodoDetailRepository todoDetailRepository;

    @Override
    public Long save(TodoDetailReqVO todoDetailReqVO) {
        return todoDetailRepository.save(todoDetailReqVO);
    }

    @Override
    public void finish(Long id) {
        todoDetailRepository.finish(id);
    }

}
