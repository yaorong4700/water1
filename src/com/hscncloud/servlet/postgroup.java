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
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;



/**
 * Servlet implementation class postgroup
 */
@WebServlet("/postgroup")
public class postgroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public postgroup() {
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
	 public String mydoGet( String uriAPI)  
	    {  
	        String result= "";  
//	      创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。  
	        HttpGet httpRequst = new HttpGet(uriAPI);  
	        try {  
	   //使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。  
	            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);//其中HttpGet是HttpUriRequst的子类  
	            if(httpResponse.getStatusLine().getStatusCode() == 200)  
	            {  
	                HttpEntity httpEntity = httpResponse.getEntity();  
	                result = EntityUtils.toString(httpEntity);//取出应答字符串  
	            // 一般来说都要删除多余的字符   
	                result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格    
	            }  
	                   else   
	                        httpRequst.abort();  
	           } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	            result = e.getMessage().toString();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            result = e.getMessage().toString();  
	        }  
	        return result;  
	    } 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String str = "{\"id\":\"/user2/task1\",\"cmd\":null,\"cpus\":1,\"mem\":128,\"disk\":0,\"instances\":1,\"container\":{\"type\":\"DOCKER\",\"volumes\":[],\"docker\":{\"image\":\"ubuntu/tomcat7\",\"network\":\"BRIDGE\",\"portMappings\":[{\"containerPort\":8080,\"hostPort\":0,\"protocol\":\"tcp\",\"labels\":{}}],\"privileged\":false,\"parameters\":[{\"key\":\"volumes-from\",\"value\":\"mesos-603fc270-eeae-4801-a39e-e73efb958262-S0.88836025-08b2-422c-88c2-9fe55c2e94fb\"}],\"forcePullImage\":false}},\"portDefinitions\":[{\"port\":10002,\"protocol\":\"tcp\",\"labels\":{}}]}";
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String result = null;
		String getresult="";
		String encoderJson = URLEncoder.encode(str, HTTP.UTF_8);
		String s = URLDecoder.decode(encoderJson, HTTP.UTF_8);
		getresult=mydoGet("http://192.168.1.89:8080/v2/apps/user2/warloader2");
		//HttpGet httpGet= new HttpGet(" http://192.168.1.89:8080/v2/apps/user2/warloader2");
		//writer.println(getresult);
		JSONObject obj = JSONObject.fromObject(getresult);  
	//	writer.println(obj);
		writer.println(obj.getString("tasks"));
		
		
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
	//		writer.println("your data: " + str);
	//		writer.println("<script>" + "alert('something wrong !!!!!');" + "</script>");
	//		writer.println(httpResponse.getStatusLine().getStatusCode());
	//		writer.println(result);
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
