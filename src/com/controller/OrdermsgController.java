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
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.CheckCode;
import com.util.Info;

import java.util.*;

@Controller
public class OrdermsgController extends BaseController {

	@Resource
	OrdermsgDAO ordermsgDAO;
	@Resource
	ProductDAO productDAO;
	@Resource
	MemberDAO memberDAO;
	@Resource
	SysuserDAO sysuserDAO;

	// 创建订单
	@ResponseBody
	@RequestMapping("ordermsgAdd")
	public HashMap<String, Object> ordermsgAdd(Ordermsg ordermsg, HttpServletRequest request) {
		HashMap<String, Object> res = new HashMap<String, Object>();
		Member sessionmember = (Member) request.getSession().getAttribute("sessionmember");
		Member member = memberDAO.findById(sessionmember.getId());
		Product product = productDAO.findById(ordermsg.getProductid());

		// 求天数判断余额
		int daynum = Info.dayToday(Info.getDateStr().substring(0, 10), ordermsg.getEtime()) + 1;
		double total = daynum * product.getPrice();
		double yjprice = ordermsg.getYjprice();
		double ordertotal = total + yjprice;

		if (member.getWallet() >= ordertotal) {
			CheckCode cc = new CheckCode();
			String ddno = cc.getCheckCode();
			ordermsg.setDdno(ddno);
			ordermsg.setTotal(total);
			ordermsg.setYjprice(yjprice);
			ordermsg.setMemberid(sessionmember.getId());
			ordermsg.setSellerid(product.getSellerid());
			ordermsg.setStatus("待同意");
			ordermsg.setStime(Info.getDateStr().substring(0, 10));
			ordermsgDAO.add(ordermsg);

			// 减押金和租金（total）租用者
			double wallet = member.getWallet() - ordertotal;
			member.setWallet(wallet);
			memberDAO.update(member);

			// 加余额物品主人
			/*
			 * Member seller = memberDAO.findById(product.getSellerid());
			 * seller.setWallet(seller.getWallet()+ordertotal); memberDAO.update(seller);
			 */
			res.put("data", 200);
		} else {
			res.put("data", 400);
		}
		return res;
	}

	/**
	 * 订单列表 租用订单
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("member/orderList")
	public HashMap<String, Object> orderList(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
			@RequestParam(defaultValue = "1", value = "pageSize") Integer pageSize, HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute("sessionmember");
		String key = request.getParameter("key");
		HashMap<String, Object> res = new HashMap<String, Object>();
		HashMap map = new HashMap();
		map.put("key", key);
		map.put("memberid", member.getId());
		List<Ordermsg> objectlist = ordermsgDAO.selectAll(map);
		for (Ordermsg ordermsg : objectlist) {
			Member mmm = memberDAO.findById(ordermsg.getMemberid());
			ordermsg.setMember(mmm);
			Member seller = memberDAO.findById(ordermsg.getSellerid());
			ordermsg.setSeller(seller);

			Product product = productDAO.findById(ordermsg.getProductid());
			ordermsg.setProduct(product);

		}
		PageHelper.startPage(pageNum, pageSize);
		List<Ordermsg> list = ordermsgDAO.selectAll(map);
		for (Ordermsg ordermsg : list) {
			Member mmm = memberDAO.findById(ordermsg.getMemberid());
			ordermsg.setMember(mmm);
			Member seller = memberDAO.findById(ordermsg.getSellerid());
			ordermsg.setSeller(seller);

			Product product = productDAO.findById(ordermsg.getProductid());
			ordermsg.setProduct(product);
		}
		PageInfo<Ordermsg> pageInfo = new PageInfo<Ordermsg>(list);
		res.put("pageInfo", pageInfo);
		res.put("list", objectlist);
		return res;
	}

	/**
	 * 租出订单
	 */

