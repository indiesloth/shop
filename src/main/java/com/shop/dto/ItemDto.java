package com.shop.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ItemDto {

  private Long id;
  private String itemNm;
  private Integer price;
  private String itemDetail;
  private String sellStatCd;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
