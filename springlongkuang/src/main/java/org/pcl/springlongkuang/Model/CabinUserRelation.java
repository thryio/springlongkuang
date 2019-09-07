package org.pcl.springlongkuang.Model;

public class CabinUserRelation {
    private Integer id;

    private Integer cabinId;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCabinId() {
        return cabinId;
    }

    public void setCabinId(Integer cabinId) {
        this.cabinId = cabinId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}