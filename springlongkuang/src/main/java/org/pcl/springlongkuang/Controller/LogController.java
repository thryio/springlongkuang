package org.pcl.springlongkuang.Controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.pcl.springlongkuang.Controller.Response.Log;
import org.pcl.springlongkuang.Service.OrderService;
import org.pcl.springlongkuang.Utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public ResponseVo<?> GetByid(@Param("id") String id) throws Exception {
        System.out.println(id);
        Log data = orderService.GetAllLog(id);
        return ResponseVo.success(data);
    }

    @GetMapping("/sum")
    public ResponseVo<?> GetSum(String date){
        if(date.length()==0){
            Date date1 = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            date = format.format(date1);
        }
        return null;
    }
}
