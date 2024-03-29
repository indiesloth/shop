package com.shop.service;

import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemImgService {

  private final ItemImgRepository itemImgRepository;
  private final FileService fileService;
  @Value("${itemImgLocation}")
  private String itemImgLocation;

  public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
    String oriImgName = itemImgFile.getOriginalFilename();
    String imgName = "";
    String imgUrl = "";

    // 파일 업로드
    if (!StringUtils.isEmpty(oriImgName)) {
      imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
      imgUrl = "/images/item/" + imgName;
    }

    // 상품 이미지 정보 저장
    itemImg.updateItemImg(oriImgName, imgName, imgUrl);
    itemImgRepository.save(itemImg);
  }

  public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception {
    if (!itemImgFile.isEmpty()) {
      ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
          .orElseThrow(EntityNotFoundException::new);

      // 기존 이미지 파일 삭제
      if (!StringUtils.isEmpty(savedItemImg.getImgName())) {
        fileService.deleteFile(itemImgLocation + "/" + savedItemImg.getImgName());
      }

      String oriImgName = itemImgFile.getOriginalFilename();
      String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
      String imgUrl = "/images/item/" + imgName;
      savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
    }
  }
}
