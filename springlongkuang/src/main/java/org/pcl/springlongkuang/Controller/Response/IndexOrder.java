package org.pcl.springlongkuang.Controller.Response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Model.OrderContainer;
import java.util.List;
@Data
@Setter
@Getter
public class IndexOrder {
    List<OrderContainer> Stage1;
    List<OrderContainer> Stage2;
    List<OrderContainer> Stage3;
}
