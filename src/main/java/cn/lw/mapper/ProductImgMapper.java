package cn.lw.mapper;

import cn.lw.domain.ProductImg;
import java.util.List;

public interface ProductImgMapper {
    int deleteByPrimaryKey(Integer productImgId);

    int insert(ProductImg record);

    ProductImg selectByPrimaryKey(Integer productImgId);

    List<ProductImg> selectAll();

    int updateByPrimaryKey(ProductImg record);

    /**
     * 批量添加商品详情图片
     * @param productImgs
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgs);

    int deleteByProductId(int productId);
}