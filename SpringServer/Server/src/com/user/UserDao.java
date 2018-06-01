package com.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.frame.Dao;
import com.mapper.UserMapper;
import com.vo.User;

@Repository("userDao") // default �� ù���� �ҹ���, �̸� �����ؼ� ����ϰ� ���� �� ("xxx")
public class UserDao implements Dao<User, String, Integer> {
	@Autowired // �Ʒ� UserMapper��üŸ���� ã�´�.
	UserMapper mapper; // �갡 ���̹�Ƽ�� �����ϴ� ��

	@Override
	public void insert(User t) {
		mapper.insert(t);
	}

	@Override
	public void insertAll(List<User> t) {
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

}
