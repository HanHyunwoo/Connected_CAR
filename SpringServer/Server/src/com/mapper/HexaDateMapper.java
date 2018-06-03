package com.mapper;

import java.util.HashMap;
import java.util.List;

import com.vo.HexaDate;
import com.vo.Score;

public interface HexaDateMapper {
	public List<HexaDate> selectId (String s);
	public HashMap<String, Integer> selectCnt(String s);
	public List<Score> selectScore (String s);
	public List<HexaDate> selectScore2 (String s);
	public List<HexaDate> selectEffi (String s);
	public List<HexaDate> selectDistEffi (String s);
	public List<HexaDate> selectMaxMin();
	public void insert (HexaDate t);
	public void insertAll (List<HexaDate> t);
}
