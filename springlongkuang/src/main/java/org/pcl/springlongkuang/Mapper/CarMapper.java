package org.pcl.springlongkuang.Mapper;

import org.apache.ibatis.annotations.Param;
import org.pcl.springlongkuang.Model.Car;

import java.util.List;

public interface CarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer id);

    int selectByKeyWord(String keyWord);
    List<Car> selectBykeyWords(String keyWord, Integer limit, Integer offset, String sortCondition, String sortRule);

    List<Car> selectAll(String keyWord,@Param("order_by") String order_by,@Param("rule") String rule);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);
}