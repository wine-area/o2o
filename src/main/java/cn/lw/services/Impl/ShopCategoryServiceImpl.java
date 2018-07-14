package cn.lw.services.Impl;

import cn.lw.domain.ShopCategory;
import cn.lw.mapper.ShopCategoryMapper;
import cn.lw.services.IShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/6/19
 */
@Service
public class ShopCategoryServiceImpl implements IShopCategoryService {
    @Autowired
    private ShopCategoryMapper shopCategoryMapper;
    @Override
    public int deleteByPrimaryKey(Integer shopCategoryId) {
        return 0;
    }

    @Override
    public int insert(ShopCategory record) {
        return 0;
    }

    @Override
    public ShopCategory selectByPrimaryKey(Integer shopCategoryId) {
        return null;
    }

    @Override
    public List<ShopCategory> selectAll(ShopCategory ShopCategoryCondition) {

        return shopCategoryMapper.selectAll( ShopCategoryCondition );
    }

    @Override
    public int updateByPrimaryKey(ShopCategory record) {
        return 0;
    }
}
