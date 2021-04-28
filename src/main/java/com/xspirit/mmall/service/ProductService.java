package com.xspirit.mmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xspirit.mmall.entity.Product;
import com.xspirit.mmall.vo.TableDataVO;
import com.xspirit.mmall.vo.TableProductVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface ProductService extends IService<Product> {
    public List<Product> findByCategoryId(String type, Integer categoryId);

    /**
     * 后台管理系统返回商品数据
     * @return
     */
    public TableDataVO<TableProductVO> findAllTableData(Integer page, Integer limit);
}
