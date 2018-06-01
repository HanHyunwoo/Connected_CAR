package com.hexadate;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.HexaDateMapper;
import com.vo.HexaDate;

@Repository("hexaDateDao") // default �� ù���� �ҹ���, �̸� �����ؼ� ����ϰ� ���� �� ("xxx")
public class HexaDateDao implements Dao<HexaDate, String, Integer>{
	@Autowired // �Ʒ� UserMapper��üŸ���� ã�´�.
	HexaDateMapper mapper; // �갡 ���̹�Ƽ�� �����ϴ� ��

	@Override
	public void insert(HexaDate t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertAll(List<HexaDate> t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HexaDate> selectId(String s) {
		// TODO Auto-generated method stub
		return mapper.selectId(s);
	}

	@Override
	public HashMap<String, Integer> selectCnt(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
