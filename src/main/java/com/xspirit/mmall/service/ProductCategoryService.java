package com.xspirit.mmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xspirit.mmall.entity.ProductCategory;
import com.xspirit.mmall.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<ProductCategoryVO> getAllProductCategoryVO();
}
