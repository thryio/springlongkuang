package org.pcl.springlongkuang.Service.Impl;

import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Mapper.DriverMapper;
import org.pcl.springlongkuang.Model.Driver;
import org.pcl.springlongkuang.Service.DriverService;
import org.pcl.springlongkuang.Utils.GetPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverMapper driverMapper;
    @Override
    public Map<String, Object> FuzzySearch(ReqPage page) {
        Map<String, Integer> driverPage = GetPage.GetPage(page);
        Integer limit = driverPage.get("limit");
        Integer offset = driverPage.get("offset");
        String keyWord="%"+page.getKeyword()+"%";
        int count = driverMapper.selectByKeyWord(keyWord);
        List<Driver> drivers = driverMapper.selectBykeyWords(keyWord, limit, offset, page.getSortCondition(), page.getSortRule());
        Map<String,Object> driversMap=new HashMap<String, Object>();
        driversMap.put("count",count);
        driversMap.put("drivers",drivers);
        return driversMap;
    }

    @Override
    public Driver GetByUserId(Integer userId) {
        return driverMapper.selectByPrimaryKey(userId);
    }
}
