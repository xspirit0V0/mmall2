package com.xspirit.mmall.Controller;


import com.xspirit.mmall.entity.Order;
import com.xspirit.mmall.entity.User;
import com.xspirit.mmall.service.CartService;
import com.xspirit.mmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @PostMapping("/settlement3")
    public ModelAndView settlement3(Order orders, HttpSession session, String address, String remark){

        User user = (User) session.getAttribute("user");
        //同时操作两张表 进行信息注入
        orderService.save(orders, user, address, remark);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement3");
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        modelAndView.addObject("orders",orders);
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("list",orderService.findAllOrederVOByUserId(user.getId()));
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }
}

