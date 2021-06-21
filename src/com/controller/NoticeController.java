package com.controller;

import com.bean.Category;
import com.bean.Member;
import com.bean.Notice;
import com.dao.CategoryDAO;
import com.dao.MemberDAO;
import com.dao.NoticeDAO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.Info;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class NoticeController extends BaseController {

	@Resource
    NoticeDAO noticeDAO;
	@Resource
	CategoryDAO categoryDAO;
    @Resource
    MemberDAO memberDAO;
	//公告列表
	@ResponseBody
	@RequestMapping("admin/noticeList")
	public HashMap<String,Object> noticeList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize,HttpServletRequest request){
		String key = request.getParameter("key");
		HashMap<String,Object> res = new HashMap<String,Object>();
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("key", key);
		List<Notice> objectlist = noticeDAO.selectAll(map);
		PageHelper.startPage(pageNum, pageSize);
		List<Notice> list = noticeDAO.selectAll(map);
		PageInfo<Notice> pageInfo = new PageInfo<Notice>(list);
		res.put("pageInfo", pageInfo);
		res.put("list", objectlist);
		return res;
	}



		
	
	//用户公告
	@ResponseBody
	@RequestMapping("admin/noticeAdd")
	public HashMap<String,Object> noticeAdd(Notice notice ,HttpServletRequest request) {
        notice.setSavetime(Info.getDateStr());
		noticeDAO.add(notice);
		return null;
	}
	
	//编辑公告
	@ResponseBody
	@RequestMapping("admin/noticeShow")
	public HashMap<String,Object> noticeShow(int id,HttpServletRequest request) {
		HashMap<String,Object> res = new HashMap<String,Object>();
		Notice notice = noticeDAO.findById(id);
		res.put("notice", notice);
		return res;
	}
	
	//编辑公告
	@ResponseBody
	@RequestMapping("admin/noticeEdit")
	public HashMap<String,Object> noticeEdit(Notice notice ,HttpServletRequest request) {
		noticeDAO.update(notice);
		return null;
	}
	
	//删除公告
	@ResponseBody
	@RequestMapping("admin/noticeDel")
	public HashMap<String,Object> noticeDel(HttpServletRequest request) {
		String id = request.getParameter("id");
		noticeDAO.delete(Integer.parseInt(id));
		return null;
	}


}
