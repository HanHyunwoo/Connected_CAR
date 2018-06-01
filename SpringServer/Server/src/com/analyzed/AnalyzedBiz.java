package com.analyzed;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Biz;
import com.frame.Dao;
import com.vo.Analyzed;


@Service("analyzedBiz")
public class AnalyzedBiz implements Biz<Analyzed> {

	@Resource(name="analyzedDao")
	Dao<Analyzed> dao;
	
	@Transactional
	@Override
	public void register(Analyzed t) {		
		dao.insert(t);
	}

	@Override
	public void registerAll(List<Analyzed> t) {
		for(Analyzed a : t)
			dao.insert(a);
		
	}
}
	
