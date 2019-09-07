package org.pcl.springlongkuang.Controller.Response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Recipient {
    private String role;
    private String shopName;
    private String operatorName;
    private String receiveTime;
}
