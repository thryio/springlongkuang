package org.pcl.springlongkuang.Model;

import java.util.Date;

public class Order {
    private Integer id;

    private String orderId;

    private Integer driverId;

    private String containers;

    private Integer ownerId;

    private Integer cabinId;

    private String shopName;

    private Integer status;

    private Integer type;

    private String photos;

    private String transOrderId;

    private String creatorId;

    private String deliverAt;

    private String receiveAt;

    private String cabinUserId;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private Integer carId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getContainers() {
        return containers;
    }

    public void setContainers(String containers) {
        this.containers = containers == null ? null : containers.trim();
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getCabinId() {
        return cabinId;
    }

    public void setCabinId(Integer cabinId) {
        this.cabinId = cabinId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos == null ? null : photos.trim();
    }

    public String getTransOrderId() {
        return transOrderId;
    }

    public void setTransOrderId(String transOrderId) {
        this.transOrderId = transOrderId == null ? null : transOrderId.trim();
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getDeliverAt() {
        return deliverAt;
    }

    public void setDeliverAt(String deliverAt) {
        this.deliverAt = deliverAt == null ? null : deliverAt.trim();
    }

    public String getReceiveAt() {
        return receiveAt;
    }

    public void setReceiveAt(String receiveAt) {
        this.receiveAt = receiveAt == null ? null : receiveAt.trim();
    }

    public String getCabinUserId() {
        return cabinUserId;
    }

    public void setCabinUserId(String cabinUserId) {
        this.cabinUserId = cabinUserId == null ? null : cabinUserId.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
}