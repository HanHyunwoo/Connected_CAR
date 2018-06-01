package com.analyzed;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.AnalyzedMapper;
import com.vo.Analyzed;

@Repository("analyzedDao") // default �� ù���� �ҹ���, �̸� �����ؼ� ����ϰ� ���� �� ("xxx")
public class AnalyzedDao implements Dao<Analyzed, String, Integer>{
	@Autowired // �Ʒ� UserMapper��üŸ���� ã�´�.
	AnalyzedMapper mapper; // �갡 ���̹�Ƽ�� �����ϴ� ��

	@Override
	public void insert(Analyzed t) {
		mapper.insert(t);
	}

	@Override
	public List<Analyzed> selectId(String s) {
		
		return mapper.selectId(s);
	}
	

	@Override
	public List<Analyzed> selectRg(String s) {
		
		return mapper.selectId(s);
	}
	@Override
	public void insertAll(List<Analyzed> t) {
		mapper.insertAll(t);
		
	}

	@Override
	public HashMap<String, Integer> selectCnt(String s) {
		// TODO Auto-generated method stub
		return mapper.selectCnt(s);
	}

}
