package cn.lw.mapper;

import cn.lw.domain.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryMapper {
    int deleteProductCategory(@Param("productCategoryId" ) Integer productCategoryId,
                              @Param( "shopId" )int shopId);

    int insert(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer productCategoryId);

    List<ProductCategory> selectAll();

    int updateByPrimaryKey(ProductCategory record);

    List<ProductCategory> selectAllByShopId(int shopId);

    /**
     * 批量添加产品分类信息
     * @param productCategories
     * @return 影响行数
     */
    int batchInsertProductCategory(List<ProductCategory> productCategories);


}