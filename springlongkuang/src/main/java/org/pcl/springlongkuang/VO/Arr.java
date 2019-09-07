package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Arr {
    private int num;

    @Override
    public String toString() {
        return "Arr{" +
                "Num=" + num +
                '}';
    }
}
