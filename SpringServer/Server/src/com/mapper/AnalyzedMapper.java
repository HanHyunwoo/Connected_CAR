package com.mapper;

import java.util.List;

import com.vo.Analyzed;

public interface AnalyzedMapper {
	public void insert (Analyzed t);
	public void insertAll (List<Analyzed> t);
}