	@ResponseBody
	@RequestMapping("member/orderLb")
	public HashMap<String, Object> orderLb(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
			@RequestParam(defaultValue = "1", value = "pageSize") Integer pageSize, HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute("sessionmember");
		String key = request.getParameter("key");
		HashMap<String, Object> res = new HashMap<String, Object>();
		HashMap map = new HashMap();
		map.put("key", key);
		map.put("sellerid", member.getId());
		List<Ordermsg> objectlist = ordermsgDAO.selectAll(map);
		for (Ordermsg ordermsg : objectlist) {
			Member mmm = memberDAO.findById(ordermsg.getMemberid());
			ordermsg.setMember(mmm);
			Member seller = memberDAO.findById(ordermsg.getSellerid());
			ordermsg.setSeller(seller);

			Product product = productDAO.findById(ordermsg.getProductid());
			ordermsg.setProduct(product);

		}
		PageHelper.startPage(pageNum, pageSize);
		List<Ordermsg> list = ordermsgDAO.selectAll(map);
		for (Ordermsg ordermsg : list) {
			Member mmm = memberDAO.findById(ordermsg.getMemberid());
			ordermsg.setMember(mmm);
			Member seller = memberDAO.findById(ordermsg.getSellerid());
			ordermsg.setSeller(seller);

			Product product = productDAO.findById(ordermsg.getProductid());
			ordermsg.setProduct(product);

		}
		PageInfo<Ordermsg> pageInfo = new PageInfo<Ordermsg>(list);
		res.put("pageInfo", pageInfo);
		res.put("list", objectlist);
		return res;
	}

	// 删除订单
	@ResponseBody
	@RequestMapping("admin/orderDel")
	public HashMap<String, Object> orderDel(int id, HttpServletRequest request) {
		ordermsgDAO.delete(id);
		return null;
	}

	/**
	 * 租出物品
	 */
	@ResponseBody
	@RequestMapping("member/borrow")
	public HashMap<String, Object> borrow(int id, HttpServletRequest request) {
		HashMap<String, Object> res = new HashMap<String, Object>();
		Ordermsg ordermsg = ordermsgDAO.findById(id);
		Product product = productDAO.findById(ordermsg.getProductid());
		if (product.getStatus().equals("闲置")) {
			// 增加用户租用次数
			product.setStatus("在租");
			productDAO.update(product);
			ordermsg.setStatus("在租");
			ordermsgDAO.update(ordermsg);
			res.put("data", 200);

		} else {

			Member member = memberDAO.findById(ordermsg.getMemberid());
			member.setWallet(member.getWallet() + ordermsg.getYjprice() + ordermsg.getTotal());
			memberDAO.update(member);

			Member seller = memberDAO.findById(product.getSellerid());
			seller.setWallet(seller.getWallet() - ordermsg.getTotal() - ordermsg.getYjprice());
			memberDAO.update(seller);

			ordermsg.setStatus("已拒绝");
			ordermsgDAO.update(ordermsg);
			res.put("data", 400);
		}
		return res;
	}

	/**
	 * 拒绝租出物品
	 */
	@ResponseBody
	@RequestMapping("member/jujeborrow")
	public HashMap<String, Object> jujeborrow(int id, HttpServletRequest request) {
		Ordermsg ordermsg = ordermsgDAO.findById(id);
		Product product = productDAO.findById(ordermsg.getProductid());
		ordermsg.setStatus("已拒绝");
		ordermsgDAO.update(ordermsg);

		Member member = memberDAO.findById(ordermsg.getMemberid());
		member.setWallet(member.getWallet() + ordermsg.getTotal() + ordermsg.getYjprice());
		memberDAO.update(member);
		// 加余额物品主人
		/*
		 * Member seller = memberDAO.findById(product.getSellerid());
		 * seller.setWallet(seller.getWallet()-ordermsg.getTotal()-ordermsg.getYjprice()
		 * ); memberDAO.update(seller);
		 */
		return null;
	}

