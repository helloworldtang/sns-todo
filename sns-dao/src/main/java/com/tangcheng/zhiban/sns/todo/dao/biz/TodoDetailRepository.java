package com.tangcheng.zhiban.sns.todo.dao.biz;


import com.tangcheng.zhiban.sns.todo.core.util.NetworkUtil;
import com.tangcheng.zhiban.sns.todo.core.constant.Flag;
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


    public void finish(String userName, Long id) {
        SnsTodoDetailDO record = new SnsTodoDetailDO();
        record.setFinished(true);
        record.setFinishTime(new Date());
        record.setFinishIp(NetworkUtil.getRemoteIp());

        SnsTodoDetailDOExample example = new SnsTodoDetailDOExample();
        example.createCriteria().andIdEqualTo(id)
                .andUserNameEqualTo(userName)
                .andFinishedEqualTo(false);
        snsTodoDetailDOMapper.updateByExampleSelective(record, example);
    }


    public List<SnsTodoDetailDO> list(String userName, Boolean finished, Integer pageNum, Integer pageSize) {
        SnsTodoDetailDOExample example = new SnsTodoDetailDOExample();
        SnsTodoDetailDOExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        if (finished != null) {
            if (finished) {
                criteria.andFinishedEqualTo(true);
            } else {
                criteria.andFinishedEqualTo(false);
            }
        }
        criteria.andStatusEqualTo(Flag.UniversalFlag.NORMAL);


        example.setOrderByClause(" weight desc,create_time desc ");

        return snsTodoDetailDOMapper.selectByExampleAndRowBounds(example, new RowBounds(pageNum, pageSize));
    }


    public long count(String userName, Boolean finished) {
        SnsTodoDetailDOExample example = new SnsTodoDetailDOExample();
        SnsTodoDetailDOExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        if (finished != null) {
            if (finished) {
                criteria.andFinishedEqualTo(true);
            } else {
                criteria.andFinishedEqualTo(false);
            }
        }
        return snsTodoDetailDOMapper.selectCountByExample(example);
    }

    public SnsTodoDetailDO get(Long todoId, String userName) {
        SnsTodoDetailDOExample example = new SnsTodoDetailDOExample();
        example.createCriteria().andIdEqualTo(todoId)
                .andUserNameEqualTo(userName);
        return snsTodoDetailDOMapper.selectByExample(example).stream().findAny().orElse(null);
    }

    public void update(String userName, Long todoId, TodoDetailReqVO todoDetailReqVO) {
        SnsTodoDetailDO record = new SnsTodoDetailDO();
        BeanUtils.copyProperties(todoDetailReqVO, record);
        record.setUpdateTime(new Date());
        record.setUpdateIp(NetworkUtil.getRemoteIp());

        SnsTodoDetailDOExample example = new SnsTodoDetailDOExample();
        example.createCriteria().andIdEqualTo(todoId)
                .andUserNameEqualTo(userName)
                .andStatusEqualTo(Flag.UniversalFlag.NORMAL);
        snsTodoDetailDOMapper.updateByExampleSelective(record, example);
    }


    public void remove(String userName, Long todoId) {
        SnsTodoDetailDO record = new SnsTodoDetailDO();
        record.setStatus(Flag.UniversalFlag.DELETE);
        record.setUpdateTime(new Date());
        record.setUpdateIp(NetworkUtil.getRemoteIp());

        SnsTodoDetailDOExample example = new SnsTodoDetailDOExample();
        example.createCriteria().andIdEqualTo(todoId)
                .andUserNameEqualTo(userName);
        snsTodoDetailDOMapper.updateByExampleSelective(record, example);
    }

    public List<TodoDetailResVO> search(String userName, String key, Integer pageNum, Integer pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("key", "%" + key + "%");
        params.put("userName", userName);
        return snsTodoDetailDOMapper.search(params, new RowBounds(pageNum, pageSize));
    }
}
