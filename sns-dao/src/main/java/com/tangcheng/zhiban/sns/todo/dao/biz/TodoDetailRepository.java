package com.tangcheng.zhiban.sns.todo.dao.biz;


import com.tangcheng.zhiban.sns.todo.core.util.NetworkUtil;
import com.tangcheng.zhiban.sns.todo.domain.constant.Flag;
import com.tangcheng.zhiban.sns.todo.domain.mapper.SnsTodoDetailDOMapper;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsTodoDetailDO;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsTodoDetailDOExample;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailReqVO;
import com.tangcheng.zhiban.sns.todo.domain.res.TodoDetailResVO;
import com.tangcheng.zhiban.sns.todo.domain.util.SecurityUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangcheng on 8/27/2017.
 */
@Repository
public class TodoDetailRepository {
    @Autowired
    private SnsTodoDetailDOMapper snsTodoDetailDOMapper;

    public Long save(TodoDetailReqVO todoDetailReqVO) {
        SnsTodoDetailDO snsTodoDetailDO = new SnsTodoDetailDO();
        BeanUtils.copyProperties(todoDetailReqVO, snsTodoDetailDO);

        snsTodoDetailDO.setUserName(SecurityUtil.getUserName());
        snsTodoDetailDO.setCreateIp(NetworkUtil.getRemoteIp());
        snsTodoDetailDO.setFinished(false);
        Date now = new Date();
        snsTodoDetailDO.setCreateDate(now);
        snsTodoDetailDO.setCreateTime(now);
        snsTodoDetailDO.setUpdateTime(now);
        snsTodoDetailDO.setStatus(Flag.UniversalFlag.NORMAL);

        snsTodoDetailDOMapper.insertUseGeneratedKeys(snsTodoDetailDO);
        return snsTodoDetailDO.getId();
    }


    public void finish(Long id) {
        SnsTodoDetailDO record = new SnsTodoDetailDO();
        record.setId(id);
        record.setFinished(true);
        record.setFinishTime(new Date());
        record.setFinishIp(NetworkUtil.getRemoteIp());
        snsTodoDetailDOMapper.updateByPrimaryKeySelective(record);
    }


    public List<SnsTodoDetailDO> list(Boolean finished, Integer pageNum, Integer pageSize) {
        SnsTodoDetailDOExample example = new SnsTodoDetailDOExample();
        SnsTodoDetailDOExample.Criteria criteria = example.createCriteria();
        if (finished == null) {
        } else if (finished) {
            criteria.andFinishedEqualTo(true);
        } else {
            criteria.andFinishedEqualTo(false);
        }
        criteria.andStatusEqualTo(Flag.UniversalFlag.NORMAL);

        example.setOrderByClause(" weight desc,create_time desc ");

        return snsTodoDetailDOMapper.selectByExampleAndRowBounds(example, new RowBounds(pageNum, pageSize));
    }


    public long count(Boolean finished) {
        SnsTodoDetailDOExample example = new SnsTodoDetailDOExample();
        SnsTodoDetailDOExample.Criteria criteria = example.createCriteria();
        if (finished == null) {
        } else if (finished) {
            criteria.andFinishedEqualTo(true);
        } else {
            criteria.andFinishedEqualTo(false);
        }

        return snsTodoDetailDOMapper.selectCountByExample(example);
    }

    public SnsTodoDetailDO get(Long todoId) {
        return snsTodoDetailDOMapper.selectByPrimaryKey(todoId);
    }

    public void update(Long todoId, TodoDetailReqVO todoDetailReqVO) {
        SnsTodoDetailDO record = new SnsTodoDetailDO();
        BeanUtils.copyProperties(todoDetailReqVO, record);
        record.setUpdateTime(new Date());
        record.setUpdateIp(NetworkUtil.getRemoteIp());

        SnsTodoDetailDOExample example = new SnsTodoDetailDOExample();
        example.createCriteria().andIdEqualTo(todoId)
                .andStatusEqualTo(Flag.UniversalFlag.NORMAL)
                .andFinishedEqualTo(false);
        snsTodoDetailDOMapper.updateByExampleSelective(record, example);
    }


    public void remove(Long todoId) {
        SnsTodoDetailDO record = new SnsTodoDetailDO();
        record.setStatus(Flag.UniversalFlag.DELETE);
        record.setUpdateTime(new Date());
        record.setUpdateIp(NetworkUtil.getRemoteIp());

        SnsTodoDetailDOExample example = new SnsTodoDetailDOExample();
        example.createCriteria().andIdEqualTo(todoId);
        snsTodoDetailDOMapper.updateByExampleSelective(record, example);
    }

    public List<TodoDetailResVO> search(String key, Integer pageNum, Integer pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("key", "%" + key + "%");
        return snsTodoDetailDOMapper.search(params, new RowBounds(pageNum, pageSize));
    }
}
