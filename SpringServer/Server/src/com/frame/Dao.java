package com.frame;

import java.util.HashMap;
import java.util.List;

public interface Dao<T, S, I> {
	public void insert(T t);
	public void insertAll(List<T> t);
	public List<T> selectId(S s);
	public List<T> selectRg(S s);
	public HashMap<S, I> selectCnt(S s);
}
