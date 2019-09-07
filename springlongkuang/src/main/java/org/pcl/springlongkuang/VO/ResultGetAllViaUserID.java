package org.pcl.springlongkuang.VO;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class ResultGetAllViaUserID {
    private List<OrderDetail> details;

    private int count;
}
