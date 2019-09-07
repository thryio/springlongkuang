package org.pcl.springlongkuang.Utils;

import org.pcl.springlongkuang.Controller.Request.ReqPage;
import java.util.HashMap;
import java.util.Map;

public class GetPage {
    public static Map<String,Integer> GetPage(ReqPage page){

        Map<String,Integer> map =new HashMap<>();
        if(page.getPageNum()>0&&page.getPageSize()>0){
            System.out.println(page.getPageNum()+"***"+page.getPageSize());
            int offset=(page.getPageNum()-1)*page.getPageSize();
            map.put("limit",page.getPageSize());
            map.put("offset",offset);
        }
        return map;
    }
}
