package com.mapper;

import java.util.HashMap;
import java.util.List;

import com.vo.Analyzed;

public interface AnalyzedMapper {
	public List<Analyzed> selectId (String s);
	public List<Analyzed> selectRg (String s);
	public HashMap<String, Integer> selectCnt(String s);
	public void insert (Analyzed t);
	public void insertAll (List<Analyzed> t);
}
