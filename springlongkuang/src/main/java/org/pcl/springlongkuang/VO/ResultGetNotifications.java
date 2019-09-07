package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ResultGetNotifications {
    private Note note;
    private String err;

    @Override
    public String toString() {
        return "ResultGetNotifications{" +
                "note=" + note +
                ", err='" + err + '\'' +
                '}';
    }
}
