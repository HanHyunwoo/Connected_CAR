package com.frame;

import java.util.HashMap;
import java.util.List;

public interface Biz<T, S, I> {
	public void register(T t);
	public void registerAll(List<T> t);
	public List<T> selectId(S s);
	public List<T> selectRg(S s);
	public HashMap<S, I> selectCnt(S s);
}
