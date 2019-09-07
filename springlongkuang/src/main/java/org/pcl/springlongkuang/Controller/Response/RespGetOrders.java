package org.pcl.springlongkuang.Controller.Response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Model.TransOrder;

import java.util.List;
@Data
@Getter
@Setter
public class RespGetOrders {
    private List<RespOrders> orders;
    private TransOrder transOrder;
}
