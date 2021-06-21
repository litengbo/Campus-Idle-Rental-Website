package com.dao;

import com.bean.About;

import java.util.HashMap;
import java.util.List;

public interface AboutDAO {
	void update(About about);
	About findById(int id);
}