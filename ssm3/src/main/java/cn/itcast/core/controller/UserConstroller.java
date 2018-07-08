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
 * �û�controller
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
		// 1.���ڸ�ʽ������
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
//		1.����HttpSolrServer��������solr����
		HttpSolrServer server = new HttpSolrServer("http://127.0.0.1:8088/solr/");
		
//		2.������ѯ����SolrQuery��
		SolrQuery sq = new SolrQuery();
		
//		��ѯ���ʽq
		sq.setQuery("*:*");
		
//		3.ʹ��HttpSolrServer����ִ�����������ؽ����Ӧ��QueryResponse��
		QueryResponse queryResponse = null;
		try {
			queryResponse = server.query(sq);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
//		4.��QueryResponse�л�ȡ����������ݣ�SolrDocumentList
		// 4.1.��ȡ���������
		SolrDocumentList results = queryResponse.getResults();
		
		List<User> userList = new ArrayList<User>();
		// 5.2.��ȡ�����������
		for(SolrDocument doc:results){
			
			User user = new User();
			int id = Integer.parseInt(doc.get("id").toString());
			
			// �û�����
			System.err.println("====================="+doc.get("user_name"));
			String name = doc.get("user_name").toString();
			
			String birthday = doc.get("user_birthday").toString();
			// 1.���ڸ�ʽ������
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
