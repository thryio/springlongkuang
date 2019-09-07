package org.pcl.springlongkuang.Controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Model.Car;
import org.pcl.springlongkuang.Service.CarService;
import org.pcl.springlongkuang.Utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResponseVo<?> PostSearch(@RequestBody ReqPage page){
        Map<String, Object> carMap = carService.FuzzySearch(page);
        Object count = carMap.get("count");
        Object cars = carMap.get("cars");
        System.out.println("count======="+count);
        return ResponseVo.success(cars);
    }
}
