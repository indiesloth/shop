package com.shop.dto;

import com.shop.constant.OrderStatus;
import com.shop.entity.Order;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class OrderHistDto {

  private Long orderId;
  private String orderDate;
  private OrderStatus orderStatus;
  private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

  public OrderHistDto(Order order) {
    this.orderId = order.getId();
    this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    this.orderStatus = order.getOrderStatus();
  }

  public void addOrderItemDto(OrderItemDto orderItemDto) {
    orderItemDtoList.add(orderItemDto);
  }
}
