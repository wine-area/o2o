package cn.lw.services.Impl;

import cn.lw.domain.Product;
import cn.lw.domain.ProductImg;
import cn.lw.dto.ImageHolder;
import cn.lw.dto.ProductExecution;
import cn.lw.enums.ProductStateEnum;
import cn.lw.exceptions.ProductCategoryOperationException;
import cn.lw.exceptions.ProductOperationException;
import cn.lw.mapper.ProductImgMapper;
import cn.lw.mapper.ProductMapper;
import cn.lw.services.IProductService;
import cn.lw.utils.ImageUtil;
import cn.lw.utils.PageCalculator;
import cn.lw.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/7/4
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductImgMapper productImgMapper;

    /**
     * 添加商品
     * 首先处理缩略图,获得缩略图路径
     * 在保存商品 使用 useGeneratedKeys="true" keyColumn="product_id" keyProperty="productId"获取productId
     * 根据productId添加商品详情图列表
     * @param product 商品
     * @param thumbnail 商品缩略图
     * @param productList 商品详情图列表
     * @return
     * @throws ProductCategoryOperationException
     */
    @Override
    public ProductExecution insert(Product product,
                                   ImageHolder thumbnail,
                                   List<ImageHolder> productList)
            throws ProductCategoryOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() > 0) {
            product.setCreateTime( new Date() );
            product.setLastEditTime( new Date() );
            //默认商品为上架状态
            product.setEnableStatus( 1 );
            if (thumbnail != null) {
                addThumbnail( product, thumbnail );
            }
            try {
                int effectedLine = productMapper.insert( product );
                if (effectedLine <= 0) {
                    throw new ProductCategoryOperationException( "创建商品失败" );
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException( "创建商品失败:" + e.getMessage() );
            }
            if (productList != null && productList.size() > 0) {
                addProductImageList( product, productList );
            }
            return new ProductExecution( ProductStateEnum.SUCCESS );
        } else {

            return new ProductExecution( ProductStateEnum.EMPTY );
        }
    }

    @Override
    public Product selectByPrimaryKey(Integer productId) {
        return productMapper.selectByPrimaryKey( productId );
    }

    @Override
    public ProductExecution modifyProduct(Product product,
                                          ImageHolder thumbnail,
                                          List<ImageHolder> productImgs)
            throws ProductCategoryOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() > 0) {
            product.setLastEditTime( new Date() );
            if (thumbnail != null) {
                Product temp = productMapper.selectByPrimaryKey( product.getProductId() );
                if (temp.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath( temp.getImgAddr() );
                }
                addThumbnail( product, thumbnail );
            }
            if (productImgs != null&&productImgs.size()>0) {
                List<ProductImg> tempProductImgList = productMapper
                        .selectByPrimaryKey( product.getProductId() )
                        .getProductImgList();
                if (tempProductImgList != null) {
                    tempProductImgList.forEach( productImg ->
                            ImageUtil.deleteFileOrPath( productImg.getImgAddr() ) );
                }
                int effectedLine = productImgMapper.deleteByProductId( product.getProductId() );
                if (effectedLine < 1) {
                    throw new ProductOperationException( "删除数据库中该商品id:"
                            + product.getProductId() + "的详情图失败" );
                }
                addProductImageList( product, productImgs );
            }
            try {
                int effectedLine = productMapper.updateByPrimaryKey( product );
                if (effectedLine < 1) {
                    throw new ProductCategoryOperationException( "修改数据库中商品id:"
                            + product.getProductId() + "商品数据失败" );
                }
                return new ProductExecution( ProductStateEnum.SUCCESS );
            } catch (Exception e) {
                throw new ProductCategoryOperationException( "更新商品失败:" + e.getMessage() );
            }
        } else {
            return new ProductExecution( ProductStateEnum.EMPTY );
        }
    }

    @Override
    public ProductExecution queryProductList(Product productCondotion, int pageIndex, int pageSize)
            throws ProductCategoryOperationException {

        List<Product> products = productMapper.queryProductList(
                productCondotion, PageCalculator.calculateRowIndex( pageIndex, pageSize ), pageSize );
        int count = productMapper.selectCount( productCondotion );
        ProductExecution productExecution = new ProductExecution();
        productExecution.setCount( count );
        productExecution.setProducts( products );
        return productExecution;
    }

    private void addProductImageList(Product product, List<ImageHolder> producImgtList) {
        String dest = PathUtil.getShopImgagePath( product.getShop().getShopId() );
        List<ProductImg> productImgs = new LinkedList<>();
        producImgtList.forEach( (productImg)->{
            String imgAddr = ImageUtil.generateNormalImg( productImg, dest );
            ProductImg img = new ProductImg();
            img.setCreateTime( new Date() );
            img.setImgAddr( imgAddr );
            img.setProductId( product.getProductId() );
            productImgs.add( img );
        } );
        if (productImgs.size() > 0) {
            try {
                int effectedLine = productImgMapper.batchInsertProductImg( productImgs );
                if (effectedLine < 1) {
                    throw new ProductCategoryOperationException( "添加商品详情图失败" );
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException( "添加商品详情图失败 :" + e.getMessage() );
            }

        }
    }

    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImgagePath( product.getShop().getShopId() );
        String thumbnailAddr = ImageUtil.generateThumbnail( thumbnail, dest );
        product.setImgAddr( thumbnailAddr );
    }

    public ProductExecution selectAllByShopId(int shopId) throws ProductCategoryOperationException {

        return null;
    }
}
