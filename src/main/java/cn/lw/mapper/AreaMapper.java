package cn.lw.mapper;

import cn.lw.domain.Area;
import java.util.List;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer areaId);

    int insert(Area record);

    Area selectByPrimaryKey(Integer areaId);

    List<Area> selectAll();

    int updateByPrimaryKey(Area record);
}