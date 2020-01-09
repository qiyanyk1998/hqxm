package com.baizhi.yk.controller;

import com.baizhi.yk.entity.Admin;
import com.baizhi.yk.imgCode.CreateValidateCode;
import com.baizhi.yk.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("imgCode")
    // 验证码
    public void imgCode(HttpServletResponse response,HttpServletRequest request) {
        try {
            CreateValidateCode code1 = new CreateValidateCode();
            String code = code1.getCode();// 随机验证码
            code1.write(response.getOutputStream());// 把图片输出client
            // 把随机验证码 存入session
            HttpSession session = request.getSession(
                    true);
            session.setAttribute("ServerCode", code);

        } catch (Exception e) {
        }
    }

    @ResponseBody
    @RequestMapping("login")
    public Map login(String username, String password, HttpServletRequest request,String clientCode){
        HashMap hashMap = new HashMap();
        //构建查询条件
        String serverCode = request.getSession().getAttribute("ServerCode").toString();
        if (clientCode.equals(serverCode)) {
            //调用service查询
            Admin admin = adminService.selectOne();
            //判断情况封装错误消息
            if (admin == null) {
                hashMap.put("status", 400);
                hashMap.put("msg", "该用户不存在");
            } else if (!admin.getPassword().equals(password)) {
                hashMap.put("status", 400);
                hashMap.put("msg", "密码错误");
            } else {
                request.getSession().setAttribute("admin", admin);
                hashMap.put("status", 200);
            }
        }else{
            hashMap.put("status", 400);
            hashMap.put("msg", "验证码错误");
        }
        //返回集合
        return hashMap;
    }

//退出登入
    @RequestMapping("" +
            "exit")
    public String exit(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
        return "redirect:/jsp/login.jsp";
    }

}
