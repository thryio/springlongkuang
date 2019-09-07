package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Controller.Response.RespOrders;

@Data
@Setter
@Getter
public class ResultGetOrderViaOrderID {

    private RespOrders resp;
    private String errMsg;
}
