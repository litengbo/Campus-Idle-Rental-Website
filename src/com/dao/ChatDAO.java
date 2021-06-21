package com.dao;


import com.bean.Chat;

import java.util.HashMap;
import java.util.List;

public interface ChatDAO {
	List<Chat> selectAll(HashMap map);
	void add(Chat chat);
	void delete(int id);
	void update(Chat chat);
    Chat findById(int id);
}