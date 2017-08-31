package com.tangcheng.zhiban.sns.todo.dao.biz;


import com.tangcheng.zhiban.sns.todo.core.util.NetworkUtil;
import com.tangcheng.zhiban.sns.todo.domain.constant.Flag;
import com.tangcheng.zhiban.sns.todo.domain.mapper.SnsTodoDetailDOMapper;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsTodoDetailDO;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsTodoDetailDOExample;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailReqVO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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

        snsTodoDetailDO.setCreateIp(NetworkUtil.getRemoteIp());
        snsTodoDetailDO.setFinished(false);
        Date now = new Date();
        snsTodoDetailDO.setCreateDate(now);
        snsTodoDetailDO.setUpdateTime(now);
        snsTodoDetailDO.setCreateDate(now);
        snsTodoDetailDO.setStatus(Flag.UniversalFlag.NORMAL);

        snsTodoDetailDOMapper.insertUseGeneratedKeys(snsTodoDetailDO);
        return snsTodoDetailDO.getId();
    }


    public void finish(Long id) {
        SnsTodoDetailDO record = new SnsTodoDetailDO();
        record.setId(id);
        record.setFinished(true);
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

        example.setOrderByClause(" type desc,create_time desc ");

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
}
