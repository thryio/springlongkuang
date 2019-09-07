package org.pcl.springlongkuang.Controller.Response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Model.Car;
import org.pcl.springlongkuang.Model.Driver;
import org.pcl.springlongkuang.Model.TransOrder;
import java.util.List;

@Data
@Setter
@Getter
public class TransOrderDetailResp {
    private List<RespOrders> orders;
    private TransOrder transOrder;
    private Car car;
    private Driver driver;
    private Integer isExcept;
}
