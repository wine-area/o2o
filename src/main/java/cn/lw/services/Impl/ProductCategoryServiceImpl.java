package cn.lw.services.Impl;

import cn.lw.domain.ProductCategory;
import cn.lw.dto.ProductCategoryExecution;
import cn.lw.enums.ProductCategoryStateEnum;
import cn.lw.exceptions.ProductCategoryOperationException;
import cn.lw.mapper.ProductCategoryMapper;
import cn.lw.mapper.ProductMapper;
import cn.lw.services.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/6/24
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
    @Autowired
    ProductCategoryMapper productCategoryMapper;
    @Autowired
    ProductMapper productMapper;
    @Override
    public List<ProductCategory> selectAllByShopId(Integer shopId) {
        return productCategoryMapper.selectAllByShopId( shopId );
    }

    @Override
    public ProductCategoryExecution batchInsertProductCategory(List<ProductCategory> productCategories) throws ProductCategoryOperationException {
        if (productCategories != null && productCategories.size() > 0) {
            try {
                int effectedLine = productCategoryMapper.batchInsertProductCategory( productCategories );
                if (effectedLine < 1) {
                    throw new ProductCategoryOperationException( "店铺类别创建失败" );
                } else {
                    return new ProductCategoryExecution( ProductCategoryStateEnum.SUCCESS );
                }
            }catch (Exception e){
                throw new ProductCategoryOperationException( "batchInsertProductCategory error"
                        + e.getMessage() );
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }
    /**
     * 先解除商品与商品类别关系
     * 根据商品类别id和店铺id删除商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    @Override
    public ProductCategoryExecution deleteProductCategory(Integer productCategoryId, int shopId) {

        try {
            int updateEffectedLine = productMapper.updateProductCategory2Null( productCategoryId );
            if (updateEffectedLine < 0) {
                throw new ProductCategoryOperationException( "更新商品类别失败" );
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException( "deleteProductCategory error :" + e.getMessage() );
        }
        try {
            int effectedLine = productCategoryMapper.deleteProductCategory( productCategoryId, shopId );
            if (effectedLine < 1) {
                throw new ProductCategoryOperationException( "删除商品类别失败" );
            } else {
                    return new ProductCategoryExecution( ProductCategoryStateEnum.SUCCESS );
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException( "deleteProductCategory error :" + e.getMessage() );
        }
    }
}
