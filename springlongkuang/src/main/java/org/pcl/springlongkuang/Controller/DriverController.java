package org.pcl.springlongkuang.Controller;

import lombok.extern.slf4j.Slf4j;
import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Model.Driver;
import org.pcl.springlongkuang.Service.DriverService;
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
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResponseVo<?> PostSearch(@RequestBody ReqPage page){
        Map<String, Object> driverMap = driverService.FuzzySearch(page);
        Object count = driverMap.get("count");
        Object drivers = driverMap.get("drivers");
        System.out.println("count==========="+count);
        return ResponseVo.success(drivers);
    }

    @RequestMapping(value = "/getSelf",method = RequestMethod.POST)
    public ResponseVo<?> GetSelf(HttpServletRequest request){
        String token=request.getHeader("Authorization");
        Map<String, Object> mapT = JWTUtils.GetInfo(token);
        Object user_id = mapT.get("user_id");
        Driver driver = driverService.GetByUserId((int) user_id);
        return ResponseVo.success(driver);
    }

}
