package com.shop.dto;

import com.shop.entity.ItemImg;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ItemImgDto {

  private static ModelMapper modelMapper = new ModelMapper();
  private Long id;
  private String imgName;
  private String oriImgName;
  private String imgUrl;
  private String repImgYn;

  public static ItemImgDto of(ItemImg itemImg) {
    return modelMapper.map(itemImg, ItemImgDto.class);
  }
}
