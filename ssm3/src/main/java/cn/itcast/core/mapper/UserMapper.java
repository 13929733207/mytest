package cn.itcast.core.mapper;

import cn.itcast.core.pojo.User;

/**
 * 用户映射接口
 * @author ldh
 *
 */
public interface UserMapper {
	
	/**
	 * 新增一个用户
	 * @param user
	 */
	void addUser(User user);
	
}
