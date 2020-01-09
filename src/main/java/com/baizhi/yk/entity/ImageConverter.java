package com.baizhi.yk.entity;

import com.alibaba.excel.converters.string.StringImageConverter;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.FileUtils;


import java.io.File;
import java.io.IOException;

public class ImageConverter extends StringImageConverter{
    @Override
    public CellData convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws IOException {
        //需要将value由相对路径|网络路径改为绝对路径
        String property = System.getProperty("user.dir");
        String[] split = value.split("/");
        value= split[split.length - 1];
        String url=property+"\\src\\main\\webapp\\upload\\img\\"+value;
        return new CellData(FileUtils.readFileToByteArray(new File(url)));
    }
}
