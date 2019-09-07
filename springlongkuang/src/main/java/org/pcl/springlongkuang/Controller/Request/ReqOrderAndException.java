package org.pcl.springlongkuang.Controller.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Model.Exception;
import org.pcl.springlongkuang.Model.Order;

import java.util.List;

@Setter
@Getter
@Data
public class ReqOrderAndException {
    private Order order;
    private List<Exception> exceptions;
}
