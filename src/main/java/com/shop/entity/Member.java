package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@ToString
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

  @Id
  @Column(name = "member_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @Column(unique = true)
  private String email;

  private String password;

  private String address;

  @Enumerated(EnumType.STRING)
  private Role role;

  public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
    Member member = new Member();
    member.setName(memberFormDto.getName());
    member.setEmail(memberFormDto.getEmail());
    member.setAddress(memberFormDto.getAddress());
    String password = passwordEncoder.encode(memberFormDto.getPassword());
    member.setPassword(password);
    member.setRole(Role.ADMIN);
    return member;
  }
}
