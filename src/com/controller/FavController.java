package com.controller;

import com.bean.Fav;
import com.bean.Member;
import com.bean.Product;
import com.dao.FavDAO;
import com.dao.MemberDAO;
import com.dao.ProductDAO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class FavController extends BaseController {

	@Resource
    FavDAO favDAO;
    @Resource
    ProductDAO productDAO;
    @Resource
    MemberDAO memberDAO;
	//收藏列表
	@ResponseBody
	@RequestMapping("favList")
	public HashMap<String,Object> favList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize,HttpServletRequest request){
        Member sessionmember = (Member) request.getSession().getAttribute("sessionmember");
	    String key = request.getParameter("key");
		HashMap<String,Object> res = new HashMap<String,Object>();
		HashMap map = new HashMap();
		map.put("memberid", sessionmember.getId());
		List<Fav> objectlist = favDAO.selectAll(map);
		for(Fav fav:objectlist){
            Product product = productDAO.findById(fav.getProductid());
            fav.setProduct(product);
        }
		PageHelper.startPage(pageNum, pageSize);
		List<Fav> list = favDAO.selectAll(map);
        for(Fav fav:list){
            Product product = productDAO.findById(fav.getProductid());
            fav.setProduct(product);
        }
		PageInfo<Fav> pageInfo = new PageInfo<Fav>(list);
		res.put("pageInfo", pageInfo);
		res.put("list", objectlist);
		return res;
	}

	//添加收藏
	@ResponseBody
	@RequestMapping("favAdd")
	public HashMap<String,Object> favAdd(Fav fav ,HttpServletRequest request) {
        HashMap<String,Object> res = new HashMap<String,Object>();
        Member sessionmember = (Member) request.getSession().getAttribute("sessionmember");
        HashMap map = new HashMap();
        map.put("memberid", sessionmember.getId());
        map.put("productid", fav.getProductid());
        List<Fav> list = favDAO.selectAll(map);
        if(list.size()==0){
            fav.setMemberid(sessionmember.getId());
            favDAO.add(fav);
            res.put("data",200);
        }else{
            res.put("data",400);
        }
		return res;
	}

	//删除收藏
	@ResponseBody
	@RequestMapping("favDel")
	public HashMap<String,Object> favDel(int id,HttpServletRequest request) {
		favDAO.delete(id);
		return null;
	}

}
