package com.analyzed;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.AnalyzedMapper;
import com.vo.Analyzed;

@Repository("analyzedDao") // default �� ù���� �ҹ���, �̸� �����ؼ� ����ϰ� ���� �� ("xxx")
public class AnalyzedDao implements Dao<Analyzed>{
	@Autowired // �Ʒ� UserMapper��üŸ���� ã�´�.
	AnalyzedMapper mapper; // �갡 ���̹�Ƽ�� �����ϴ� ��

	@Override
	public void insert(Analyzed t) {
		mapper.insert(t);
	}

	@Override
	public void insertAll(List<Analyzed> t) {
		mapper.insertAll(t);
		
	}

}
