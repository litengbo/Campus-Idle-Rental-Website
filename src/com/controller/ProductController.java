package com.controller;

import javax.annotation.Resource;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.bean.*;
import com.dao.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.Info;

import java.util.*;

@Controller
public class ProductController extends BaseController {

	@Resource
	ProductDAO productDAO;
	@Resource
	CategoryDAO categoryDAO;
    @Resource
    MemberDAO memberDAO;

	//物品列表
	@ResponseBody
	@RequestMapping("admin/productList")
	public HashMap<String,Object> productList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize,HttpServletRequest request){
		String key = request.getParameter("key");
		String categoryid = request.getParameter("categoryid");
        String issj = request.getParameter("issj");
        String childid = request.getParameter("childid");
        String shstatus = request.getParameter("shstatus");

		HashMap<String,Object> res = new HashMap<String,Object>();
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("key", key);
		map.put("categoryid", categoryid);
        map.put("childid", childid);
        map.put("issj", issj);
        map.put("shstatus", shstatus);

		List<Product> objectlist = productDAO.selectAll(map);
		for(Product product:objectlist){
			Category fcategory = categoryDAO.findById(product.getCategoryid());
			product.setFcategory(fcategory);

            Category ccategory = categoryDAO.findById(product.getChildid());
            product.setCcategory(ccategory);
 		}
		PageHelper.startPage(pageNum, pageSize);
		List<Product> list = productDAO.selectAll(map);
		for(Product product:list){
			Category category = categoryDAO.findById(product.getCategoryid());
            product.setFcategory(category);

            Category ccategory = categoryDAO.findById(product.getChildid());
            product.setCcategory(ccategory);
			
 		}
		PageInfo<Product> pageInfo = new PageInfo<Product>(list);
		res.put("pageInfo", pageInfo);
		res.put("list", objectlist);
		return res;
	}



    //过滤自己的商品物品列表
    @ResponseBody
    @RequestMapping("productList")
    public HashMap<String,Object> productList2(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize,HttpServletRequest request){
        String key = request.getParameter("key");
        String categoryid = request.getParameter("categoryid");
        String issj = request.getParameter("issj");
        String childid = request.getParameter("childid");
        String shstatus = request.getParameter("shstatus");
        HashMap<String,Object> res = new HashMap<String,Object>();
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("key", key);
        map.put("categoryid", categoryid);
        map.put("childid", childid);
        map.put("issj", issj);
        map.put("shstatus", shstatus);

        //过滤自己的物品
        Member sessionemember = (Member)request.getSession().getAttribute("sessionmember");
        if(sessionemember!=null){
            map.put("cwsellerid", String.valueOf(sessionemember.getId()));
        }

        List<Product> objectlist = productDAO.selectAll(map);
        for(Product product:objectlist){
            Category fcategory = categoryDAO.findById(product.getCategoryid());
            product.setFcategory(fcategory);

            Category ccategory = categoryDAO.findById(product.getChildid());
            product.setCcategory(ccategory);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productDAO.selectAll(map);
        for(Product product:list){
            Category category = categoryDAO.findById(product.getCategoryid());
            product.setFcategory(category);

            Category ccategory = categoryDAO.findById(product.getChildid());
            product.setCcategory(ccategory);

        }
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        res.put("pageInfo", pageInfo);
        res.put("list", objectlist);
        return res;
    }


    //用户物品列表
    @ResponseBody
    @RequestMapping("member/productList")
    public HashMap<String,Object> productList1(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize,HttpServletRequest request){
        Member member = (Member)request.getSession().getAttribute("sessionmember");
	    String key = request.getParameter("key");
        String categoryid = request.getParameter("categoryid");
        String childid = request.getParameter("childid");
        HashMap<String,Object> res = new HashMap<String,Object>();
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("key", key);
        map.put("categoryid", categoryid);
        map.put("childid", childid);
        map.put("sellerid", String.valueOf(member.getId()));
        List<Product> objectlist = productDAO.selectAll(map);
        for(Product product:objectlist){
            Category fcategory = categoryDAO.findById(product.getCategoryid());
            Category ccategory = categoryDAO.findById(product.getChildid());
            product.setFcategory(fcategory);
            product.setCcategory(ccategory);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productDAO.selectAll(map);
        for(Product product:list){
            Category fcategory = categoryDAO.findById(product.getCategoryid());
            Category ccategory = categoryDAO.findById(product.getChildid());
            product.setFcategory(fcategory);
            product.setCcategory(ccategory);
        }
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        res.put("pageInfo", pageInfo);
        res.put("list", objectlist);
        return res;
    }


	
	//用户物品
	@ResponseBody
	@RequestMapping("member/productAdd")
	public HashMap<String,Object> productAdd(Product product ,HttpServletRequest request) {
        Member member = (Member)request.getSession().getAttribute("sessionmember");
        product.setSellerid(member.getId());
        product.setStatus("闲置");
        product.setIssj("yes");
        product.setShstatus("待审核");
		productDAO.add(product);
		return null;
	}
	
	//编辑物品
	@ResponseBody
	@RequestMapping("member/productShow")
	public HashMap<String,Object> productShow(int id,HttpServletRequest request) {
		HashMap<String,Object> res = new HashMap<String,Object>();
		Product product = productDAO.findById(id);
		Category fcategory = categoryDAO.findById(product.getCategoryid());
        Category ccategory = categoryDAO.findById(product.getChildid());
        Member seller = memberDAO.findById(product.getSellerid());
        product.setSeller(seller);
        product.setFcategory(fcategory);
        product.setCcategory(ccategory);
		res.put("product", product);


        Member member = (Member)request.getSession().getAttribute("sessionmember");
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("childid", String.valueOf(product.getChildid()));
        if(member!=null){
            map.put("cwsellerid", String.valueOf(member.getId()));
        }
		List<Product> lovelist = productDAO.selectAll(map);
		res.put("lovelist", lovelist);
		return res;
	}
	
	//编辑物品
	@ResponseBody
	@RequestMapping("member/productEdit")
	public HashMap<String,Object> productEdit(Product product ,HttpServletRequest request) {
		/*
		 * productDAO.update(product); return null;
		 */
		int id = product.getId();
		productDAO.delete(id); 
		 Member member = (Member)request.getSession().getAttribute("sessionmember");
	        product.setSellerid(member.getId());
	        product.setStatus("闲置");
	        product.setIssj("yes");
	        product.setShstatus("待审核");
			productDAO.add(product);
		return null;
	}
	
	//删除物品
	@ResponseBody
	@RequestMapping("admin/productDel")
	public HashMap<String,Object> productDel(HttpServletRequest request) {
		String id = request.getParameter("id");
		productDAO.delete(Integer.parseInt(id));
		return null;
	}

    /**
     * 物品上下架
     */

    @ResponseBody
    @RequestMapping("member/issjedit")
    public void issjedit(int id,HttpServletRequest request) {
        Product product =  productDAO.findById(id);
        if(product.getIssj().equals("yes")){
            product.setIssj("no");
        }else{
            product.setIssj("yes");
        }
        productDAO.update(product);
    }

    /**
     * 审核物品
     */

    @ResponseBody
    @RequestMapping("admin/shstatusedit")
    public void shstatusedit(int id,HttpServletRequest request) {
        Product product =  productDAO.findById(id);
        String flag = request.getParameter("flag");
        product.setShstatus(flag);
        productDAO.update(product);
    }

}
