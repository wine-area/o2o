package cn.lw.services;

import cn.lw.domain.Shop;
import cn.lw.dto.ImageHolder;
import cn.lw.dto.ShopExecution;
import cn.lw.enums.ShopStateEnum;
import cn.lw.exceptions.ShopOperationException;
import org.apache.ibatis.annotations.Param;

import java.io.InputStream;
import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services
 * @date 2018/6/18
 */
public interface IShopOperationService {
    int deleteByPrimaryKey(Integer shopId);

    /**
     * 新增店铺
     *
     * @param shop
     * @param thunmbnail
     * @return 受影响的行数
     */
    ShopExecution insert(Shop shop, ImageHolder thunmbnail);

    Shop selectByPrimaryKey(Integer shopId);

    List<Shop> selectAll();

    ShopExecution modifyShop(Shop shop,  ImageHolder thunmbnail)
            throws ShopOperationException;

    /**
     * 分页条件查询
     *
     * @param shopCondition 店铺信息:可以根据店铺名字,店铺主人,类别,区域Id店铺类别进行查询
     * @param pageIndex      页数
     * @param pageSize      每页大小
     * @return
     */
    ShopExecution queryShopList(Shop shopCondition,
                                int pageIndex,
                                int pageSize);
}
