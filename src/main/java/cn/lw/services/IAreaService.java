package cn.lw.services;

import cn.lw.domain.Area;

import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services
 * @date 2018/6/17
 */
public interface IAreaService {
    int deleteByPrimaryKey(Integer areaId);

    int insert(Area record);

    Area selectByPrimaryKey(Integer areaId);

    List<Area> selectAll();

    int updateByPrimaryKey(Area record);
}
