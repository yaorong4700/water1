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

import net.sf.json.JSONObject;

/**
 * Servlet implementation class createpost
 */
@WebServlet("/createpost")
public class createpost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public createpost() {
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
		String createseccessinfo = "seccess!";
		String Prog_ID = request.getParameter("prog_name");
		String Prog_CPU = request.getParameter("prog_cpu");
		double d_Prog_CPU = Double.parseDouble(Prog_CPU);
		String Prog_Mem = request.getParameter("prog_mem");
		double d_Prog_Mem = Double.parseDouble(Prog_Mem);
		String Prog_DiskSpace = request.getParameter("prog_diskspace");
		double d_Prog_DiskSpace = Double.parseDouble(Prog_DiskSpace);
		String Prog_Instances = request.getParameter("prog_instances");
		double d_Prog_Instances = Double.parseDouble(Prog_Instances);
		String Prog_CMD = request.getParameter("prog_source");
		PrintWriter writer = response.getWriter();
		JSONObject obj = new JSONObject();
		obj.put("id", Prog_ID);
		obj.put("cpus", d_Prog_CPU);
		obj.put("mem", d_Prog_Mem);
		obj.put("instances", d_Prog_Instances);
		obj.put("disk", d_Prog_DiskSpace);
		obj.put("cmd", Prog_CMD);
		//writer.println("your data: " + obj);
		String str = obj.toString();
		String result = null;
		String encoderJson = URLEncoder.encode(str, HTTP.UTF_8);
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
		response.setCharacterEncoding("UTF-8");//设置编码
		response.setContentType("text/html");//服务器响应类型
		if (httpResponse.getStatusLine().getStatusCode()!= 201) {
			writer.println("your data: " + obj);
			writer.println("<script>" + "alert('something wrong !!!!!');"+ "</script>");
			writer.println(httpResponse.getStatusLine().getStatusCode());
			writer.println(result);
			writer.close();
		}
		if (httpResponse.getStatusLine().getStatusCode() == 201) {
			request.setAttribute("id", Prog_ID);
			writer.print("<script>" + "alert('success!');"+ "</script>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/rrrr.jsp");
			requestDispatcher.forward(request, response);
			writer.close();
		}
	}

}
