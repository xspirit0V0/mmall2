package com.xspirit.mmall.Controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xspirit.mmall.entity.Product;
import com.xspirit.mmall.entity.User;
import com.xspirit.mmall.service.CartService;
import com.xspirit.mmall.service.ProductCategoryService;
import com.xspirit.mmall.service.ProductService;
import com.xspirit.mmall.vo.TableDataVO;
import com.xspirit.mmall.vo.TableProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@Controller
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CartService cartService;

    @GetMapping("/list/{type}/{id}")
    public ModelAndView list(
            @PathVariable("type") String type,
            @PathVariable("id") Integer id,
            HttpSession session
    ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        modelAndView.addObject("productList",productService.findByCategoryId(type,id));
        modelAndView.addObject("list",productCategoryService.getAllProductCategoryVO());
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
            modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }

    @PostMapping("/findByKey")
    public ModelAndView findByKey(String keyWord,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        //根据关键字查询
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("name",keyWord);
        modelAndView.addObject("productList",productService.list(wrapper));
        modelAndView.addObject("list",productCategoryService.getAllProductCategoryVO());
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
            modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") Integer id,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetail");
        modelAndView.addObject("product",productService.getById(id));
        modelAndView.addObject("list",productCategoryService.getAllProductCategoryVO());
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
            modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }

    @RequestMapping("/findAllTableProduct")
    @ResponseBody
    public TableDataVO<TableProductVO> findAllTableProduct(Integer page, Integer limit){
        return productService.findAllTableData(page, limit);
    }

    @GetMapping("/findAllProduct")
    public ModelAndView findAllProduct(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productUpdate");
        modelAndView.addObject("alllist",productService.list());
        return modelAndView;
    }

    @PostMapping("/updateProduct/{id}/{stock}")
    @ResponseBody
    public String updateProduct(
            @PathVariable("id") Integer id,
            @PathVariable("stock") Integer stock){
        Product product = productService.getById(id);
        product.setStock(stock);
        boolean b = productService.updateById(product);
        if (b == true){
            return "success";
        }else {
            return "fail";
        }
    }
    @PostMapping("/updatePrice/{id}/{price}")
    public String updatePrice(@PathVariable("id") Integer id,
                              @PathVariable("price") Float price){
        Product product = productService.getById(id);
        product.setPrice(price);
        boolean b = productService.updateById(product);
        return "redirect:/product/findAllProduct";
    }

    @PostMapping("/findOneProduct")
    public ModelAndView findOneProduct(String productsname){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productUpdate");

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("name",productsname);
        modelAndView.addObject("onelist",productService.list(wrapper));
        return modelAndView;
    }
}

