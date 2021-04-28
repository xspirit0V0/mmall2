package com.xspirit.mmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xspirit.mmall.entity.Order;
import com.xspirit.mmall.entity.User;
import com.xspirit.mmall.vo.OrderVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface OrderService extends IService<Order> {
    public boolean save(Order orders, User user, String address, String remark);
    public List<OrderVO> findAllOrederVOByUserId(Integer id);
}
