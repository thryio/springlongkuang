package org.pcl.springlongkuang.Model;

public class Shop {
    private Integer id;

    private String shopName;

    private String shopNum;

    private Integer ownerId;

    private Integer shopOwnerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopNum() {
        return shopNum;
    }

    public void setShopNum(String shopNum) {
        this.shopNum = shopNum == null ? null : shopNum.trim();
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getShopOwnerId() {
        return shopOwnerId;
    }

    public void setShopOwnerId(Integer shopOwnerId) {
        this.shopOwnerId = shopOwnerId;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", shopNum='" + shopNum + '\'' +
                ", ownerId=" + ownerId +
                ", shopOwnerId=" + shopOwnerId +
                '}';
    }
}