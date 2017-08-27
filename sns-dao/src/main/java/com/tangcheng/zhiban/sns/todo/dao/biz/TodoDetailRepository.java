package com.tangcheng.zhiban.sns.todo.dao.biz;


import com.tangcheng.zhiban.sns.todo.domain.constant.Global;
import com.tangcheng.zhiban.sns.todo.domain.mapper.SnsTodoDetailDOMapper;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsTodoDetailDO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailReqVO;
import com.tangcheng.zhiban.sns.todo.domain.util.NetworkUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

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
        snsTodoDetailDO.setStatus(Global.GlobalStatus.NORMAL);

        snsTodoDetailDOMapper.insertUseGeneratedKeys(snsTodoDetailDO);
        return snsTodoDetailDO.getId();
    }


    public void finish(Long id) {
        SnsTodoDetailDO record = new SnsTodoDetailDO();
        record.setId(id);
        record.setFinished(true);
        snsTodoDetailDOMapper.updateByPrimaryKeySelective(record);
    }


}
