package com.shop.controller;

import com.shop.dto.CartItemDto;
import com.shop.service.CartService;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class CartController {

  private final CartService cartService;

  @PostMapping(value = "/cart")
  public @ResponseBody ResponseEntity<?> order(@RequestBody @Valid CartItemDto cartItemDto,
      BindingResult bindingResult, Principal principal) {
    if (bindingResult.hasErrors()) {
      StringBuilder sb = new StringBuilder();
      List<FieldError> fiedlErrors = bindingResult.getFieldErrors();
      for (FieldError fieldError : fiedlErrors) {
        sb.append(fieldError.getDefaultMessage());
      }
      return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
    }
    String email = principal.getName();
    Long cartItemId;

    try {
      cartItemId = cartService.addCart(cartItemDto, email);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(cartItemId, HttpStatus.OK);
  }
}
