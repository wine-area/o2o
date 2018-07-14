package cn.lw.services.Impl;

import cn.lw.domain.Shop;
import cn.lw.dto.ImageHolder;
import cn.lw.dto.ShopExecution;
import cn.lw.enums.ShopStateEnum;
import cn.lw.exceptions.ShopOperationException;
import cn.lw.mapper.ShopMapper;
import cn.lw.services.IShopOperationService;
import cn.lw.utils.ImageUtil;
import cn.lw.utils.PageCalculator;
import cn.lw.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/6/18
 */
@Service
public class ShopOperationServiceImpl implements IShopOperationService {
    @Autowired
    private ShopMapper shopMapper;

    @Override
    public int deleteByPrimaryKey(Integer shopId) {
        return shopMapper.deleteByPrimaryKey( shopId );
    }

    @Override
    public ShopExecution insert(Shop shop,ImageHolder thunmbnail) {
        //空置判断   应补充shop类中的对象的空值判断  area personInfo...
        if (shop == null) {
            return new ShopExecution( ShopStateEnum.NULL_SHOP );
        }
        try {
            shop.setEnableStatus( 0 );
            shop.setCreateTime( new Date() );
            shop.setLastEditTime( new Date() );
            int effectedLine = shopMapper.insert( shop );
            if (effectedLine <= 0) {
                //RuntimeException回滚事务
                throw new ShopOperationException( "店铺创建失败" );
            } else {
                if (thunmbnail.getImage() != null) {
                    try {
                        addShopImg( shop, thunmbnail);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ShopOperationException( " addShopImg ERROR   " + e.getMessage() );
                    }
                    //更新店铺图片地址
                    effectedLine = shopMapper.updateByPrimaryKey( shop );
                    if (effectedLine <= 0) {
                        //RuntimeException回滚事务
                        throw new ShopOperationException( "更新图片地址失败" );
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException( "insertShop ERROR " + e.getMessage() );
        }
        return new ShopExecution( ShopStateEnum.CHECK );
    }

    private void addShopImg(Shop shop, ImageHolder thunmbnail) {
        //获取shop图片的相对路径
        String dest = PathUtil.getShopImgagePath( shop.getShopId() );
        String shopImgAddr = ImageUtil.generateThumbnail( thunmbnail, dest );
        shop.setShopImg( shopImgAddr );
    }

    @Override
    public Shop selectByPrimaryKey(Integer shopId) {
        return shopMapper.selectByPrimaryKey( shopId );
    }

    @Override
    public List<Shop> selectAll() {
        return shopMapper.selectAll();
    }

    /**
     * 1.判断是否需要处理图片
     * 2.更新店铺信息
     *
     * @param shop
     * @param thunmbnail
     * @return
     * @throws ShopOperationException
     */
    @Override
    public ShopExecution modifyShop(Shop shop, ImageHolder thunmbnail)
            throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            // 1.判断是否需要处理图片
            try {
                if (thunmbnail != null && thunmbnail.getFileName() != null&&thunmbnail.getImage()!=null
                        && !"".equals(thunmbnail.getFileName()) ){
                    Shop tempShop = shopMapper.selectByPrimaryKey(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, thunmbnail);
                }
                // 2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopMapper.updateByPrimaryKey(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopMapper.selectByPrimaryKey(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                e.printStackTrace();

                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }
        }
    }

    @Override
    public ShopExecution queryShopList(Shop shopCondition, int pageIndex, int pageSize) {
        List<Shop> shopList = shopMapper.queryShopList( shopCondition,
                PageCalculator.calculateRowIndex( pageIndex, pageSize ),
                pageSize );
        int count = shopMapper.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null) {
            se.setShopList( shopList );
            se.setCount( count );
        } else {
            se.setState( ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }
}