	/**
	 * 归还
	 */
	@ResponseBody
	@RequestMapping("member/guihuan")
	public void guihuan(int id, HttpServletRequest request) {
		HashMap<String, Object> res = new HashMap<String, Object>();
		Ordermsg ordermsg = ordermsgDAO.findById(id);
		Product product = productDAO.findById(ordermsg.getProductid());
		// product.setStatus("闲置");
		productDAO.update(product);
		ordermsg.setStatus("已归还");

		// 后期修改，点击归还先不返回押金
		// 返回押金()
		/*
		 * Member member = memberDAO.findById(ordermsg.getMemberid());
		 * member.setWallet(member.getWallet()+ordermsg.getYjprice());
		 * memberDAO.update(member);
		 */

		// 减押金
		/*
		 * Member seller = memberDAO.findById(product.getSellerid());
		 * seller.setWallet(seller.getWallet()-ordermsg.getYjprice());
		 * memberDAO.update(seller);
		 */

		ordermsgDAO.update(ordermsg);
	}

	// 确认归还
	
	  @ResponseBody
	  @RequestMapping("member/confirmreturn") 
	  public void confirmReturn(int id,HttpServletRequest request) { 
		  //id是ordermsg中的id 
		  HashMap<String, Object> res =new HashMap<String, Object>(); 
		  Ordermsg ordermsg = ordermsgDAO.findById(id);
	  Product product = productDAO.findById(ordermsg.getProductid());
	  product.setStatus("闲置"); productDAO.update(product);
	  ordermsg.setStatus("交易完成");
	  
	  //返回租户押金() 
	  Member member = memberDAO.findById(ordermsg.getMemberid()); 
	  String today = Info.getToday(); 
	  if((Info.dayToday(ordermsg.getEtime(),today)) >0 && (Info.dayToday(ordermsg.getEtime(),today))<9998){
	  int day = Info.dayToday(ordermsg.getEtime(), today);
	  //逾期多少天，那就多付beYondTimeMoney的钱
	  double beYondTimeMoney = day*product.getPrice();
	  //最多扣完押金
	  if(beYondTimeMoney > ordermsg.getYjprice()) {
		  beYondTimeMoney = ordermsg.getYjprice();
	  }
	  //返还租户押金（扣掉逾期费用beYondTimeMoney）
	  member.setWallet(member.getWallet()+ordermsg.getYjprice()-beYondTimeMoney);
	  memberDAO.update(member); 
	  //主人账户多这么多
	  Member seller = memberDAO.findById(product.getSellerid());
	  seller.setWallet(seller.getWallet()+ordermsg.getTotal()+beYondTimeMoney);
	  memberDAO.update(seller);
	  }else { 
		//返还租用者的押金
			member.setWallet(member.getWallet()+ordermsg.getYjprice());
			memberDAO.update(member);

			 //给物品主人增加租金 
			Member seller = memberDAO.findById(product.getSellerid());
			seller.setWallet(seller.getWallet()+ordermsg.getTotal());
			memberDAO.update(seller);
	  } 

	  ordermsgDAO.update(ordermsg); 
	  }
	 
	
	
	
//	@ResponseBody
//	@RequestMapping("member/confirmreturn")
//	public void confirmReturn(int id, HttpServletRequest request) {
//		// id是ordermsg中的id
//		HashMap<String, Object> res = new HashMap<String, Object>();
//		Ordermsg ordermsg = ordermsgDAO.findById(id);
//		Product product = productDAO.findById(ordermsg.getProductid());
//		product.setStatus("闲置");
//		productDAO.update(product);
//		ordermsg.setStatus("交易完成");
//		
//		//返还租用者的押金
//		Member member = memberDAO.findById(ordermsg.getMemberid());
//		member.setWallet(member.getWallet()+ordermsg.getYjprice());
//		memberDAO.update(member);
//
//		 //给物品主人增加租金 
//		Member seller = memberDAO.findById(product.getSellerid());
//		seller.setWallet(seller.getWallet()+ordermsg.getTotal());
//		memberDAO.update(seller);
//
//		ordermsgDAO.update(ordermsg); 
//	}
	@Test
	public void test01() {
		String Date1 = "2021-05-01";
		String Date2 = "2021-07-01";
		int day = Info.dayToday(Date1, Date2);
		System.out.println(day);
	}
	
	
	
	
	
	
	
}
