package org.pcl.springlongkuang.Mapper;

import org.pcl.springlongkuang.Model.Shop;

import java.util.List;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    Shop selectByShopName(String shopName);

    Shop selectByOwnerId(Integer ownerId);

    int selectByKeyWord(String keyWord);

    List<Shop> selectBykeyWords(String keyWord,Integer limit,Integer offset,String sortCondition,String sortRule);

}