package com.dao;


import com.bean.Notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeDAO {
	List<Notice> selectAll(HashMap map);
	void add(Notice notice);
	void delete(int id);
	void update(Notice notice);
    Notice findById(int id);
}