package cn.lw.services.Impl;

import cn.lw.domain.PersonInfo;
import cn.lw.mapper.PersonInfoMapper;
import cn.lw.services.IPersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonInfoServiceImpl implements IPersonInfoService {
	@Autowired
	private PersonInfoMapper personInfoMapper;

	@Override
	public PersonInfo getPersonInfoById(int userId) {
		return personInfoMapper.selectByPrimaryKey(userId);
	}
}
