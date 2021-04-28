package com.xspirit.mmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xspirit.mmall.entity.Cart;
import com.xspirit.mmall.vo.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *继承Iservice让接口有crud能力
 */
public interface CartService extends IService<Cart> {
    public List<CartVO> findAllCartVOByUserId(Integer id);
}
