package com.tangcheng.zhiban.sns.todo.domain.mapper;

import com.tangcheng.zhiban.sns.todo.domain.model.SnsTodoDetailDO;
import com.tangcheng.zhiban.sns.todo.domain.res.TodoDetailResVO;
import com.tangcheng.zhiban.sns.todo.domain.util.MyMapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface SnsTodoDetailDOMapper extends MyMapper<SnsTodoDetailDO> {
    List<TodoDetailResVO> search(Map<String, Object> params, RowBounds rowBounds);
}