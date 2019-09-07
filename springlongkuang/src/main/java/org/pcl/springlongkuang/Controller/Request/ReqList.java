package org.pcl.springlongkuang.Controller.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ReqList {
    private ReqPage page;
    private Integer state;
    private Integer type;
}
