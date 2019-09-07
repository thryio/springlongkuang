package org.pcl.springlongkuang.Mapper;

import org.pcl.springlongkuang.Model.Container;

public interface ContainerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Container record);

    int insertSelective(Container record);

    Container selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Container record);

    int updateByPrimaryKey(Container record);
}