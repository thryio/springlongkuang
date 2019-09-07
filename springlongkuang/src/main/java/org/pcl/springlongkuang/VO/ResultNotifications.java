package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ResultNotifications {
    private List<Arr> arr;
    private String err;

    @Override
    public String toString() {
        return "ResultNotifications{" +
                "arr=" + arr +
                ", err='" + err + '\'' +
                '}';
    }
}
