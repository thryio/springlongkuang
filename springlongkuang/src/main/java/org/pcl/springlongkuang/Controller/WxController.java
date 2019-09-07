package org.pcl.springlongkuang.Controller;

import org.pcl.springlongkuang.Controller.Request.ReqBindViaCode;
import org.pcl.springlongkuang.Service.UserService;
import org.pcl.springlongkuang.Utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/wx")
public class WxController {
    @Autowired
    private UserService userService;

    @PostMapping("/bind")
    public ResponseVo<?> PostBind(@RequestBody ReqBindViaCode req){
        Map<String, Object> checkMap = userService.Check(req);
        System.out.println("err:::"+checkMap.get("err"));
        if( checkMap.get("err")!=null){
            return ResponseVo.error(checkMap.get("err").toString());
        }
        System.out.println("resp:::"+checkMap.get("resp"));
        return ResponseVo.success(checkMap.get("resp"));

    }
}
