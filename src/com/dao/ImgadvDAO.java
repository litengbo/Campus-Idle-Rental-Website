package com.dao;


import com.bean.Imgadv;

import java.util.HashMap;
import java.util.List;

public interface ImgadvDAO {
	List<Imgadv> selectAll(HashMap map);
	void add(Imgadv imgadv);
	void update(Imgadv imgadv);
	void delete(int id);
    Imgadv findById(int id);
}