package com.bean;

import org.apache.poi.hssf.record.formula.MemErrPtg;

import java.math.BigDecimal;

public class Product {
    private Integer id;
    private String name;
    private String filename;
    private int categoryid;
    private int childid;
    private double price;
    private String content;
    private int sellerid;
    private String status;
    private String issj;
    private String shstatus;


    private Member seller;
    private Category fcategory;
    private Category ccategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getChildid() {
        return childid;
    }

    public void setChildid(int childid) {
        this.childid = childid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSellerid() {
        return sellerid;
    }

    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssj() {
        return issj;
    }

    public void setIssj(String issj) {
        this.issj = issj;
    }

    public String getShstatus() {
        return shstatus;
    }

    public void setShstatus(String shstatus) {
        this.shstatus = shstatus;
    }

    public Member getSeller() {
        return seller;
    }

    public void setSeller(Member seller) {
        this.seller = seller;
    }

    public Category getFcategory() {
        return fcategory;
    }

    public void setFcategory(Category fcategory) {
        this.fcategory = fcategory;
    }

    public Category getCcategory() {
        return ccategory;
    }

    public void setCcategory(Category ccategory) {
        this.ccategory = ccategory;
    }
}