package com.frame;

import java.util.List;

public interface Dao<T> {
	public void insert(T t);
	

	public void insertAll(List<T> t);
}
