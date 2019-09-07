package org.pcl.springlongkuang.Service.Impl;

import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Mapper.OrderMapper;
import org.pcl.springlongkuang.Mapper.TransOrderMapper;
import org.pcl.springlongkuang.Model.Order;
import org.pcl.springlongkuang.Model.TransOrder;
import org.pcl.springlongkuang.Service.OrderService;
import org.pcl.springlongkuang.Service.TransOrderService;
import org.pcl.springlongkuang.Utils.GetPage;
import org.pcl.springlongkuang.VO.ResultGetAllViaTSOrderID;
import org.pcl.springlongkuang.VO.TransOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransOrderServiceImpl implements TransOrderService {

    @Autowired
    private TransOrderMapper transOrderMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;
    @Override
    public String GenerateTransOrderID() {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String today=sdf.format(date);
        int count=0;
        TransOrder transOrder = transOrderMapper.selectByCreatedAt();
        if (transOrder!=null&&transOrder.getId()!=0){
            String id=transOrder.getTransOrderId().split("-")[1];
            count=Integer.parseInt(id);
        }
        String batchNum=String.format("%05d",count+1);
        String ID="DB"+today+"-"+batchNum;
        return ID;
    }

    @Transactional
    @Override
    public void DeleteTransOrder(String tsOrder)throws RuntimeException {

//        if (orderMapper.deleteByTransOrderId(tsOrder)==0
//                ||transOrderMapper.deleteByTOid(tsOrder)==0){
//
//        }
        if (orderMapper.deleteByTransOrderId(tsOrder)==0){
            throw new RuntimeException("删除订单失败！");
        }
        if (transOrderMapper.deleteByTOid(tsOrder)==0){
            throw new RuntimeException("删除运单失败！");
        }


    }

    @Override
    public void CheckFinish(String transOrderID) {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now=sdf.format(date);
        transOrderMapper.updateByTransOrderIdAndCheckFinish(transOrderID,now);
    }

    @Override
    @Transactional
    public boolean CreateOrUpdateTransOrder(TransOrder transOrder,
                                            List<Order> createOrders, List<Order> updateOrders) throws RuntimeException {
//        if (!orderService.AddOrders(createOrders,transOrder.getTransOrderId())){
//            throw new RuntimeException();
//        }
        try {
            orderService.AddOrders(createOrders,transOrder.getTransOrderId());
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        if (updateOrders.size()!=0){
            for (Order v:updateOrders) {
                v.setUpdatedAt(new Date());
                if (orderMapper.updateByOrderId(v)==0){
                    throw new RuntimeException("修改订单失败！");
                }
            }
        }

        transOrder.setUpdatedAt(new Date());
        if (transOrderMapper.updateByTransOrderIdAll(transOrder)==0){
            throw new RuntimeException("修改运单失败！");
        }
       return true;
    }


    @Override
    public List<TransOrder> GetAllByDate(String date) {
        return transOrderMapper.selectByDate(date);
    }



    @Override
    public Map<String, ResultGetAllViaTSOrderID> GetOrdersViaTSOrderID(String tsOrderID, ReqPage page, int state, int orderType ){
        Map<String, Integer> map = GetPage.GetPage(page);
        Integer limit = map.get("limit");
        Integer offset = map.get("offset");
        List<TransOrderDetail> details
                = transOrderMapper.GetTransOrderDetail(tsOrderID, orderType, state, limit, offset);
        int count = transOrderMapper.CountTransOrder(tsOrderID, orderType, state);

        //构建返回值
        ResultGetAllViaTSOrderID resultModel = new ResultGetAllViaTSOrderID();
        resultModel.setCount(count);
        resultModel.setDetails(details);
        Map<String, ResultGetAllViaTSOrderID> resultMap =new HashMap<>();
        resultMap.put("result",resultModel);
        return resultMap;
    }

    @Transactional
    @Override
    public void AddTransOrder(TransOrder transOrder) throws RuntimeException{
        if (transOrderMapper.insertSelective(transOrder)!=1){
            throw new RuntimeException("创建运单失败！");
        }

    }
}
