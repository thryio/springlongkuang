package org.pcl.springlongkuang.Mapper;

import org.apache.ibatis.annotations.Param;
import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Model.Order;
import org.pcl.springlongkuang.Model.OrderAndTransOrder;
import org.pcl.springlongkuang.Model.OrderDetail;
import org.pcl.springlongkuang.VO.Arr;
import org.pcl.springlongkuang.VO.OrderLog;
import org.pcl.springlongkuang.VO.ResultGetTSOrdersViaUserID;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByTransOrderId(String tsOrderId);

    int deleteByOrderId(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    List<OrderAndTransOrder> selectByOrderId(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByOrderId(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderDetail> getOrder(String orderID);

    Order selectByShowNameAndCurdate(String shopName);

    //
    List<org.pcl.springlongkuang.VO.OrderDetail> GetOrderDetails (int userID , int state , int orderType , int limit , int offset);
    List<OrderLog> GetAllLogs(@Param("transOrdedID") String transOrdedID);
    int CountOrder(int orderType ,int state, int userID);
    List<org.pcl.springlongkuang.VO.OrderDetail> GetOrderDetailsByCabinID (int userID , int CabinID , int state , int orderType , int limit , int offset);
    int CountOrderByCabinID(int orderType ,int state ,int userID , int CabinID);
    List<org.pcl.springlongkuang.VO.OrderDetail> GetOrder(@Param("OrderId") String OrderId);
    List<Arr> OneNotifications(int userID);
    List<Arr> SecondNotifications(int userID);
    List<Arr> ThreeNotifications(int userID);
}