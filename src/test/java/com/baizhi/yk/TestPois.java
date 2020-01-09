package com.baizhi.yk;

import com.alibaba.excel.EasyExcel;
import com.baizhi.yk.dao.BannerDao;
import com.baizhi.yk.entity.Banner;
import com.baizhi.yk.entity.BannerListener1;
import com.baizhi.yk.service.BannerService;
import com.baizhi.yk.util.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestPois {
    @Autowired
    BannerDao bannerDao;
    BannerService bannerService;
//    @Test
//    public void test(){
//        //1.创建Excle文档
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        //2.创建一个工作薄
//        HSSFSheet sheet = workbook.createSheet();
//        //3.创建行对象
//        HSSFRow row = sheet.createRow(0);
//        //4.创建单元格
//        HSSFCell cell = row.createCell(0);
//        //5.给单元格设置内容
//        cell.setCellValue("杨科真好");
//        //6.导出单元格
//        try {
//            workbook.write(new FileOutputStream(new File("E://TestPoi.xls")));
//            //7.释放资源
//            workbook.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }

    //导出
//    @Test
//    public void test2(){
//        List<Banner> banners = bannerDao.selectAll();
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet();
//        HSSFRow row = sheet.createRow(0);
//        String[] str = {"ID","标题","图片","超链接","创建时间","描述","状态"};
//        for(int i=0;i<str.length;i++){
//            String s=str[i];
//            row.createCell(i).setCellValue(s);
//        }
//        //通过workbook对象获取样式对象
//        HSSFCellStyle cellStyle = workbook.createCellStyle();
//        //通过workbook对象获取数据格式化处理对象
//        HSSFDataFormat dataFormat = workbook.createDataFormat();
//        //指定格式化样式
//        short format = dataFormat.getFormat("yyyy-MM-dd");
//        //为样式对象 设置格式化处理
//        cellStyle.setDataFormat(format);
//
//        HSSFCell cell = row.createCell(0);
//    }

    //导出
    @Test
    public void test1(){
        List<Banner> banners = bannerDao.selectAll();
        String fileName="E://easyexcel.xls";
        EasyExcel.write(fileName,Banner.class
        ).sheet("测试")
                .doWrite(banners);
    }

    //导入
    @Test
    public void uploadBanner(MultipartFile url, HttpSession session, HttpServletRequest request){
        //HashMap hashMap = new HashMap();
        String http = HttpUtil.getHttp(url, request, "/upload/xls/");
        // 文件上传 工具类完成
        // 更新数据库信息
        Banner banner = new Banner();
        banner.setUrl(http);
        String fileName="http";
        EasyExcel.read(fileName, Banner.class, new BannerListener1()).sheet().doRead();
        //return hashMap;
    }
}
