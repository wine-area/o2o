package cn.lw.services;

import cn.lw.domain.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services
 * @date 2018/7/7
 */
public interface IHeadLineService {

    List<HeadLine> getHeadLineList(HeadLine headLineCondition)
            throws IOException;
}
