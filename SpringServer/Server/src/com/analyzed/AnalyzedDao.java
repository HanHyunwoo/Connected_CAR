package com.analyzed;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.AnalyzedMapper;
import com.vo.Analyzed;

@Repository("analyzedDao") // default 가 첫글자 소문자, 이름 변경해서 사용하고 싶을 때 ("xxx")
public class AnalyzedDao implements Dao<Analyzed>{
	@Autowired // 아래 UserMapper객체타입을 찾는다.
	AnalyzedMapper mapper; // 얘가 마이바티스 연동하는 거

	@Override
	public void insert(Analyzed t) {
		mapper.insert(t);
	}

	@Override
	public void insertAll(List<Analyzed> t) {
		mapper.insertAll(t);
		
	}

}
