package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Model.TransOrder;

@Data
@Setter
@Getter
public class TransOrderDetail {
    private TransOrder transOrder;
    private OrderDetail orderDetail;

    @Override
    public String toString() {
        return "TransOrderDetail{" +
                "transOrder=" + transOrder +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
