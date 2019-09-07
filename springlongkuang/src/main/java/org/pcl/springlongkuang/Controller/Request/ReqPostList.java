package org.pcl.springlongkuang.Controller.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReqPostList {
    private ReqPage page;
    private Integer state;
    private Integer type;


    @Override
    public String toString() {
        return "ReqPostList{" +
                "page=" + page +
                ", state=" + state +
                ", type=" + type +
                '}';
    }
}
