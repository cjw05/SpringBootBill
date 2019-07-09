package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.entities.User;
import com.mengxuegu.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
public class LoginController {


    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public String login(HttpSession session,String username, String password, Map<String,Object> map){

        if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            User user = userMapper.getUserByName(username);
            if(user!=null && user.getPassword().equals(password)){
                session.setAttribute("loginUser",user);
                return "redirect:/main.html";
            }
            map.put("msg","用户名或密码错误");
            return "main/login";
        }
        map.put("msg","用户名或密码不能为空");
        return "main/login";




    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        session.invalidate();
        return "redirect:/index.html";
    }


}
