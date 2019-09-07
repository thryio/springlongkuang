package org.pcl.springlongkuang.Controller.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ReqPage {
    private String keyword;
    private Integer pageSize;
    private Integer pageNum;
    private String sortCondition;
    private String sortRule;
}
