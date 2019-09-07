package org.pcl.springlongkuang.Mapper;

import org.pcl.springlongkuang.Model.Exception;
import org.springframework.transaction.annotation.Transactional;

public interface ExceptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Exception record);

    @Transactional
    int insertSelective(Exception record);

    Exception selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Exception record);

    int updateByPrimaryKey(Exception record);
}