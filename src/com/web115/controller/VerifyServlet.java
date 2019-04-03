package com.web115.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web115.dao.DBUtils;
import com.web115.dao.XMLUtils;

public class VerifyServlet extends HttpServlet {

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
		
		String name = req.getParameter("username");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");

		String type = null; // 前端提交的数据类别
		String result = null;

		if (name != null) {
			// 确定数据类别
			type = "name";
			// 去验证
			DBUtils.getConnection("web115");
			if (DBUtils.selectProperty("user", "username", name) == 0) {
				result = "namedafeat";
			} else {
				result = "namesuccess";

			}
			DBUtils.closeAll();
		}
		if (email != null) {
			// 确定数据类别
			type = "email";
			// 去验证
			DBUtils.getConnection("web115");
			if (DBUtils.selectProperty("user", "email", email) == 0) {
				result = "emaildafeat";
			} else {
				result = "emailsuccess";
			}
			DBUtils.closeAll();
		}
		if (phone != null) {
			// 确定数据类别
			type = "phone";
			// 去验证
			DBUtils.getConnection("web115");
			if (DBUtils.selectProperty("user", "phone", phone) == 0) {
				result = "phonedafeat";
			} else {
				result = "phonesuccess";
			}
			DBUtils.closeAll();
		}
		
		System.out.println(name + "," + email + "," + phone);

		/*// 将结果保存在xml文件中
		// 创建一个XMLUtils对象，用来操作xml文件
		XMLUtils xmlUtils = new XMLUtils(
				"D:\\MyFile\\MyWorkSpace\\JavaWorkSpace\\Java_Web_JingDong\\WebContent\\xml\\" + name + ".xml",
				"verify");
		xmlUtils.setRootAttribute("type", type);
		xmlUtils.setRootContent(result);
		xmlUtils.generateXML();*/
		
		File file = new File("D:\\MyFile\\MyWorkSpace\\JavaWorkSpace\\Java_Web_JingDong\\WebContent\\txt\\verify.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(result.getBytes());
		fileOutputStream.flush();
		fileOutputStream.close();

		resp.setStatus(302);
		resp.addHeader("location", "/Java_Web_JingDong/register.html");

	}

}
