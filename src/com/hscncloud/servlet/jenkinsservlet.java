package com.hscncloud.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.MainView;

import static org.mockito.Mockito.mock;
/**
 * Servlet implementation class jenkinsservlet
 */
@WebServlet("/jenkinsservlet")
public class jenkinsservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public jenkinsservlet() {
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
		PrintWriter printWriter = response.getWriter();
//		URI jenkinsUrl = null;
//		try {
//			jenkinsUrl = new URI("http://localhost:8080/");
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
		
		HttpClient client = new HttpClient();

        String hostName = "http://192.168.1.89:8080/createItem";
        GetMethod loginLink = new GetMethod(hostName+"loginEntry");
        client.executeMethod(loginLink);
        checkResult(loginLink.getStatusCode());

        String location = hostName+"j_security_check";
        while(true) {
            PostMethod loginMethod = new PostMethod(location);
            loginMethod.addParameter("j_username", "hscn123"); // TODO: replace with real user name and password
            loginMethod.addParameter("j_password", "hscn123");
            loginMethod.addParameter("action", "login");
            client.executeMethod(loginMethod);
            if(loginMethod.getStatusCode()/100==3) {
                // Commons HTTP client refuses to handle redirects for POST
                // so we have to do it manually.
                location = loginMethod.getResponseHeader("Location").getValue();
                continue;
            }
            checkResult(loginMethod.getStatusCode());
            break;
        }
        HttpMethod method = new GetMethod(hostName+"log");
        client.executeMethod(method);
        checkResult(method.getStatusCode());
        System.out.println(method.getResponseBodyAsString());
			
		// JenkinsHttpClient client = mock(JenkinsHttpClient.class);
	   //  JenkinsServer server = new JenkinsServer(client);
	   //  MainView mainView = new MainView(new Job("Hello", "http://localhost/job/Hello/"));
	//	 JenkinsServer jenkinsServer =new JenkinsServer(jenkinsUrl, "hscn123", "hscn123"); 
		 
		  //jenkinsServer.getJob("demo");
		//  jenkinsServer.deleteJob("demo");
	     /* URI url1 = null;
	  try {
		url1= new URI("http://192.168.1.89:8080/me/my-views/view/All/job/demo/14/consoleText");
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		JenkinsHttpClient client = new JenkinsHttpClient(jenkinsUrl,"hscn123","hscn123");
		printWriter.println("aaaaaaaaaa");
		InputStream inputStream = client.getFile(url1);
		printWriter.println(inputStream);*/
		
	}
	private static void checkResult(int i) throws IOException {
        if(i/100!=2)
            throw new IOException();
    }

}
