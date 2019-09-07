package org.pcl.springlongkuang.Model;

public class Car {
    private Integer id;

    private String carNum;

    private String gpsDevice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum == null ? null : carNum.trim();
    }

    public String getGpsDevice() {
        return gpsDevice;
    }

    public void setGpsDevice(String gpsDevice) {
        this.gpsDevice = gpsDevice == null ? null : gpsDevice.trim();
    }
}