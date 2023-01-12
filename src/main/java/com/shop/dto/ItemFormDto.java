package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ItemFormDto {

  private static ModelMapper modelMapper = new ModelMapper();
  private Long id;
  @NotBlank(message = "상품명은 필수 입력 값입니다.")
  private String itemNm;
  @NotNull(message = "가격은 필수 입력 값입니다.")
  private Integer price;
  @NotBlank(message = "이름은 필수 입력 값입니다.")
  private String itemDetail;
  @NotNull(message = "재고는 필수 입력 값입니다.")
  private Integer stockNumber;
  private ItemSellStatus itemSellStatus;
  private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
  private List<Long> itemImgIds = new ArrayList<>();

  public static ItemFormDto of(Item item) {
    return modelMapper.map(item, ItemFormDto.class);
  }

  public Item createItem() {
    return modelMapper.map(this, Item.class);
  }
}
