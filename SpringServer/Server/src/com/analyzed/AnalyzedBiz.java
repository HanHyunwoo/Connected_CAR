package com.analyzed;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Biz;
import com.frame.Dao;
import com.vo.Analyzed;
import com.vo.Score;


@Service("analyzedBiz")
public class AnalyzedBiz implements Biz<Analyzed, String, Integer> {

	@Resource(name="analyzedDao")
	Dao<Analyzed, String, Integer> dao;
	
	@Transactional
	@Override
	public void register(Analyzed t) {		
		dao.insert(t);
	}
	
	@Override
	public List<Analyzed> selectId(String s) {	
		
		return dao.selectId(s);
	}

	@Override
	public void registerAll(List<Analyzed> t) {
		for(Analyzed a : t)
			dao.insert(a);
		
	}

	@Override
	public HashMap<String, Integer> selectCnt(String s) {
		// TODO Auto-generated method stub
		return dao.selectCnt(s);
	}

	@Override
	public List<Analyzed> selectRg(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Score> selectScore(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Analyzed> selectScore2(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Analyzed> selectEffi(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Analyzed> selectMaxMin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Analyzed> selectDistEffi(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
	
