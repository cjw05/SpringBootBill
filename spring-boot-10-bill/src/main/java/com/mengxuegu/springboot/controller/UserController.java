package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.entities.User;
import com.mengxuegu.springboot.mapper.ProviderMapper;
import com.mengxuegu.springboot.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserMapper userMapper;

//    @RequestMapping(value = "/providers",method = RequestMethod.GET)

    @GetMapping("/users")
    public String list(Map<String,Object> map, User user){

        logger.info("供应商列表查询。。。"+user);

        List<User> users = userMapper.getUsers(user);


        map.put("users",users);

        return  "user/list";

    }

    @GetMapping("/user/{id}")
    public String view(@PathVariable("id") Integer id,@RequestParam(value = "type",defaultValue = "view") String type,Map<String,Object> map){
        logger.info("查询"+id+"的供应商详细信息");
        User userById = userMapper.getUserById(id);

        map.put("user",userById);
        return "user/"+type;

    }

    @PutMapping("/user")
    public String update(User user){
        userMapper.updateUser(user);


        return "redirect:/users";
    }

    @GetMapping("/user")
    public String toAddpage(){


        return "user/add";

    }

    @PostMapping("/user")
    public String add(User user){
        userMapper.addUser(user);
        return "redirect:/users";
    }


    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Integer id){
        logger.info("111111");
        userMapper.deleteUserById(id);
        return "redirect:/users";

    }

    @GetMapping("/user/pwd")
    public String toUpdatePwdPage(){
        return "main/password";
    }

    @ResponseBody
    @GetMapping("/user/pwd/{oldPwd}")
    public Boolean checkPwd(HttpSession session,@PathVariable("oldPwd") String oldPwd){

        User user =(User) session.getAttribute("loginUser");
        if(user.getPassword().equals(oldPwd)){
            return true;
        }
        return false;
    }


    @PostMapping("/user/pwd")
    public String updatePwd(HttpSession session,String password){
        User user =(User) session.getAttribute("loginUser");
        user.setPassword(password);
        userMapper.updateUser(user);
        return "redirect:/logout";


    }
}
