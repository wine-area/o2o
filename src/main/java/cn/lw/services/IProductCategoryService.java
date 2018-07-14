package cn.lw.services;

import cn.lw.domain.ProductCategory;
import cn.lw.dto.ProductCategoryExecution;
import cn.lw.exceptions.ProductCategoryOperationException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/6/24
 */
public interface IProductCategoryService {

    List<ProductCategory> selectAllByShopId(Integer shopId);

    ProductCategoryExecution batchInsertProductCategory(List<ProductCategory> productCategories)
            throws ProductCategoryOperationException;


    /**
     * 根据商品类别id和店铺id删除商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(Integer productCategoryId, int shopId)
            throws ProductCategoryOperationException;
}
