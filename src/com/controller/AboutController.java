package com.controller;

import com.bean.About;
import com.dao.AboutDAO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
public class AboutController extends BaseController {

	@Resource
    AboutDAO aboutDAO;



    //修改关于我们
    @ResponseBody
    @RequestMapping("admin/aboutEdit")
    public void aboutEdit(About about, HttpServletRequest request) {
        aboutDAO.update(about);
    }

    /**
     * 查看信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("admin/aboutShow")
    public HashMap<String, Object> aboutShow(HttpServletRequest request) {
        HashMap<String, Object> res = new HashMap<String, Object>();
        About about = aboutDAO.findById(1);
        res.put("about", about);
        return res;
    }





}
