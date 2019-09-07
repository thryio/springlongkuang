package org.pcl.springlongkuang.Mapper;

import org.pcl.springlongkuang.Model.Driver;

import java.util.List;

public interface DriverMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Driver record);

    int insertSelective(Driver record);

    Driver selectByPrimaryKey(Integer id);

    int selectByKeyWord(String keyWord);
    List<Driver> selectBykeyWords(String keyWord, Integer limit, Integer offset, String sortCondition, String sortRule);

    int updateByPrimaryKeySelective(Driver record);

    int updateByPrimaryKey(Driver record);
}