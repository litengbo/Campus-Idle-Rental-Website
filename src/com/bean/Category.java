package com.bean;

import java.util.*;

public class Category {
	private int id;
	private String name;
    private int fatherid;
	private String delstatus;
    private List<Category>  childlist;

    public List<Category> getChildlist() {
        return childlist;
    }

    public void setChildlist(List<Category> childlist) {
        this.childlist = childlist;
    }

    public int getFatherid() {
        return fatherid;
    }

    public void setFatherid(int fatherid) {
        this.fatherid = fatherid;
    }

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDelstatus() {
		return delstatus;
	}
	public void setDelstatus(String delstatus) {
		this.delstatus = delstatus;
	}
	
	
	
	
}
