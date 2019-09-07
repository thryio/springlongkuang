package org.pcl.springlongkuang.Service;
import org.apache.ibatis.annotations.Param;
import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Controller.Response.Log;
import org.pcl.springlongkuang.Controller.Response.ManageIndexResp;
import org.pcl.springlongkuang.Model.*;
import org.pcl.springlongkuang.Model.Exception;
import org.pcl.springlongkuang.Model.OrderDetail;
import org.pcl.springlongkuang.VO.*;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<OrderDetail> GetOrders(String orderID);
    String GenerateOrderID(Order order);

    void AddOrders(List<Order> orders,String tsOrderID);

    String CreateOrder(TransOrder transOrder,List<Order> orders);
    boolean DeleteViaTSOrderID(String tsOrder);
    void DeleteOrder(String orderID);
    boolean BeforeDelete(String orderID);
    boolean DeleteViaOrderID(String OrderID);
    String UpdateOrderAndException(Order order, List<Exception> exceptions);
    String CreateOrUpdateTransOrder(TransOrder transOrder,List<Order> orders);
    ManageIndexResp GetAllByDate(String date);
    List<OrderContainer> Count(List<OrderContainer> OrderContainer);

    //////
    public Log GetAllLog(String transOrderID) throws java.lang.Exception;

    public Map<String, ResultGetTSOrdersViaUserID>
    GetTSOrdersViaUserID(int userID , ReqPage page, int state, int orderType);

    public Map<String, ResultGetOrdersViaUserID>
    GetOrdersViaUserID(int userID , ReqPage page, int state, int orderType);

    public Map<String, ResultGetAllViaUserID>
    GetAllViaUserID(int userID, ReqPage page , int state, int orderType);

    public Map<String, ResultGetOrdersViaTSOrderID>
    GetOrdersViaTSOrderID( String tsOrderID, ReqPage page , int state, int orderType );

    public Map<String, ResultGetOrderViaOrderID>
    GetOrderViaOrderID(String OrderID);

    public Map<String,ResultGetNotifications>
    GetNotifications(int userID , int roleID );
    public Map<String,ResultNotifications>
    Notifications(int userID , int roleID);
}
