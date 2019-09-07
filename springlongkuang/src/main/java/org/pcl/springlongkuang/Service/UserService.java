package org.pcl.springlongkuang.Service;

import org.pcl.springlongkuang.Controller.Request.ReqBindViaCode;
import org.pcl.springlongkuang.Controller.Response.RespBindViaCode;
import org.pcl.springlongkuang.Model.User;
import org.pcl.springlongkuang.Model.UserInfo;

import java.util.Map;

public interface UserService {
    Map<String,Object> Check(ReqBindViaCode req);
    public RespBindViaCode GetByUserID(int userID);

}
