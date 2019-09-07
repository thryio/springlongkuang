package org.pcl.springlongkuang.Mapper;

import org.pcl.springlongkuang.Model.Cabin;

public interface CabinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cabin record);

    int insertSelective(Cabin record);

    Cabin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cabin record);

    int updateByPrimaryKey(Cabin record);
}