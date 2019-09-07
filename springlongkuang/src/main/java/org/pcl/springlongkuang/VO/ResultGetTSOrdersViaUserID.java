package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class ResultGetTSOrdersViaUserID {
    private List<TransOrderList> target;
    private Integer count;
    private String err;

    @Override
    public String toString() {
        return "ResultGetTSOrdersViaUserID{" +
                "target=" + target +
                ", count=" + count +
                ", err='" + err + '\'' +
                '}';
    }
}
