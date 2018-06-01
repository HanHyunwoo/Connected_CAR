package com.mapper;

import java.util.HashMap;
import java.util.List;

import com.vo.HexaDate;

public interface HexaDateMapper {
	public List<HexaDate> selectId (String s);
	public HashMap<String, Integer> selectCnt(String s);
	public void insert (HexaDate t);
	public void insertAll (List<HexaDate> t);
}
