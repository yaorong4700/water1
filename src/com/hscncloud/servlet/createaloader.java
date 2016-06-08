package com.hscncloud.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * Servlet implementation class createaloader
 */
@WebServlet("/createaloader")
public class createaloader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public createaloader() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String War_url = request.getParameter("url_war");
		PrintWriter writer = response.getWriter();
		String str1 = "{\"id\":\"/user2/warloader2\",\"cmd\":null,\"cpus\":0.2,\"mem\":32,\"disk\":0,\"instances\":0,\"container\":{\"type\":\"DOCKER\",\"volumes\":[],\"docker\":{\"image\":\"yrrr/fetcher\",\"network\":\"BRIDGE\",\"portMappings\":[{\"containerPort\":0,\"hostPort\":0,\"protocol\":\"tcp\",\"labels\":{}}],\"privileged\":false,\"parameters\":[],\"forcePullImage\":false}},\"portDefinitions\":[{\"protocol\":\"tcp\",\"labels\":{}}],\"args\":[\""+War_url+"\"]}";                               
		String result = null;
		String encoderJson = URLEncoder.encode(str1, HTTP.UTF_8);
		String s = URLDecoder.decode(encoderJson, HTTP.UTF_8);

		HttpPost httpPost = new HttpPost("http://192.168.1.89:8080/v2/apps");
		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
		StringEntity se = new StringEntity(s);
		se.setContentType("text/json");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		httpPost.setEntity(se);
		HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);

		HttpEntity httpEntity = httpResponse.getEntity();
		result = EntityUtils.toString(httpEntity);// 取出应答字符串
		response.setCharacterEncoding("UTF-8");// 设置编码
		response.setContentType("text/html");// 服务器响应类型

		if (httpResponse.getStatusLine().getStatusCode() != 201) {
			writer.println("your data: " + str1);
			writer.println("<script>" + "alert('something wrong !!!!!');" + "</script>");
			writer.println(httpResponse.getStatusLine().getStatusCode());
			writer.println(result);
			writer.close();
		}
		if (httpResponse.getStatusLine().getStatusCode() == 201) {
			writer.print("<script>" + "alert('success!');" + "</script>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/rrrr.jsp");
			requestDispatcher.forward(request, response);
			writer.close();
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}

}
