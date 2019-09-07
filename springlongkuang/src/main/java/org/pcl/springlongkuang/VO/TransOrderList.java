package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Model.TransOrder;

@Data
@Setter
@Getter
public class TransOrderList {
    private TransOrder transOrder;
    private Integer isExcept;
    private String shopName;
    private Integer shopCount;

    @Override
    public String toString() {
        return "TransOrderList{" +
                "transOrder=" + transOrder +
                ", isExcept=" + isExcept +
                ", shopName='" + shopName + '\'' +
                ", shopCount=" + shopCount +
                '}';
    }
}
