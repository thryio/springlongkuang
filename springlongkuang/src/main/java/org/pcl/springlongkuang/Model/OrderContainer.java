package org.pcl.springlongkuang.Model;

public class OrderContainer {
    private String Name;

    private int Count;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    @Override
    public String toString() {
        return "OrderContainer{" +
                "Name='" + Name + '\'' +
                ", Count=" + Count +
                '}';
    }
}
