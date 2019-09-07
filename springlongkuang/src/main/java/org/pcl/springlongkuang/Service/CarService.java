package org.pcl.springlongkuang.Service;

import com.github.pagehelper.PageInfo;
import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Model.Car;

import java.util.Map;

public interface CarService {
    Map<String,Object> FuzzySearch(ReqPage page);
    Car GetOneByID(Integer id);
}
