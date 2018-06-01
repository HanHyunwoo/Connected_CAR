package com.frame;

import java.util.List;

public interface Biz<T> {
	public void register(T t);
	public void registerAll(List<T> t);
}
