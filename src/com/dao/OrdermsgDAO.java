package com.dao;

import java.util.HashMap;
import java.util.List;

import com.bean.Ordermsg;

public interface OrdermsgDAO {
	List<Ordermsg> selectAll(HashMap map);
	void add(Ordermsg ordermsg);
	void update(Ordermsg ordermsg);
	void delete(int id);
	Ordermsg findById(int id);
    
}