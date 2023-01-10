package com.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item_img")
public class ItemImg extends BaseEntity {
  @Id
  @Column(name = "item_img_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String imgName;

  private String oriImgName;

  private String imgUri;

  private String repimgYn;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  public void updateItemImg(String oriImgName, String imgName, String imgUri) {
    this.oriImgName = oriImgName;
    this.imgName = imgName;
    this.imgUri = imgUri;
  }
}
