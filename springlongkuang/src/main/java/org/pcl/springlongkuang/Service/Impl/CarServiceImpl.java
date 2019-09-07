package org.pcl.springlongkuang.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Mapper.CarMapper;
import org.pcl.springlongkuang.Model.Car;
import org.pcl.springlongkuang.Service.CarService;
import org.pcl.springlongkuang.Utils.GetPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {
    
    @Autowired
    private CarMapper carMapper;
    @Override
    public Map<String,Object> FuzzySearch(ReqPage page) {
        Map<String, Integer> carPage = GetPage.GetPage(page);
        Integer limit = carPage.get("limit");
        Integer offset = carPage.get("offset");
        String keyWord="%"+page.getKeyword()+"%";
        int count = carMapper.selectByKeyWord(keyWord);
        List<Car> cars = carMapper.selectBykeyWords(keyWord, limit, offset, page.getSortCondition(), page.getSortRule());
        Map<String,Object> carMap=new HashMap<String, Object>();
        carMap.put("count",count);
        carMap.put("cars",cars);
        return carMap;

//        String keyWord="%"+page.getKeyword()+"%";
//        PageHelper.startPage(page.getPageNum(),page.getPageSize());
//        List<Car> cars = carMapper.selectAll(keyWord,page.getSortCondition(), page.getSortRule());
//        PageInfo<Car> carPageInfo=new PageInfo<Car>(cars);
//        System.out.println("getPageNum=="+page.getPageNum()+"   getPageSize=="+page.getPageSize());
//        System.out.println("carPageInfo===="+carPageInfo);
//        return carPageInfo;
    }

    @Override
    public Car GetOneByID(Integer id) {
        return carMapper.selectByPrimaryKey(id);
    }
}
