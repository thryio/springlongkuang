package org.pcl.springlongkuang.Model;

public class ShopOwner {
    private Integer id;

    private String shopOwnerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopOwnerName() {
        return shopOwnerName;
    }

    public void setShopOwnerName(String shopOwnerName) {
        this.shopOwnerName = shopOwnerName == null ? null : shopOwnerName.trim();
    }
}