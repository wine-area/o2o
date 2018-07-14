package cn.lw.services.Impl;

import cn.lw.domain.Area;
import cn.lw.mapper.AreaMapper;
import cn.lw.services.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/6/17
 */
@Service@Transactional
public class AreaServiceImpl implements IAreaService {
    @Autowired
    private AreaMapper mapper;

    @Override
    public int deleteByPrimaryKey(Integer areaId) {

        return mapper.deleteByPrimaryKey( areaId );
    }

    @Override
    public int insert(Area record) {

        return mapper.insert( record );
    }

    @Override
    public Area selectByPrimaryKey(Integer areaId) {

        return mapper.selectByPrimaryKey( areaId );
    }

    @Override
    public List<Area> selectAll() {

        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Area record) {

        return mapper.updateByPrimaryKey( record );
    }
}
