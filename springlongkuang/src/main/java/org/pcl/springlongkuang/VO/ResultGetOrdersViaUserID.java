package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Controller.Response.RespGetOrders;

@Data
@Setter
@Getter
public class ResultGetOrdersViaUserID {
    private RespGetOrders respGetOrders;
    private Integer count;
    private String err;
}
