package com.xspirit.mmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xspirit.mmall.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *继承BaseMapper让其满足crud能力
 */
public interface CartMapper extends BaseMapper<Cart> {

}
