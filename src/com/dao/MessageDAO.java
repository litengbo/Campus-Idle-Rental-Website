package com.dao;


import com.bean.Message;

import java.util.HashMap;
import java.util.List;

public interface MessageDAO {
	List<Message> selectAll(HashMap map);
	void add(Message message);
	void delete(int id);
	void update(Message message);
    Message findById(int id);
}