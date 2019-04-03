package com.web115.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web115.dao.DBUtils;
import com.web115.dao.XMLUtils;

public class RegisterServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 设置web容器编码格式
		req.setCharacterEncoding("utf-8"); 
		
		List<String> properties = new ArrayList<String>();
		List values = new ArrayList();
		String result = "success";

		properties.add("id");
		properties.add("username");
		properties.add("password");
		properties.add("email");
		properties.add("phone");

		String name = req.getParameter("name");
		
		DBUtils.getConnection("web115");

		values.add(DBUtils.getNumber("user") + 1);
		values.add(name);
		values.add(req.getParameter("password"));
		values.add(req.getParameter("email"));
		values.add(req.getParameter("phone"));

		DBUtils.insert("user", properties, values);
		DBUtils.closeAll();

		/*// 将注册成功的消息保存在相应的XML文件中
		XMLUtils xmlUtils = new XMLUtils(
				"D:\\MyFile\\MyWorkSpace\\JavaWorkSpace\\Java_Web_JingDong\\WebContent\\xml\\" + name + ".xml",
				"register");
		xmlUtils.setRootContent(result);
		xmlUtils.generateXML();*/
		
		resp.setStatus(302);
		resp.addHeader("location", "/XUTMOOC/login.html");
	}

}
