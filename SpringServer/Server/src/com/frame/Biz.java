package com.frame;

import java.util.HashMap;
import java.util.List;

import com.vo.Score;

public interface Biz<T, S, I> {
	public void register(T t);
	public void registerAll(List<T> t);
	public List<T> selectId(S s);
	public List<T> selectRg(S s);
	public List<T> selectScore2(S s);
	public List<T> selectEffi(S s);
	public HashMap<S, I> selectCnt(S s);
	public List<T> selectMaxMin();
	public List<Score> selectScore(S s);
}
