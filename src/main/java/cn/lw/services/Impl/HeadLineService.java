package cn.lw.services.Impl;

import cn.lw.domain.HeadLine;
import cn.lw.mapper.HeadLineMapper;
import cn.lw.services.IHeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/7/7
 */
@Service
public class HeadLineService implements IHeadLineService {
    @Autowired
    HeadLineMapper headLineMapper;
    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        return headLineMapper.queryHeadLine( headLineCondition );
    }
}
