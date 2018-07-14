package cn.lw.mapper;

import cn.lw.domain.ProductCategory;
import cn.lw.domain.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryMapper {
    int deleteByPrimaryKey(Integer shopCategoryId);

    int insert(ShopCategory record);

    ShopCategory selectByPrimaryKey(Integer shopCategoryId);

    List<ShopCategory> selectAll(@Param( "ShopCategoryCondition" )
                                         ShopCategory ShopCategoryCondition);

    int updateByPrimaryKey(ShopCategory record);



}