package org.pcl.springlongkuang.Mapper;

import org.apache.ibatis.annotations.Param;
import org.pcl.springlongkuang.Model.TransOrder;
import org.pcl.springlongkuang.VO.TransOrderDetail;
import org.pcl.springlongkuang.VO.TransOrderList;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransOrderMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByTOid(String tsOrder);

    int deleteByOrderId(String orderId);

    int insert(TransOrder record);

    int insertSelective(TransOrder record);

    TransOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TransOrder record);

    int updateByPrimaryKey(TransOrder record);

    int updateByTransOrderId(String transOrderID,String contrainasbyte);

    int updateByTransOrderIdAll(TransOrder transOrder);

    int updateByTransOrderIdAndCheckFinish(String transOrderID,String now);

    TransOrder selectByCreatedAt();

    List<TransOrder> selectByDate(String date);

    TransOrderList GetTransOrderListViaTransOrderID(
            @Param("orderType") int oT, @Param("state") int sT, @Param("tsOrderID" )String tOID );

    List<TransOrderDetail> GetTransOrderDetail(
            String tsOrderID, int orderType,int state,int limit, int offset);

    int CountTransOrder(String tsOrderID,int orderType,int state);

}