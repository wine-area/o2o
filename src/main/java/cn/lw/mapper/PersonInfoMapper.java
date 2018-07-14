package cn.lw.mapper;

import cn.lw.domain.PersonInfo;
import java.util.List;

public interface PersonInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(PersonInfo record);

    PersonInfo selectByPrimaryKey(Integer userId);

    List<PersonInfo> selectAll();

    int updateByPrimaryKey(PersonInfo record);
}