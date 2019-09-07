package org.pcl.springlongkuang.Controller;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.pcl.springlongkuang.Controller.Response.ManageIndexResp;
import org.pcl.springlongkuang.Service.OrderService;
import org.pcl.springlongkuang.Utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/log")
public class testController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/sum",method = RequestMethod.POST)
    public ResponseVo<?> Get(HttpServletRequest request){
        String date=null;
        date = request.getParameter("date");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        if ("".equals(date)){
            date = sdf.format(new Date());
        }
//        System.out.println("fdate========"+date);
        ManageIndexResp manageIndexResp = orderService.GetAllByDate(date);
        return ResponseVo.success(manageIndexResp);
    }
}
