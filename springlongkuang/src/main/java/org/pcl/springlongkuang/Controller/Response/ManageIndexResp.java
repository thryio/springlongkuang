package org.pcl.springlongkuang.Controller.Response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Data
@Getter
public class ManageIndexResp {
    private IndexOrder NRSOrder;
    private IndexOrder NRTOrder;
}
