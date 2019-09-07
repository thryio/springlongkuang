package org.pcl.springlongkuang.Controller.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ReqBindViaCode {
    private String account;
    private String password;
    private String nickname;
    private String avatar_url;
    private String code;
}
