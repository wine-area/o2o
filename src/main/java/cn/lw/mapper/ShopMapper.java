package cn.lw.mapper;

import cn.lw.domain.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer shopId);

    /**
     *新增店铺
     * @param shop
     * @return 受影响的行数
     */
    int insert(Shop shop);

    Shop selectByPrimaryKey(Integer shopId);

    List<Shop> selectAll();

    int updateByPrimaryKey(Shop record);

    /**
     * 分页条件查询
     * @param shopCondition 店铺信息:可以根据店铺名字,店铺主人,类别,区域Id店铺类别进行查询
     * @param rowIndex 从第几行开始取
     * @param pageSize  每页大小
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
                             @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    /**
     * 获取数据表中满足条件数据的总数
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}