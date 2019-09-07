package org.pcl.springlongkuang.Controller.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class QiNiuToken {
    private String access_key;
    private String secret_key;
    private String bucket;

    @Override
    public String toString() {
        return "QiNiuToken{" +
                "access_key='" + access_key + '\'' +
                ", secret_key='" + secret_key + '\'' +
                ", bucket='" + bucket + '\'' +
                '}';
    }
}
