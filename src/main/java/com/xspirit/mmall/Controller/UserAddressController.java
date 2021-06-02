package com.xspirit.mmall.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xspirit.mmall.entity.User;
import com.xspirit.mmall.entity.UserAddress;
import com.xspirit.mmall.service.CartService;
import com.xspirit.mmall.service.UserAddressService;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@Controller
@RequestMapping("/userAddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private CartService cartService;

    @GetMapping("/list")
    public ModelAndView list(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userAddressList");
        User user = (User)session.getAttribute("user");
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getId());
        modelAndView.addObject("addressList",userAddressService.list(wrapper));
        return modelAndView;
    }
    @PostMapping("/addone")
    public String AddoneAddress(@RequestParam("address") String newaddress,
                                @RequestParam("remark") String remark,
                                @RequestParam("userId") Integer userid){
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userid);
        userAddress.setAddress(newaddress);
        userAddress.setRemark(remark);
        userAddressService.save(userAddress);
        return "redirect:/userAddress/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") Integer id){
        userAddressService.removeById(id);
        return "redirect:/userAddress/list";
    }
}

