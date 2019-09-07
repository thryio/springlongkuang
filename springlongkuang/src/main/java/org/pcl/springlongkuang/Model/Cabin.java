package org.pcl.springlongkuang.Model;

public class Cabin {
    private Integer id;

    private String cabinName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCabinName() {
        return cabinName;
    }

    public void setCabinName(String cabinName) {
        this.cabinName = cabinName == null ? null : cabinName.trim();
    }
}