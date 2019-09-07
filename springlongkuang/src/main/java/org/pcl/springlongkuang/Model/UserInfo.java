package org.pcl.springlongkuang.Model;

import java.util.List;

public class UserInfo {
    private User user;
    private Role role;
    private List<Permission> permission;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Permission> getPermission() {
        return permission;
    }

    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user=" + user +
                ", role=" + role +
                ", permission=" + permission +
                '}';
    }
}


