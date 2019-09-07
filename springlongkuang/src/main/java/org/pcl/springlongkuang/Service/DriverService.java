package org.pcl.springlongkuang.Service;

import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Model.Driver;

import java.util.Map;

public interface DriverService {
    Map<String,Object> FuzzySearch(ReqPage page);
    Driver GetByUserId(Integer userId);
}
