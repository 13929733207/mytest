package cn.itcast.core.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.itcast.core.pojo.User;
import cn.itcast.core.service.UserService;

/**
 * 用户controller
 * @author ldh
 *
 */
@Controller
public class UserConstroller {
	
	//
	@RequestMapping("add")
	public String tao(){
		return "insert";
	}
	
	@Resource
	private UserService userService;
	
	
	@RequestMapping("insert")
	@ResponseBody
	public String insert(User user) throws Exception{
		// 1.日期格式化对象
	/*	SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sFormat.parse(birthdays);
		User user = new User();
		user.setName(name);
		user.setBirthday(date);*/
		userService.addUser(user);
		
		return "success";
	}
	
	@RequestMapping("list")
	public String list(Model model){
//		1.建立HttpSolrServer对象，连接solr服务
		HttpSolrServer server = new HttpSolrServer("http://127.0.0.1:8088/solr/");
		
//		2.建立查询对象（SolrQuery）
		SolrQuery sq = new SolrQuery();
		
//		查询表达式q
		sq.setQuery("*:*");
		
//		3.使用HttpSolrServer对象，执行搜索，返回结果响应（QueryResponse）
		QueryResponse queryResponse = null;
		try {
			queryResponse = server.query(sq);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
//		4.从QueryResponse中获取搜索结果数据，SolrDocumentList
		// 4.1.获取搜索结果集
		SolrDocumentList results = queryResponse.getResults();
		
		List<User> userList = new ArrayList<User>();
		// 5.2.获取搜索结果数据
		for(SolrDocument doc:results){
			
			User user = new User();
			int id = Integer.parseInt(doc.get("id").toString());
			
			// 用户名称
			System.err.println("====================="+doc.get("user_name"));
			String name = doc.get("user_name").toString();
			
			String birthday = doc.get("user_birthday").toString();
			// 1.日期格式化对象
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sFormat.parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			user.setId(id);
			user.setName(name);
			user.setBirthday(date);
			
			userList.add(user);
		}
		
		model.addAttribute("userList", userList);
		
		return "list";
		
	}
}
