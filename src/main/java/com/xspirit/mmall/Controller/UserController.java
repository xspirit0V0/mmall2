package com.xspirit.mmall.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xspirit.mmall.entity.User;
import com.xspirit.mmall.enums.GenderEnum;
import com.xspirit.mmall.service.CartService;
import com.xspirit.mmall.service.UserService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    /**
     * 注册
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/register")
    public String register(User user, Model model){
        boolean result = false;
        try {
            if(user.getGenderCode() == 1){
                user.setGender(GenderEnum.MAN);
            }
            if(user.getGenderCode() == 0){
                user.setGender(GenderEnum.WOMAN);
            }
            result = userService.save(user);
        } catch (Exception e) {
            model.addAttribute("error",user.getLoginName()+"已存在！请重新输入！");
            return "register";
        }
        if(result) return "login";
        return "register";
    }

    /**
     * 登录
     * @param loginName
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(String loginName, String password, HttpSession session,Model model){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name",loginName);
        wrapper.eq("password",password);
        User user = userService.getOne(wrapper);
        if(user == null){
            model.addAttribute("msg","请输入正确的用户名和密码");
            return "login";
        }else{
            session.setAttribute("user",user);
            return "redirect:/productCategory/list";
        }
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    /**
     * 用户信息
     */
    @GetMapping("/userInfo")
    public ModelAndView userInfo(HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userInfo");
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }

    @GetMapping("/alluser")
    public ModelAndView allUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userAll");
        modelAndView.addObject("users",userService.list());
        return modelAndView;
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.removeById(id);
        return "redirect:/user/alluser";
    }

    @GetMapping("/updatePassword")
    public ModelAndView updatePassword(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updatePassword");
        return modelAndView;
    }

    @PostMapping("/updatePasswordById/{id}")
    public ModelAndView updatePasswordById(@PathVariable("id") Integer id,
                                           String oldpassword,
                                           String newpassword,
                                           String newpassword2){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getById(id);
        if (oldpassword.equals(newpassword)) {
            modelAndView.addObject("err3","修改前后的密码不能相同");
            modelAndView.setViewName("updatePassword");
        }
        else{
            if (user.getPassword().equals(oldpassword)) {
                if (newpassword.equals(newpassword2)) {
                    user.setPassword(newpassword);
                    userService.updateById(user);
                    modelAndView.setViewName("login");
                } else {
                    modelAndView.addObject("err1", "请输入两次相同的密码");
                    modelAndView.setViewName("updatePassword");
                }
            } else {
                modelAndView.addObject("err2", "请确认您的密码与当前密码一致");
                modelAndView.setViewName("updatePassword");
            }
        }
        return modelAndView;
    }

}

