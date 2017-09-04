package com.tangcheng.zhiban.sns.todo.service.biz;

import com.tangcheng.zhiban.sns.todo.dao.biz.TodoDetailRepository;
import com.tangcheng.zhiban.sns.todo.domain.global.PageData;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsTodoDetailDO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailListReqVO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailReqVO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoSearchReqVO;
import com.tangcheng.zhiban.sns.todo.domain.res.TodoDetailResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PageData<TodoDetailResVO> list(TodoDetailListReqVO todoDetailListReqVO) {

        List<TodoDetailResVO> detailResVOList = listWeb(todoDetailListReqVO);
        PageData<TodoDetailResVO> pageData = new PageData<>(detailResVOList, todoDetailListReqVO);
        if (todoDetailListReqVO.getNeedTotal()) {
            pageData.setTotal(todoDetailRepository.count(todoDetailListReqVO.getFinished()));
        }
        return pageData;
    }

    @Override
    public List<TodoDetailResVO> listWeb(TodoDetailListReqVO todoDetailListReqVO) {
        Boolean finished = todoDetailListReqVO.getFinished();
        List<SnsTodoDetailDO> detailDOList = todoDetailRepository.list(finished, todoDetailListReqVO.getPageNum(), todoDetailListReqVO.getPageSize());

        List<TodoDetailResVO> detailResVOList = new ArrayList<>(detailDOList.size());

        for (SnsTodoDetailDO snsTodoDetailDO : detailDOList) {
            TodoDetailResVO resVO = new TodoDetailResVO();
            BeanUtils.copyProperties(snsTodoDetailDO, resVO);
            detailResVOList.add(resVO);
        }
        return detailResVOList;
    }

    @Override
    public TodoDetailResVO get(Long id) {
        SnsTodoDetailDO snsTodoDetailDO = todoDetailRepository.get(id);
        if (snsTodoDetailDO == null) {
            return null;
        }
        TodoDetailResVO resVO = new TodoDetailResVO();
        BeanUtils.copyProperties(snsTodoDetailDO, resVO);
        return resVO;
    }

    @Override
    public void update(Long todoId, TodoDetailReqVO todoDetailReqVO) {
        todoDetailRepository.update(todoId, todoDetailReqVO);
    }

    @Override
    public void remove(Long id) {
        todoDetailRepository.remove(id);
    }

    @Override
    public List<TodoDetailResVO> search(TodoSearchReqVO searchReqVO) {
        return todoDetailRepository.search(searchReqVO.getKey(), searchReqVO.getPageNum(), searchReqVO.getPageSize());
    }


}
