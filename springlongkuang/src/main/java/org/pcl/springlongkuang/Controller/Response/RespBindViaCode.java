package org.pcl.springlongkuang.Controller.Response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Model.Permission;
import org.pcl.springlongkuang.Model.Role;
import org.pcl.springlongkuang.Model.User;
import java.util.List;

@Setter
@Getter
@Data
public class RespBindViaCode {
    private User user;
    private Role role;
    private List<Permission> permissions;
    private String token;
}
