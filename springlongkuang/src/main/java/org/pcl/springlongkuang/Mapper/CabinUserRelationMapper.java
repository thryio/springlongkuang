package org.pcl.springlongkuang.Mapper;

import org.pcl.springlongkuang.Model.CabinUserRelation;

public interface CabinUserRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CabinUserRelation record);

    int insertSelective(CabinUserRelation record);

    CabinUserRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CabinUserRelation record);

    int updateByPrimaryKey(CabinUserRelation record);

    CabinUserRelation GetViaUserID(int UserId);
}