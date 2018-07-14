package cn.lw.mapper;

import cn.lw.domain.HeadLine;
import cn.lw.services.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.mapper
 * @date 2018/7/7
 */
public class HeadLineMapperTest extends BaseTest {
    @Autowired
    HeadLineMapper headLineMapper;

    @Test

    public void queryHeadLine() {
        List<HeadLine> headLines = headLineMapper.queryHeadLine( new HeadLine() );
        assertEquals( 4
                , headLines.size() );
    }
}