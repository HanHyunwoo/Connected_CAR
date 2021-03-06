package com.hexadate;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.HexaDateMapper;
import com.vo.HexaDate;
import com.vo.Score;

@Repository("hexaDateDao") // default 가 첫글자 소문자, 이름 변경해서 사용하고 싶을 때 ("xxx")
public class HexaDateDao implements Dao<HexaDate, String, Integer>{
	@Autowired // 아래 UserMapper객체타입을 찾는다.
	HexaDateMapper mapper; // 얘가 마이바티스 연동하는 거

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

	@Override
	public List<HexaDate> selectRg(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Score> selectScore(String s) {
		// TODO Auto-generated method stub
		return mapper.selectScore(s);
	}

	@Override
	public List<HexaDate> selectScore2(String s) {
		// TODO Auto-generated method stub
		return mapper.selectScore2(s);
	}

	@Override
	public List<HexaDate> selectEffi(String s) {
		// TODO Auto-generated method stub
		return mapper.selectEffi(s);
	}

	@Override
	public List<HexaDate> selectMaxMin() {
		// TODO Auto-generated method stub
		return mapper.selectMaxMin();
	}

	@Override
	public List<HexaDate> selectDistEffi(String s) {
		// TODO Auto-generated method stub
		return mapper.selectDistEffi(s);
	}

	
}
