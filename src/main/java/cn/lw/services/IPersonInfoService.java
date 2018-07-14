package cn.lw.services;


import cn.lw.domain.PersonInfo;

public interface IPersonInfoService {

	/**
	 * 根据用户Id获取personInfo信息
	 * @param userId
	 * @return
	 */
	PersonInfo getPersonInfoById(int userId);

}
