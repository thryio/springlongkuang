package org.pcl.springlongkuang.Service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.pcl.springlongkuang.Controller.Request.ReqPage;
import org.pcl.springlongkuang.Controller.Response.*;
import org.pcl.springlongkuang.Mapper.*;
import org.pcl.springlongkuang.Model.*;
import org.pcl.springlongkuang.Model.Exception;
import org.pcl.springlongkuang.Model.OrderDetail;
import org.pcl.springlongkuang.Service.ExceptionService;
import org.pcl.springlongkuang.Service.OrderService;
import org.pcl.springlongkuang.Service.TransOrderService;
import org.pcl.springlongkuang.Utils.DateUtil;
import org.pcl.springlongkuang.Utils.GetPage;
import org.pcl.springlongkuang.VO.*;
import org.pcl.springlongkuang.VO.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private TransOrderService transOrderService;

    @Autowired
    private TransOrderMapper transOrderMapper;

    @Autowired
    private ExceptionService exceptionService;

    @Autowired
    private CabinUserRelationMapper cabinUserRelationMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<OrderDetail> GetOrders(String orderID) {
        return orderMapper.getOrder(orderID);
    }

    @Override
    public String GenerateOrderID(Order order) {
        String orderID=null;
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String today=sdf.format(date);
        if (order.getType()==1){
            orderID=today;
        }else if (order.getType()==2){
            orderID="T"+today;
        }
        int count=0;
        Shop shop = shopMapper.selectByShopName(order.getShopName());
        Order order1 = orderMapper.selectByShowNameAndCurdate(order.getShopName());
        if (order1!=null&&order1.getId()!=0){
            count=Integer.parseInt(order1.getOrderId().split("-")[2]);
        }
        orderID=orderID+"-"+shop.getShopNum()+"-"+(count+1);
        return orderID;
    }

    @Transactional
    @Override
    public void AddOrders(List<Order> orders, String tsOrderID) throws RuntimeException{

//        System.out.println("订单数量："+orders.size());
        for (Order order:orders) {
            order.setTransOrderId(tsOrderID);
            order.setOrderId(this.GenerateOrderID(order));
            order.setCreatedAt(new Date());
//            System.out.println("OrderId:===="+order.getOrderId());
            if (orderMapper.insertSelective(order)!=1){
                throw new RuntimeException("创建订单失败！");
            }
        }
    }

    /**
     * 创建订单
     * @param transOrder
     * @param orders
     * @return
     */
    @Override
    public String CreateOrder(TransOrder transOrder, List<Order> orders) {

        String orderID = transOrderService.GenerateTransOrderID();
        transOrder.setTransOrderId(orderID);
        transOrder.setCreatedAt(new Date());
//        if (transOrderMapper.insertSelective(transOrder)!=1){
//            return "添加订单错误";
//        }

        try{
            this.AddOrders(orders,orderID);
            transOrderService.AddTransOrder(transOrder);
        }catch (RuntimeException e){
            e.printStackTrace();
            return "添加订单错误";
        }

//        if (!this.AddOrders(orders,orderID)){
//            return "添加订单错误";
//        }
        return "ok";
    }

    /**
     * 删除订单
     * @param tsOrder
     * @return
     */

    @Override
    public boolean DeleteViaTSOrderID(String tsOrder) {
//        return transOrderService.DeleteTransOrder(tsOrder);
        try{
            transOrderService.DeleteTransOrder(tsOrder);
            return true;
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    @Override
    public void  DeleteOrder(String orderID) throws RuntimeException {

        int i = transOrderMapper.deleteByOrderId(orderID);
        if (i==0&&orderMapper.deleteByOrderId(orderID)==0){
            throw new RuntimeException("删除错误,没有这订单");
        }
        if (i==1){
            if (orderMapper.deleteByOrderId(orderID)==0){
                throw new RuntimeException("删除订单错误");
            }
        }
    }

    @Override
    public boolean BeforeDelete(String orderID) {
        List<OrderAndTransOrder> oos = orderMapper.selectByOrderId(orderID);
        if (oos.size()>1){
            String containers = oos.get(0).getTransOrder().getContainers();
//            System.out.println("containers======"+containers);
            JSONArray jsonArray=JSONArray.fromObject(containers);
            List<OrderContainer> transContainer=new ArrayList<OrderContainer>();
            for (int i = 0; i <jsonArray.size() ; i++) {
                JSONObject job=jsonArray.getJSONObject(i);
                OrderContainer orderContainer=new OrderContainer();
                orderContainer.setName(job.getString("name"));
                orderContainer.setCount(Integer.parseInt(job.getString("count")));
                transContainer.add(orderContainer);
            }
            String c=null;
            for (OrderAndTransOrder v:oos) {
                if (v.getOrder().getOrderId().equals(orderID)){
                    c=v.getOrder().getContainers();
                }
            }
            List<OrderContainer> single=new ArrayList<OrderContainer>();
            JSONArray jsonArray1=JSONArray.fromObject(c);
            for (int i = 0; i <jsonArray1.size() ; i++) {
                JSONObject job1=jsonArray1.getJSONObject(i);
                OrderContainer orderContainer=new OrderContainer();
                orderContainer.setName(job1.getString("name"));
                orderContainer.setCount(Integer.parseInt(job1.getString("count")));
                single.add(orderContainer);
            }
            List<OrderContainer> toInsert=new ArrayList<OrderContainer>();
            for (OrderContainer total:transContainer) {
                for (OrderContainer one:single) {
                    if (total.getName().equals(one.getName())){
                        OrderContainer a=new OrderContainer();
                        total.setCount(total.getCount()-one.getCount());
                        a.setName(total.getName());
                        a.setCount(total.getCount());
                        toInsert.add(a);
                    }
                }
            }
            JSONArray contrainasbyteArr=JSONArray.fromObject(toInsert);
            String contrainasbyte=contrainasbyteArr.toString();

            if (transOrderMapper.updateByTransOrderId(oos.get(0).getTransOrder().getTransOrderId(),contrainasbyte)==0){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean DeleteViaOrderID(String orderID) {

//        System.out.println("BeforeDelete 返回值：===="+this.BeforeDelete(orderID));
//        System.out.println("DeleteOrder 返回值：===="+this.DeleteOrder(orderID));
//        if (this.BeforeDelete(orderID)&&this.DeleteOrder(orderID)){
//            return true;
//        }

        try{
            this.BeforeDelete(orderID);
            this.DeleteOrder(orderID);
            return true;
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 修改订单
     * @param order
     * @param exceptions
     * @return
     */
    @Override
    @Transactional
    public String UpdateOrderAndException(Order order, List<Exception> exceptions) {

//        System.out.println("order======"+order);
        order.setUpdatedAt(new Date());
        if (orderMapper.updateByOrderId(order)==0){
            try {
                throw new RuntimeException();
            }catch (RuntimeException e){
                e.printStackTrace();
            }finally {
                return "修改订单失败！";
            }
        }

        if (exceptions.size()!=0){
            if (!exceptionService.AddException(exceptions)){
                return "添加异常失败！";
            }
        }
        transOrderService.CheckFinish(order.getTransOrderId());
        return "ok";
    }

    @Override
    public String CreateOrUpdateTransOrder(TransOrder transOrder, List<Order> orders) {
        List<Order> updateOrders=new ArrayList<Order>();
        List<Order> createOrders=new ArrayList<Order>();
        for (Order v:orders) {
            if (v.getOrderId()==null){
                createOrders.add(v);
            }else {
                updateOrders.add(v);
            }
        }
//        if (!transOrderService.CreateOrUpdateTransOrder(transOrder,createOrders,updateOrders)){
//            return "defeated";
//        }
//        return "ok";

        try {
            transOrderService.CreateOrUpdateTransOrder(transOrder,createOrders,updateOrders);
            return "ok";
        }catch (RuntimeException e){
            e.printStackTrace();
            return "defeated";
        }
    }

    /**
     * 管理统计页
     * @param date
     * @return
     */

    @Override
    public ManageIndexResp GetAllByDate(String date) {
        ManageIndexResp manageIndexResp=new ManageIndexResp();
        IndexOrder indexOrder1=new IndexOrder();
        IndexOrder indexOrder2=new IndexOrder();
        List<TransOrder> transOrders = transOrderService.GetAllByDate(date);
        for (TransOrder v:transOrders) {
            //NRS 订单
            if (v.getType()==1){
                if (v.getStatus()==1&&v!=null){
                    JSONArray jsonArray=JSONArray.fromObject(v.getContainers());
                    List<OrderContainer> temp1=new ArrayList<OrderContainer>();
                    for (int i = 0; i <jsonArray.size() ; i++) {
                        OrderContainer orderContainer=new OrderContainer();
                        Object obj = jsonArray.get(i);
                        JSONObject object=JSONObject.fromObject(obj);
                        orderContainer.setName(object.getString("name"));
                        orderContainer.setCount(Integer.parseInt(object.getString("count")));
                        temp1.add(orderContainer);
                    }
                    List<OrderContainer> count = this.Count(temp1);
                    indexOrder1.setStage1(count);

                }else if (v.getStatus()==2&&v!=null){
                    JSONArray jsonArray=JSONArray.fromObject(v.getContainers());
                    List<OrderContainer> temp2=new ArrayList<OrderContainer>();
                    for (int i = 0; i <jsonArray.size() ; i++) {
                        OrderContainer orderContainer=new OrderContainer();
                        Object obj = jsonArray.get(i);
                        JSONObject object=JSONObject.fromObject(obj);
                        orderContainer.setName(object.getString("name"));
                        orderContainer.setCount(Integer.parseInt(object.getString("count")));
                        temp2.add(orderContainer);
                    }
                    List<OrderContainer> count = this.Count(temp2);

                    System.out.println("temp2======"+temp2);
                    System.out.println("count====="+count);
                    indexOrder1.setStage2(count);

                }else if (v.getStatus()==3&&v!=null){
                    JSONArray jsonArray=JSONArray.fromObject(v.getContainers());
                    List<OrderContainer> temp3=new ArrayList<OrderContainer>();
//                    System.out.println("jsonArray-----"+jsonArray.toString()+"size=="+jsonArray.size());
                    for (int i = 0; i <jsonArray.size() ; i++) {
                        OrderContainer orderContainer=new OrderContainer();
                        Object obj = jsonArray.get(i);
                        JSONObject object=JSONObject.fromObject(obj);
                        orderContainer.setName(object.getString("name"));
                        orderContainer.setCount(Integer.parseInt(object.getString("count")));
                        temp3.add(orderContainer);
                    }
                    List<OrderContainer> count = this.Count(temp3);
                    indexOrder1.setStage3(count);
                }

            }else if (v.getType()==2){  //NRT   订单
                if (v.getStatus()==1&&v!=null){
                    JSONArray jsonArray=JSONArray.fromObject(v.getContainers());
                    List<OrderContainer> temp1=new ArrayList<OrderContainer>();
                    for (int i = 0; i <jsonArray.size() ; i++) {
                        OrderContainer orderContainer=new OrderContainer();
                        Object obj = jsonArray.get(i);
                        JSONObject object=JSONObject.fromObject(obj);
                        orderContainer.setName(object.getString("name"));
                        orderContainer.setCount(Integer.parseInt(object.getString("count")));
                       temp1.add(orderContainer);
                    }
                    List<OrderContainer> count = this.Count(temp1);
                    indexOrder2.setStage1(count);

                }else if (v.getStatus()==2&&v!=null){
                    JSONArray jsonArray=JSONArray.fromObject(v.getContainers());
                    List<OrderContainer> temp2=new ArrayList<OrderContainer>();
                    for (int i = 0; i <jsonArray.size() ; i++) {
                        OrderContainer orderContainer=new OrderContainer();
                        Object obj = jsonArray.get(i);
                        JSONObject object=JSONObject.fromObject(obj);
                        orderContainer.setName(object.getString("name"));
                        orderContainer.setCount(Integer.parseInt(object.getString("count")));
                       temp2.add(orderContainer);
                    }

                    List<OrderContainer> count = this.Count(temp2);
                    indexOrder2.setStage2(count);

                }else if (v.getStatus()==3&&v!=null){
                    JSONArray jsonArray=JSONArray.fromObject(v.getContainers());
                    List<OrderContainer> temp3=new ArrayList<OrderContainer>();
                    for (int i = 0; i <jsonArray.size() ; i++) {
                        OrderContainer orderContainer=new OrderContainer();
                        Object obj = jsonArray.get(i);
                        JSONObject object=JSONObject.fromObject(obj);
                        orderContainer.setName(object.getString("name"));
                        orderContainer.setCount(Integer.parseInt(object.getString("count")));
                        temp3.add(orderContainer);
                    }
                    List<OrderContainer> count = this.Count(temp3);

                    System.out.println("temp3====="+temp3);
                    System.out.println("count3====="+count);
                    indexOrder2.setStage3(count);
                }
            }
        }
        manageIndexResp.setNRSOrder(indexOrder1);
        manageIndexResp.setNRTOrder(indexOrder2);
        return manageIndexResp;
    }

    @Override
    public List<OrderContainer> Count(List<OrderContainer> orderContainer) {
        int LC6Sun = 0;
        int LC4Sun = 0;
        int NKLSun = 0;
        int ZDK6411Sun = 0;
        int ZDK6422Sun = 0;
        int SCKSun = 0;
        int WRKSun = 0;
        int EKLSun = 0;
        for (OrderContainer v : orderContainer) {
            String name = v.getName();
            int count = v.getCount();
            if ("LC6".equals(name)) {
                LC6Sun = LC6Sun + count;
            } else if ("LC4".equals(name)) {
                LC4Sun = LC4Sun + count;
            } else if ("NKL".equals(name)) {
                NKLSun = NKLSun + count;
            } else if ("ZDK6411".equals(name)) {
                ZDK6411Sun = ZDK6411Sun + count;
            } else if ("ZDK6422".equals(name)) {
                ZDK6422Sun = ZDK6422Sun + count;
            } else if ("SCK".equals(name)) {
                SCKSun = SCKSun + count;
            } else if ("WRK".equals(name)) {
                WRKSun = WRKSun + count;
            } else if ("EKL".equals(name)) {
                EKLSun = EKLSun + count;
            }
        }
        List<OrderContainer> orderContainers = new ArrayList<OrderContainer>();

        OrderContainer oc1 = new OrderContainer();
        oc1.setName("LC6");
        oc1.setCount(LC6Sun);
        orderContainers.add(oc1);

        OrderContainer oc2 = new OrderContainer();
        oc2.setName("LC4");
        oc2.setCount(LC4Sun);
        orderContainers.add(oc2);

        OrderContainer oc3 = new OrderContainer();
        oc3.setName("NKL");
        oc3.setCount(NKLSun);
        orderContainers.add(oc3);

        OrderContainer oc4 = new OrderContainer();
        oc4.setName("ZDK6411");
        oc4.setCount(ZDK6411Sun);
        orderContainers.add(oc4);

        OrderContainer oc5 = new OrderContainer();
        oc5.setName("ZDK6422");
        oc5.setCount(ZDK6422Sun);
        orderContainers.add(oc5);

        OrderContainer oc6 = new OrderContainer();
        oc6.setName("SCK");
        oc6.setCount(SCKSun);
        orderContainers.add(oc6);

        OrderContainer oc7 = new OrderContainer();
        oc7.setName("WRK");
        oc7.setCount(WRKSun);
        orderContainers.add(oc7);

        OrderContainer oc8 = new OrderContainer();
        oc8.setName("EKL");
        oc8.setCount(EKLSun);
        orderContainers.add(oc8);

        return orderContainers;
    }





    @Override
    public Log GetAllLog(String transOrderID) throws java.lang.Exception {
        List<OrderLog> data = orderMapper.GetAllLogs(transOrderID);
        Log resp = new Log();
        resp.setDriverName(data.get(0).getDriverName());
        resp.setCreatorName(data.get(0).getCreatorName());
        resp.setCreatorRole(data.get(0).getCreatorRole());
        resp.setCreatorShop(data.get(0).getOrder().getShopName());
        resp.setCreatTime(DateUtil.dateToStringTime(data.get(0).getOrder().getCreatedAt()));
        resp.setDeliverTime(data.get(0).getOrder().getDeliverAt());
        ArrayList<Recipient> recipientList = new ArrayList<>();

        // nrs订单 接收者是门店
        if(data.get(0).getOrder().getType() ==1){
            for(int i=0;i<data.size();i++){
                OrderLog v = data.get(i);
                System.out.println(v);
                Recipient one = new Recipient();
                one.setOperatorName(v.getShopUserName());
                one.setShopName(v.getOrder().getShopName());
                one.setReceiveTime(v.getOrder().getReceiveAt());
                one.setRole("门店");
                recipientList.add(one);
            }
            // nrt 订单接收者是 仓库
        }else if(data.get(0).getOrder().getType()==2){
            for(int i=0;i<data.size();i++){
                OrderLog v = data.get(i);
                System.out.println(v);
                Recipient one = new Recipient();
                one.setOperatorName(v.getCabinUserName());
                one.setReceiveTime(v.getOrder().getReceiveAt());
                one.setRole("仓库");
                //if(one!=null){recipientList.add(one);}
                recipientList.add(one);
                System.out.println(recipientList);

            }
        }
        resp.setRecipients(recipientList);
        return  resp;
    }

    @Override
    public Map<String, ResultGetTSOrdersViaUserID> GetTSOrdersViaUserID(int userID , ReqPage page, int state, int orderType){
        log.info("/GetTsORDERViaUserID");
        Map<String, ResultGetOrdersViaUserID> map
                = GetOrdersViaUserID(userID, page, state, orderType);
        ResultGetOrdersViaUserID result = map.get("result");
        RespGetOrders data = result.getRespGetOrders();
        Integer count = result.getCount();
        String err = result.getErr();
        List<RespOrders> dataOrders = data.getOrders();
        ArrayList<TransOrderList> target=new ArrayList<>();
        for(int i=0;i<dataOrders.size();i++){
            RespOrders v = dataOrders.get(i);
            TransOrderList one = transOrderMapper.GetTransOrderListViaTransOrderID(orderType, state, v.getOrder().getTransOrderId());
            System.out.println("ONE:****"+one);
            if(v.getExceptions().size()!=0){
                one.setIsExcept(1);
            }if(v.getOrder().getTransOrderId()!=null){
                target.add(one);
                continue;
            }else {
                if(one.getTransOrder().getId()!=0){
                    target.add(one);
                }
            }
        }
        //构建返回值
        Map<String, ResultGetTSOrdersViaUserID> resultMap =new HashMap<>();
        ResultGetTSOrdersViaUserID resultModel = new ResultGetTSOrdersViaUserID();
        resultModel.setTarget(target);
        resultModel.setCount(target.size());
        resultModel.setErr("ok");
        resultMap.put("result",resultModel);
        return resultMap;
    }

    @Override
    public Map<String, ResultGetOrdersViaUserID> GetOrdersViaUserID(int userID , ReqPage page, int state, int orderType){
        Map<String, ResultGetOrdersViaUserID> resultMap =new HashMap<>();
        Map<String, ResultGetAllViaUserID> map = GetAllViaUserID(userID, page, state, orderType);
        ResultGetAllViaUserID result = map.get("result");
        List<org.pcl.springlongkuang.VO.OrderDetail> data = result.getDetails();
        int count = result.getCount();
        RespGetOrders resp = new RespGetOrders();
        ArrayList<RespOrders> list=new ArrayList<>();
        ArrayList<Exception> eList=new ArrayList<>();

        for (int i=0;i<data.size();i++){
            org.pcl.springlongkuang.VO.OrderDetail v = data.get(i);
            if(v.getOrder().getOrderId()!=null){
                RespOrders singleOrder = new RespOrders();
                singleOrder.setOrder(v.getOrder());
                singleOrder.setDriver(v.getDriver());
                singleOrder.setCar(v.getCar());
                eList.add(v.getException());
                singleOrder.setExceptions(eList);
                singleOrder.setIsExcept(1);
                list.add(singleOrder);
                continue;
            }else {
                RespOrders singleOrder = new RespOrders();
                singleOrder.setOrder(v.getOrder());
                singleOrder.setCar(v.getCar());
                if(v.getException().getOrderId().length() != 0){
                    List<Exception> exceptionList = singleOrder.getExceptions();
                    exceptionList.add(v.getException());
                    singleOrder.setExceptions(exceptionList);
                    singleOrder.setIsExcept(1);
                }
                list.add(singleOrder);

            }
        }
        resp.setOrders(list);
        // 返回值构造
        ResultGetOrdersViaUserID resultModel = new ResultGetOrdersViaUserID();
        resultModel.setRespGetOrders(resp);
        resultModel.setCount(count);
        resultModel.setErr("ok");
        resultMap.put("result",resultModel);
        return resultMap;
    }

    //return List<OrderDetail> ，int count(Mapper)
    @Override
    public Map<String,ResultGetAllViaUserID>GetAllViaUserID(int userID, ReqPage page , int state, int orderType){
        Map<String,ResultGetAllViaUserID> map =new HashMap<>();
        ResultGetAllViaUserID resultModel = new ResultGetAllViaUserID();
        Map<String, Integer> PageMap = GetPage.GetPage(page);
        Integer limit = PageMap.get("limit");
        Integer offset = PageMap.get("offset");
        if(state==3){
            List<org.pcl.springlongkuang.VO.OrderDetail> details = orderMapper.GetOrderDetails(userID, state, orderType, limit, offset);
            int count = orderMapper.CountOrder(orderType, state, userID);
            resultModel.setDetails(details);
            resultModel.setCount(count);
            map.put("result",resultModel);
            return map;
        }
        CabinUserRelation relation = cabinUserRelationMapper.GetViaUserID(userID);
        List<org.pcl.springlongkuang.VO.OrderDetail> details =
                orderMapper.GetOrderDetailsByCabinID(userID, relation.getCabinId(), state, orderType, limit, offset);
        int count =
                orderMapper.CountOrderByCabinID(orderType, state, userID, relation.getCabinId());
        resultModel.setDetails(details);
        resultModel.setCount(count);
        map.put("result",resultModel);
        return map;
    }


    @Override
    public Map<String,ResultGetOrdersViaTSOrderID>
    GetOrdersViaTSOrderID( String tsOrderID, ReqPage page , int state, int orderType){
        Map<String, ResultGetAllViaTSOrderID> map
                = transOrderService.GetOrdersViaTSOrderID(tsOrderID, page, state, orderType);
        ResultGetAllViaTSOrderID result = map.get("result");
        List<TransOrderDetail> data = result.getDetails();
        int count = result.getCount();

        TransOrderDetailResp resp=new TransOrderDetailResp();
        List<RespOrders> respOrdersList=null;
        // 只有一个transOrder
        if(data.size()!=0){
            resp.setTransOrder(data.get(0).getTransOrder());
            resp.setDriver(data.get(0).getOrderDetail().getDriver());
            // 硬查车辆
            resp.setCar(carMapper.selectByPrimaryKey(resp.getTransOrder().getCarId()));
            // 添加异常
            if(data.get(0).getOrderDetail().getException().getOrderId().length()!=0){
                resp.setIsExcept(1);
            }
            // 存在证明
            for(int i=0;i<data.size();i++){
                TransOrderDetail v = data.get(i);
                Order one = v.getOrderDetail().getOrder();
                if(one.getOrderId()!=null){
                    // 存在
                    continue;
                }else {
                    // 不存在
                    RespOrders single = new RespOrders();
                    single.setOrder(v.getOrderDetail().getOrder());
                    single.setDriver(v.getOrderDetail().getDriver());
                    if(v.getOrderDetail().getException().getOrderId().length()!=0){
                        single.setIsExcept(1);
                    }
                    respOrdersList.add(single);
                }
            }
            resp.setOrders(respOrdersList);
        }
        //构造返回
        ResultGetOrdersViaTSOrderID resultModel = new ResultGetOrdersViaTSOrderID();
        resultModel.setTransOrderDetailResp(resp);
        resultModel.setCount(count);
        resultModel.setErrMsg("ok");
        Map<String,ResultGetOrdersViaTSOrderID> ResultMap= new HashMap<>();
        ResultMap.put("result",resultModel);
        return ResultMap;
    }


    @Override
    public Map<String,ResultGetOrderViaOrderID> GetOrderViaOrderID(String OrderID){
        List<org.pcl.springlongkuang.VO.OrderDetail> data = orderMapper.GetOrder(OrderID);
        RespOrders resp = new RespOrders();
        List<Exception> exceptionList=null;
        resp.setOrder(data.get(0).getOrder());
        resp.setDriver(data.get(0).getDriver());
        resp.setCar(data.get(0).getCar());
        for(int i=0;i<data.size();i++){
            org.pcl.springlongkuang.VO.OrderDetail v = data.get(0);
            if(v.getException().getOrderId()==resp.getOrder().getOrderId()){
                exceptionList.add(v.getException());
            }
        }resp.setExceptions(exceptionList);

        //构造返回值
        ResultGetOrderViaOrderID resultModel = new ResultGetOrderViaOrderID();
        resultModel.setResp(resp);
        resultModel.setErrMsg("ok");
        Map<String,ResultGetOrderViaOrderID> ResultMap =new HashMap<>();
        ResultMap.put("result",resultModel);
        return ResultMap;

    }

    @Override
    public Map<String,ResultGetNotifications> GetNotifications(int userID , int roleID ){
        Map<String, ResultNotifications> map = Notifications(userID, roleID);
        ResultGetNotifications resultModel = new ResultGetNotifications();
        Note note = new Note();
        ResultNotifications result = map.get("result");
        List<Arr> data = result.getArr();
        if(result.getErr()!="ok"){
            resultModel.setErr(result.getErr());
            resultModel.setNote(note);
        }
        note.setNRS0(data.get(0).getNum());
        note.setNRS1(data.get(1).getNum());
        note.setNRS2(data.get(2).getNum());
        note.setNRS3(data.get(3).getNum());
        note.setNRT0(data.get(4).getNum());
        note.setNRT1(data.get(5).getNum());
        note.setNRT2(data.get(6).getNum());
        note.setNRT3(data.get(7).getNum());
        resultModel.setNote(note);
        resultModel.setErr(result.getErr());
        Map<String,ResultGetNotifications> resultMap=new HashMap<>();
        resultMap.put("result",resultModel);
        return resultMap;
    }

    @Override
    public Map<String,ResultNotifications> Notifications(int userID , int roleID){
        ResultNotifications resultModel = new ResultNotifications();
        switch (roleID){
            case 1:{
                List<Arr> arrs = orderMapper.OneNotifications(userID);
                if(arrs.size()!=8){
                    resultModel.setArr(arrs);
                    resultModel.setErr("查询错误");
                }
                resultModel.setArr(arrs);
                resultModel.setErr("ok");
            }
            case 2:{
                List<Arr> arrs = orderMapper.SecondNotifications(userID);
                if(arrs.size()!=8){
                    resultModel.setArr(arrs);
                    resultModel.setErr("查询错误");
                }
                resultModel.setArr(arrs);
                resultModel.setErr("ok");
            }
            case 3:{
                List<Arr> arrs = orderMapper.ThreeNotifications(userID);
                if(arrs.size()!=8){
                    resultModel.setArr(arrs);
                    resultModel.setErr("查询错误");
                }
                resultModel.setArr(arrs);
                resultModel.setErr("ok");
            }

            default:{
                resultModel.setErr("错误的roleID");
                resultModel.setArr(null);
            }
            Map<String,ResultNotifications> resultmap=new HashMap<>();
            resultmap.put("result",resultModel);
            return resultmap;
        }
    }

}
