package org.pcl.springlongkuang.Service;

import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Model.Shop;

import java.util.Map;

public interface ShopService {
    Map<String,Object> FuzzySearch(ReqPage page);
    Shop GetByOwnerID(Integer ownerId);
}
