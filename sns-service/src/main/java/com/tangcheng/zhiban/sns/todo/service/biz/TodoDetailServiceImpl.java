package com.tangcheng.zhiban.sns.todo.service.biz;

import com.tangcheng.zhiban.sns.todo.dao.biz.TodoDetailRepository;
import com.tangcheng.zhiban.sns.todo.domain.global.PageData;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsTodoDetailDO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailListReqVO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailReqVO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoSearchReqVO;
import com.tangcheng.zhiban.sns.todo.domain.res.TodoDetailResVO;
import com.tangcheng.zhiban.sns.todo.domain.util.SecurityUtil;
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
            String userName = SecurityUtil.getUserName();
            pageData.setTotal(todoDetailRepository.count(userName, todoDetailListReqVO.getFinished()));
        }
        return pageData;
    }

    @Override
    public List<TodoDetailResVO> listWeb(TodoDetailListReqVO todoDetailListReqVO) {
        Boolean finished = todoDetailListReqVO.getFinished();
        Integer pageNum = todoDetailListReqVO.getPageNum();
        Integer pageSize = todoDetailListReqVO.getPageSize();
        String userName = SecurityUtil.getUserName();
        List<SnsTodoDetailDO> detailDOList = todoDetailRepository.list(userName, finished, pageNum, pageSize);

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
        String userName = SecurityUtil.getUserName();
        SnsTodoDetailDO snsTodoDetailDO = todoDetailRepository.get(id, userName);
        if (snsTodoDetailDO == null) {
            return null;
        }
        TodoDetailResVO resVO = new TodoDetailResVO();
        BeanUtils.copyProperties(snsTodoDetailDO, resVO);
        return resVO;
    }

    @Override
    public void update(Long todoId, TodoDetailReqVO todoDetailReqVO) {
        String userName = SecurityUtil.getUserName();
        todoDetailRepository.update(userName, todoId, todoDetailReqVO);
    }

    @Override
    public void remove(Long id) {
        String userName = SecurityUtil.getUserName();
        todoDetailRepository.remove(userName, id);
    }

    @Override
    public List<TodoDetailResVO> search(TodoSearchReqVO searchReqVO) {
        String userName = SecurityUtil.getUserName();
        String key = searchReqVO.getKey();
        Integer pageNum = searchReqVO.getPageNum();
        Integer pageSize = searchReqVO.getPageSize();
        return todoDetailRepository.search(userName, key, pageNum, pageSize);
    }


}
