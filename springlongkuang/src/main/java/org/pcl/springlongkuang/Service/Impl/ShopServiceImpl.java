package org.pcl.springlongkuang.Service.Impl;

import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Mapper.ShopMapper;
import org.pcl.springlongkuang.Model.Shop;
import org.pcl.springlongkuang.Service.ShopService;
import org.pcl.springlongkuang.Utils.GetPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Override
    public Map<String,Object> FuzzySearch(ReqPage page) {
        Map<String, Integer> limitOffsetMap = GetPage.GetPage(page);
        Integer limit = limitOffsetMap.get("limit");
        Integer offset = limitOffsetMap.get("offset");
        String keyWord="%"+page.getKeyword()+"%";
        int count = shopMapper.selectByKeyWord(keyWord);
        List<Shop> shops = shopMapper.selectBykeyWords(keyWord, limit, offset,page.getSortCondition(),page.getSortRule());
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("count",count);
        map.put("shops",shops);
        return map;
    }

    @Override
    public Shop GetByOwnerID(Integer ownerId) {
        return shopMapper.selectByOwnerId(ownerId);
    }
}
