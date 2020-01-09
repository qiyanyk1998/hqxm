package com.baizhi.yk.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Banner {
  @Id
@ExcelProperty(value = "ID")
  private String id;
  @ExcelProperty(value = "标题")
  private String title;
  @ExcelProperty(value = "图片",converter = ImageConverter.class)
  private String url;
  @ExcelProperty(value = "路径")
  private String href;
  @ExcelProperty(value = "发布时间")
  @JSONField(format = "yyyy-MM-dd")
  @DateTimeFormat(pattern ="yyyy-MM-dd")
  private Date createDate;
  @ExcelProperty(value = "描述")
  private String desction;
  @ExcelProperty(value = "状态")
  private String status;

}
