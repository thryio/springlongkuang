package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Controller.Response.TransOrderDetailResp;

@Data
@Setter
@Getter
public class ResultGetOrdersViaTSOrderID {
    private TransOrderDetailResp transOrderDetailResp;
    private Integer count;
    private String errMsg;

    @Override
    public String toString() {
        return "ResultGetOrdersViaTSOrderID{" +
                "transOrderDetailResp=" + transOrderDetailResp +
                ", count=" + count +
                ", err='" + errMsg + '\'' +
                '}';
    }
}
