package com.web115.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Array;
import com.web115.dao.DBUtils;
import com.web115.dao.XMLUtils;

public class LoginServlet extends HttpServlet {

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
		
		// 获取前端提交的用户账号和密码
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		String result = null;

//		System.out.println(name + "," + password);
		
		DBUtils.getConnection("web115");
		String[] properties = { "username", "password" };
		String[] values = { name, password };
		if (DBUtils.selectProperties("user", properties, values) == 0) {
			result = "success";
		} else {
			result = "defeat";
		}
		DBUtils.closeAll();

		// 如果登录成功，直接进行页面跳转；
		// 否则，重新跳转回登录界面
		if (result == "success") {
			System.out.println(result);
			resp.setStatus(302);
			resp.addHeader("location", "/XUTMOOC/index.html");
		} else {

			/*// 将结果保存在xml文件中
			// 创建一个XMLUtils对象，用来操作xml文件
			XMLUtils xmlUtils = new XMLUtils(
					"D:\\MyFile\\MyWorkSpace\\JavaWorkSpace\\Java_Web_JingDong\\WebContent\\xml\\" + name + ".xml",
					"login");
			xmlUtils.setRootContent(result);
			xmlUtils.generateXML();*/
			System.out.println(result);
			resp.setStatus(302);
			resp.addHeader("location", "/XUTMOOC/login.html");

		}

	}

}
