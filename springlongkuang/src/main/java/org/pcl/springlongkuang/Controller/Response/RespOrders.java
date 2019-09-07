package org.pcl.springlongkuang.Controller.Response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Model.Car;
import org.pcl.springlongkuang.Model.Driver;
import org.pcl.springlongkuang.Model.Exception;
import org.pcl.springlongkuang.Model.Order;

import java.util.List;

@Setter
@Getter
@Data
public class RespOrders {
    private Order order;
    private Driver driver;
    private Car car;
    private List<Exception> exceptions;
    private Integer isExcept;
}
