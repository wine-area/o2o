package cn.lw.services;

import cn.lw.domain.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/6/19
 */
public interface IShopCategoryService {
    int deleteByPrimaryKey(Integer shopCategoryId);

    int insert(ShopCategory record);

    ShopCategory selectByPrimaryKey(Integer shopCategoryId);

    List<ShopCategory> selectAll(ShopCategory ShopCategoryCondition);

    int updateByPrimaryKey(ShopCategory record);
}
