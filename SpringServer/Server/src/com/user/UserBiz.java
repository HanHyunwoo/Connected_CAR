package com.user;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Biz;
import com.frame.Dao;
import com.vo.User;


@Service("userBiz")
public class UserBiz implements Biz<User, String, Integer> {

	@Resource(name="userDao")
	Dao<User, String, Integer> dao;
	
	@Transactional
	@Override
	public void register(User t) {		
		dao.insert(t);
	}

	@Override
	public void registerAll(List<User> t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> selectId(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Integer> selectCnt(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectRg(String s) {
		// TODO Auto-generated method stub
		return null;
	}
}





