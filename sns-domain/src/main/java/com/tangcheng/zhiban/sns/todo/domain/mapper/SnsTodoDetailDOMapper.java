package com.tangcheng.zhiban.sns.todo.domain.mapper;

import com.tangcheng.zhiban.sns.todo.domain.model.SnsTodoDetailDO;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsTodoDetailDOExample;
import com.tangcheng.zhiban.sns.todo.domain.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SnsTodoDetailDOMapper extends MyMapper<SnsTodoDetailDO> {
    int countByExample(SnsTodoDetailDOExample example);

    int deleteByExample(SnsTodoDetailDOExample example);

    List<SnsTodoDetailDO> selectByExample(SnsTodoDetailDOExample example);

    int updateByExampleSelective(@Param("record") SnsTodoDetailDO record, @Param("example") SnsTodoDetailDOExample example);

    int updateByExample(@Param("record") SnsTodoDetailDO record, @Param("example") SnsTodoDetailDOExample example);
}