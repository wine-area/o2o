package cn.lw.services;

import cn.lw.domain.Product;
import cn.lw.dto.ImageHolder;
import cn.lw.dto.ProductExecution;
import cn.lw.exceptions.ProductCategoryOperationException;

import java.io.InputStream;
import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services
 * @date 2018/7/4
 */
public interface IProductService {

    ProductExecution insert(Product product, ImageHolder thumbnail,List<ImageHolder> productList)
            throws ProductCategoryOperationException;

    Product selectByPrimaryKey(Integer productId);

    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgs)
            throws ProductCategoryOperationException;

    ProductExecution queryProductList(Product productCondotion,int pageIndex,int pageSize)
            throws ProductCategoryOperationException;
}
