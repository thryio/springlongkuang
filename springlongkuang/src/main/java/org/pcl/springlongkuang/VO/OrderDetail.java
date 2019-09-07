package org.pcl.springlongkuang.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pcl.springlongkuang.Model.Car;
import org.pcl.springlongkuang.Model.Driver;
import org.pcl.springlongkuang.Model.Exception;
import org.pcl.springlongkuang.Model.Order;

@Data
@Setter
@Getter
public class OrderDetail {
   private Order order;
   private Driver driver;
   private Car car;
   private Exception exception;

   @Override
   public String toString() {
      return "OrderDetail{" +
              "order=" + order +
              ", driver=" + driver +
              ", car=" + car +
              ", exception=" + exception +
              '}';
   }
}
