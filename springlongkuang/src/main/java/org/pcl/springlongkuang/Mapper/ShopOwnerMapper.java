package org.pcl.springlongkuang.Mapper;

import org.pcl.springlongkuang.Model.ShopOwner;

public interface ShopOwnerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopOwner record);

    int insertSelective(ShopOwner record);

    ShopOwner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopOwner record);

    int updateByPrimaryKey(ShopOwner record);
}