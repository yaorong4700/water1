package com.hscncloud.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.offbytwo.jenkins.JenkinsServer;

/**
 * Servlet implementation class shellrun
 */
@WebServlet("/shellrun")
public class shellrun extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public shellrun() {
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
		PrintWriter writer = response.getWriter();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		writer.println("");
		// String lne = "";

		try {
			getDataByShellCMD(response);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("eeeeeeerr");
		}
		// writer.println(lne);
	}

	private void getDataByShellCMD(HttpServletResponse response) throws InterruptedException {
		Process process = null;
		List<String> processList = new ArrayList<String>();
		try {
			String[] command = { "./dockercomposefile/docker-compose.sh" };
			PrintWriter writer = response.getWriter();
		//	String shpath = "/WebRoot/shelltext/test.sh";
		//	String command1 = "echo \"hscn123\" |sudo -S chmod +x " + shpath;
			process = Runtime.getRuntime().exec(command);
			//process.waitFor();
			//String[] command2  = { "/bin/bash",  shpath, "" };
		//String[] command2 = { "/bin/bash", "-c", " echo \"hscn123\" |sudo -S ../test.sh" };
		 //   process = Runtime.getRuntime().exec("cd  /WebRoot/shelltext/");
			//process = Runtime.getRuntime().exec(command2);
			int exitValue = process.waitFor();
						System.out.println(exitValue);
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
				writer.println(line);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String line : processList) {
			System.out.println(line);
		
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
