package com.controller;
import com.bean.Member;
import com.bean.Message;
import com.bean.Ordermsg;
import com.bean.Product;
import com.dao.MemberDAO;
import com.dao.MessageDAO;
import com.dao.OrdermsgDAO;
import com.dao.ProductDAO;
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
public class MessageController extends BaseController {

	@Resource
    MessageDAO messageDAO;
    @Resource
    ProductDAO productDAO;
    @Resource
    MemberDAO memberDAO;
    @Resource
    OrdermsgDAO ordermsgDAO;

	//评论列表
	@ResponseBody
	@RequestMapping("member/messageList")
	public HashMap<String,Object> messageList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize,HttpServletRequest request){
        Member sessionmember = (Member) request.getSession().getAttribute("sessionmember");
	    String key = request.getParameter("key");
		HashMap<String,Object> res = new HashMap<String,Object>();
		HashMap map = new HashMap();
		map.put("key", key);
        map.put("sellerid", sessionmember.getId());
		List<Message> objectlist = messageDAO.selectAll(map);
		for(Message message:objectlist){
            Member member = memberDAO.findById(message.getMemberid());
            message.setMember(member);
            Member seller = memberDAO.findById(message.getSellerid());
            message.setSeller(seller);
            Product product = productDAO.findById(message.getProductid());
            message.setProduct(product);
        }
		PageHelper.startPage(pageNum, pageSize);
		List<Message> list = messageDAO.selectAll(map);
        for(Message message:list){
            Member member = memberDAO.findById(message.getMemberid());
            message.setMember(member);
            Member seller = memberDAO.findById(message.getSellerid());
            message.setSeller(seller);
            Product product = productDAO.findById(message.getProductid());
            message.setProduct(product);
        }
		PageInfo<Message> pageInfo = new PageInfo<Message>(list);
		res.put("pageInfo", pageInfo);
		res.put("list", objectlist);
		return res;
	}


    @ResponseBody
    @RequestMapping("messageLb")
    public HashMap<String,Object> messageLb(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(defaultValue = "1",value = "pageSize") Integer pageSize,HttpServletRequest request){
        String key = request.getParameter("key");
        String productid = request.getParameter("productid");
        HashMap<String,Object> res = new HashMap<String,Object>();
        HashMap map = new HashMap();
        map.put("key", key);
        map.put("productid", productid);
        List<Message> objectlist = messageDAO.selectAll(map);
        for(Message message:objectlist){
            Member member = memberDAO.findById(message.getMemberid());
            message.setMember(member);
            Member seller = memberDAO.findById(message.getSellerid());
            message.setSeller(seller);
            Product product = productDAO.findById(message.getProductid());
            message.setProduct(product);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Message> list = messageDAO.selectAll(map);
        for(Message message:list){
            Member member = memberDAO.findById(message.getMemberid());
            message.setMember(member);
            Member seller = memberDAO.findById(message.getSellerid());
            message.setSeller(seller);
            Product product = productDAO.findById(message.getProductid());
            message.setProduct(product);
        }
        PageInfo<Message> pageInfo = new PageInfo<Message>(list);
        res.put("pageInfo", pageInfo);
        res.put("list", objectlist);

        return res;
    }
		
	
	//添加评论
	@ResponseBody
	@RequestMapping("massageAdd")
	public HashMap<String,Object> messageAdd(Message message ,HttpServletRequest request) {
        HashMap<String,Object> res = new HashMap<String,Object>();
        Member sessionmember = (Member) request.getSession().getAttribute("sessionmember");
        Product product = productDAO.findById(message.getProductid());

        HashMap map = new HashMap();
        map.put("productid", product.getId());//12
        map.put("memberid", sessionmember.getId());//40
        map.put("status", "交易完成");
        List<Ordermsg> list = ordermsgDAO.selectAll(map);


        if(list.size()!=0){
            message.setMemberid(sessionmember.getId());
            message.setSellerid(product.getSellerid());
            message.setSavetime(Info.getDateStr());
            messageDAO.add(message);
            res.put("data", 200);
        }else{
            res.put("data", 400);
        }
		return res;
	}

    /**
     * 评论回复
     * @param message
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("member/messageEdit")
    public HashMap<String,Object> messageEdit(Message message,HttpServletRequest request) {
        messageDAO.update(message);
        return null;
    }
	

	
	//删除评论
	@ResponseBody
	@RequestMapping("member/messageDel")
	public HashMap<String,Object> messageDel(int id,HttpServletRequest request) {
		messageDAO.delete(id);
		return null;
	}

}
