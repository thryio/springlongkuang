package org.pcl.springlongkuang.Controller.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Model.Exception;
import org.pcl.springlongkuang.Model.Order;
import org.pcl.springlongkuang.Model.TransOrder;
import java.util.List;

@Data
@Setter
@Getter
public class ReqTransOrderAndOrders {
    private Order order;
    private List<Exception> exceptions;
    private TransOrder transOrder;
    private List<Order> orders;
}
