package com.baizhi.yk.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

  private String id;
  private String phone;
  private String password;
  private String salt;
  private String status;
  private String photo;
  private String name;
  private String nickName;
  private String sex;
  private String sign;
  private String location;
  @JSONField(format = "yyyy-MM-dd")
  @DateTimeFormat(pattern ="yyyy-MM-dd")
  private java.util.Date rigestDate;
  @JSONField(format = "yyyy-MM-dd")
  @DateTimeFormat(pattern ="yyyy-MM-dd")
  private java.util.Date lastLogin;

}
