package org.pcl.springlongkuang.Controller.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Model.Order;
import org.pcl.springlongkuang.Model.TransOrder;

import java.util.List;
@Setter
@Getter
@Data
public class CreatrOrderReq {

    private TransOrder transOrder;
    private List<Order> orders;
}
