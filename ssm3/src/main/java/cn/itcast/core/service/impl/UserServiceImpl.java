package cn.itcast.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.core.mapper.UserMapper;
import cn.itcast.core.pojo.User;
import cn.itcast.core.service.UserService;

/**
 * 用户业务接口实现类
 * @author ldh
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;
	
	public void addUser(User user) {
		
		userMapper.addUser(user);
	}

}
