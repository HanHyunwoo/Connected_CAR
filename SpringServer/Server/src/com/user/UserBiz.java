package com.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Biz;
import com.frame.Dao;
import com.vo.User;


@Service("userBiz")
public class UserBiz implements Biz<User> {

	@Resource(name="userDao")
	Dao<User> dao;
	
	@Transactional
	@Override
	public void register(User t) {		
		dao.insert(t);
	}
}





