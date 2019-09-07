package org.pcl.springlongkuang.Controller;

import lombok.extern.slf4j.Slf4j;
import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Model.Shop;
import org.pcl.springlongkuang.Service.ShopService;
import org.pcl.springlongkuang.Utils.JWTUtils;
import org.pcl.springlongkuang.Utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/shop")
public class ShopController {
    
    @Autowired
    private ShopService shopService;
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResponseVo<?> PostSearch(@RequestBody ReqPage page){
        Map<String, Object> map = shopService.FuzzySearch(page);
        System.out.println(map.get("count"));
        Object shops = map.get("shops");
        return ResponseVo.success(shops);
    }

    @RequestMapping(value = "/getSelf",method = RequestMethod.POST)
    private ResponseVo<?> GetSelf(HttpServletRequest request){
        String token=request.getHeader("Authorization");
        Map<String, Object> clainmap = JWTUtils.GetInfo(token);
        Object user_id = clainmap.get("user_id");
        Shop shop = shopService.GetByOwnerID((int) user_id);
        return ResponseVo.success(shop);
    }
}
