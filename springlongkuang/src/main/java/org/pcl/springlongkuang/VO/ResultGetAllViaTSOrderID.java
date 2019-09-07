package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class ResultGetAllViaTSOrderID {
    private List<TransOrderDetail> details;
    private int count;

    @Override
    public String toString() {
        return "ResultGetAllViaTSOrderID{" +
                "details=" + details +
                ", count=" + count +
                '}';
    }
}


