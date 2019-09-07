package org.pcl.springlongkuang.Service.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcl.springlongkuang.Mapper.UserMapper;
import org.pcl.springlongkuang.Model.User;
import org.pcl.springlongkuang.Model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
@Autowired
    private UserMapper userMapper;

    @Test
    public void getUserById() {
//        UserInfo userInfo = userMapper.GetUserByID(1);
//        System.out.println(userInfo.getPermission().getId());
        UserInfo userInfo = userMapper.GetUser("o9VAC5Wl07UbAbRlZerwRoIBrhTI");
//        System.out.println(userInfo.getPermission().getId());
    }

    @Test
    public void GetUserInfo(){
        User user = userMapper.GetUserInfo("o9VAC5Wl07UbAbRlZerwRoIBrhTI");
        System.out.println(user.toString());
    }
}