package cn.lw.mapper;

import cn.lw.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    Product selectByPrimaryKey(Integer productId);

    List<Product> queryProductList(@Param("productCondition") Product productCondition,
                                    @Param("rowIndex") int rowIndex,
                                    @Param("pageSize") int pageSize);

    int selectCount(@Param("productCondition") Product productCondition);


    int updateByPrimaryKey(Product record);

    int updateProductCategory2Null(int productCategoryId);
}