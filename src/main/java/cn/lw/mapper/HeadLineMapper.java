package cn.lw.mapper;

import cn.lw.domain.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineMapper {
    int deleteByPrimaryKey(Integer lineId);

    int insert(HeadLine record);

    HeadLine selectByPrimaryKey(Integer lineId);


    int updateByPrimaryKey(HeadLine record);

    List<HeadLine> queryHeadLine(@Param( "headLineCondition" ) HeadLine headLineCondition);
}