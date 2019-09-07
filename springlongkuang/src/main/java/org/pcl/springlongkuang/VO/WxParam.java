package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class WxParam {
    private String OpenID;
    private String SessionKey;
    private String UnionID;
    private String ErrCode;
    private String ErrMsg;
}
