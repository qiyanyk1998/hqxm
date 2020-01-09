package com.baizhi.yk.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class Admin  implements Serializable {

  private String id;
  private String username;
  private String password;

}
