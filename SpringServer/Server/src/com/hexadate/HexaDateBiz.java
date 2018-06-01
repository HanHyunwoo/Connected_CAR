package com.hexadate;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Biz;
import com.frame.Dao;
import com.vo.Analyzed;
import com.vo.HexaDate;


@Service("hexaDateBiz")
public class HexaDateBiz implements Biz<HexaDate, String, Integer> {

	@Resource(name="hexaDateDao")
	Dao<HexaDate, String, Integer> dao;

	@Override
	public void register(HexaDate t) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void registerAll(List<HexaDate> t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HexaDate> selectId(String s) {
		return dao.selectId(s);
	}


	@Override
	public List<HexaDate> selectRg(String s) {
		return dao.selectRg(s);
	}
	@Override
	public HashMap<String, Integer> selectCnt(String s) {
		// TODO Auto-generated method stub
		return null;
	}
}
	
