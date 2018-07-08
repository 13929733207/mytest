package cn.itcast.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.core.mapper.UserMapper;
import cn.itcast.core.pojo.User;
import cn.itcast.core.service.UserService;

/**
 * �û�ҵ��ӿ�ʵ����
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
