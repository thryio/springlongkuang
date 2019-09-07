package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Model.Order;

@Data
@Setter
@Getter
public class OrderLog {
    private Order order;
    private String driverName;
    private String cabinUserName;
    private String shopUserName;
    private String creatorRole;
    private String creatorName;


    @Override
    public String toString() {
        return "OrderLog{" +
                "order=" + order +
                ", driverName='" + driverName + '\'' +
                ", cabinUserName='" + cabinUserName + '\'' +
                ", shopUserName='" + shopUserName + '\'' +
                ", creatorRole='" + creatorRole + '\'' +
                ", creatorName='" + creatorName + '\'' +
                '}';
    }
}
