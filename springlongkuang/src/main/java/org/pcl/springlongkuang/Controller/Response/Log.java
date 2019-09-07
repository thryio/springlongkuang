package org.pcl.springlongkuang.Controller.Response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Data
public class Log {
    private String creatorName;
    private String creatorRole;
    private String creatorShop;
    private String creatTime;
    private String driverName;
    private String DeliverTime;
    List<Recipient> recipients;
}
