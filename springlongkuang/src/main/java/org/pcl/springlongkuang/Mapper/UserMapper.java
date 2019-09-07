package org.pcl.springlongkuang.Mapper;

import org.pcl.springlongkuang.Model.User;
import org.pcl.springlongkuang.Model.UserInfo;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    UserInfo GetUserByID (Integer id);

    UserInfo GetUser (String openId);

    User GetUserInfo(String openId);

    int InsertUser(User user);

    int Bind(User user);

    User Check(String openId);
}