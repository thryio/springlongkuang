package org.pcl.springlongkuang.Service;

import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Model.Order;
import org.pcl.springlongkuang.Model.TransOrder;
import org.pcl.springlongkuang.VO.ResultGetAllViaTSOrderID;

import java.util.List;
import java.util.Map;

public interface TransOrderService {
    String GenerateTransOrderID();
    void DeleteTransOrder(String tsOrder);
    void CheckFinish(String transOrderID);
    boolean CreateOrUpdateTransOrder(TransOrder transOrder, List<Order> createOrders ,List<Order> updateOrder);
    List<TransOrder> GetAllByDate(String date);

    public Map<String, ResultGetAllViaTSOrderID>
    GetOrdersViaTSOrderID(String tsOrderID, ReqPage page, int state, int orderType );

    void AddTransOrder(TransOrder transOrder);
}
