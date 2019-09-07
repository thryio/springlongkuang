package org.pcl.springlongkuang.Controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.pcl.springlongkuang.Controller.Request.CreatrOrderReq;
import org.pcl.springlongkuang.Controller.Request.ReqPostList;
import org.pcl.springlongkuang.Controller.Request.ReqTransOrderAndOrders;
import org.pcl.springlongkuang.Service.OrderService;
import org.pcl.springlongkuang.Utils.CodeEnum;
import org.pcl.springlongkuang.Utils.JWTUtils;
import org.pcl.springlongkuang.Utils.ResponseVo;
import org.pcl.springlongkuang.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseVo<?> PostCreate(@RequestBody CreatrOrderReq creatrOrderReq){
        String s = orderService.CreateOrder(creatrOrderReq.getTransOrder(), creatrOrderReq.getOrders());
        if (!"ok".equals(s)){
            return ResponseVo.error(CodeEnum.frequently_error,s);
        }else {
            return ResponseVo.success(true);
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseVo<?> PostDelete(HttpServletRequest request){

        String transOrderID=request.getParameter("trans_order_id");
        String orderID=request.getParameter("order_id");
        if (transOrderID!=null){
            if (!orderService.DeleteViaTSOrderID(transOrderID)){
                return ResponseVo.error(CodeEnum.frequently_error,"false");
            }
        }else if (orderID!=null){
            if (!orderService.DeleteViaOrderID(orderID)){
                return ResponseVo.error(CodeEnum.frequently_error,"false");
            }
        }
        return ResponseVo.success(true);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseVo<?> PostUpdate(HttpServletRequest req ,@RequestBody ReqTransOrderAndOrders reqTOAndO){
        System.out.println(reqTOAndO.toString());
        String action=req.getParameter("action");
        if ("order".equals(action)&&reqTOAndO.getOrder()!=null){
            log.info("single order update only for receive order");
            String s = orderService.UpdateOrderAndException(reqTOAndO.getOrder(),reqTOAndO.getExceptions());
            if (!"ok".equals(s)){
                return ResponseVo.error(CodeEnum.frequently_error,s);
            }else {
                return ResponseVo.success(true);
            }
        } else if ("trans".equals(action)&&reqTOAndO.getOrders()!=null&&reqTOAndO.getTransOrder()!=null){
            log.info("batch order update");
            String s = orderService.CreateOrUpdateTransOrder(reqTOAndO.getTransOrder(), reqTOAndO.getOrders());
            if (!"ok".equals(s)){
                return ResponseVo.error(CodeEnum.frequently_error,s);
            }else {
                return ResponseVo.success(true);
            }
        } else {
            return ResponseVo.error(CodeEnum.frequently_error,"wrong action");
        }

    }


    ///////////////////

    @PostMapping("/list")
    public ResponseVo<?> PostList(HttpServletRequest request , @RequestBody ReqPostList req){
        log.info("/order/List");
        String token = request.getHeader("Authorization").substring(7, request.getHeader("Authorization").length());
        Map<String, Object> claimMap = JWTUtils.GetInfo(token);
        Object userID = claimMap.get("user_id");

        Map<String, ResultGetTSOrdersViaUserID> map
                = orderService.GetTSOrdersViaUserID((Integer) userID, req.getPage(), req.getState(), req.getType());
        ResultGetTSOrdersViaUserID result = map.get("result");
        List<TransOrderList> data = result.getTarget();
        Integer count = result.getCount();
        String err = result.getErr();
        if(err !="ok"){
            return ResponseVo.error(err);
        }
        return ResponseVo.success(data, String.valueOf(count));
    }


    @RequestMapping("/orders")
    public ResponseVo<?> PostOrder(HttpServletRequest request ,  String trans_order_id, @RequestBody ReqPostList req){
        log.info("/order/orders");
        System.out.println(req);
        if(trans_order_id.length()!=0||!trans_order_id.isEmpty()){
            log.info("根据运单号查询");
            Map<String, ResultGetOrdersViaTSOrderID> map =
                    orderService.GetOrdersViaTSOrderID(trans_order_id, req.getPage(), req.getState(), req.getType());
            ResultGetOrdersViaTSOrderID result = map.get("result");
            if(result.getErrMsg()!="ok"){
                return ResponseVo.error(result.getErrMsg());
            }
            return ResponseVo.success(result.getTransOrderDetailResp(), String.valueOf(result.getCount()));
        }
        // 没传运单号，就是根据用户查
        log.info("根据用户查");
        String token = request.getHeader("Authorization").substring(7, request.getHeader("Authorization").length());
        Map<String, Object> claimMap = JWTUtils.GetInfo(token);
        Object userID = claimMap.get("user_id");
        Map<String, ResultGetOrdersViaUserID> map =
                orderService.GetOrdersViaUserID((Integer) userID, req.getPage(), req.getState(), req.getType());
        ResultGetOrdersViaUserID result = map.get("result");
        if(result.getErr() !="ok"){
            return ResponseVo.error(result.getErr());
        }
        return ResponseVo.success(result.getRespGetOrders(), String.valueOf(result.getCount()));

    }

    @GetMapping("/one")
    public ResponseVo<?>GetOne(String id){
        String order_id=id;
        Map<String, ResultGetOrderViaOrderID> map = orderService.GetOrderViaOrderID(order_id);
        ResultGetOrderViaOrderID result = map.get("result");
        if(result.getErrMsg()!="ok"){
            return ResponseVo.error(result.getErrMsg());
        }
        return ResponseVo.success(result.getResp());
    }


    @GetMapping("/notification")
    public ResponseVo<?> GetNotification(HttpServletRequest request ,@Param("role") int role){
        String token = request.getHeader("Authorization").substring(7, request.getHeader("Authorization").length());
        Map<String, Object> claimMap = JWTUtils.GetInfo(token);
        Object userID = claimMap.get("user_id");
        Map<String, ResultGetNotifications> map =
                orderService.GetNotifications((Integer) userID, role);
        ResultGetNotifications result = map.get("result");
        Note data = result.getNote();
        if(result.getErr() !="ok"){
            return ResponseVo.error(result.getErr());
        }
        return ResponseVo.success(data);

    }

}
