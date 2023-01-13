package com.shop.dto;

import java.util.List;
import lombok.Data;

@Data
public class CartOrderDto {

  private Long cartItemId;
  private List<CartOrderDto> cartOrderDtoList;
}
