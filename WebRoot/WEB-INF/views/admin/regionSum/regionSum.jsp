<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/flexigrid.pack.css" />
<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/Flexigrid/flexigrid.pack.js"></script>
<script type="text/javascript"
	src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_gray"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>
<script type="text/javascript" src="js/js_user/com.js"></script>
<script type="text/javascript" src="js/cookie/cookie.js"></script>
<style type="text/css">
body {
	font-size: 13px;
}

label {
	color: black;
}

.table_1 {
	background-color: #EEF2FB;
	width: 400px;
	height: auto;
}

.input {
	width: 188px;
	height: 20px;
	margin-left: 30px;
	border: 1px solid #7F9DB9;
	vertical-align: middle;
}

.btn_all {
	font-family: PMingLiu, Helvetica, sans-serif;
	font-size: 12px;
	height: 36px;
	line-height: 36px;
	color: #000000;
	margin-left: 490px;
}

.btn {
	width: 60px;
	height: 25px;
	letter-spacing: 3px;
	margin-right: 70px;
	cursor: pointer;
}
</style>

<script type="text/javascript">
	//if (window != top) 
	//top.location.href = location.href; 
	var errInfo = "${errInfo}";
	var nameerrInfo = "项目名称不得为空";
	var sourceerrInfo = "代码源不得为空";
	//用户名或密码错误，请重新输入。
	var E0003 = "";
	var E0004 = "";
	var E00061 = "";
	var language = getCookieValue("language");
	var type = "";

	function genTimestamp() {
		var time = new Date();
		return time.getTime();
	}
	function resetErr() {
	}
	function submit() {
		resetErr();
		setCookie("language", language, 365 * 24, "/");
		var errflag = null;
		if ($("#prog_name").val() == "") {
			alert(nameerrInfo);
			$("#prog_name").focus();
			errflag = true;
		} else {
			errflag = false;
		}
		if ($("#prog_source").val() == "") {
			alert(sourceerrInfo);
			$("#prog_source").focus();
			errflag = true;
		} else {
			errflag = false;
		}
		//var str = '{"cpus": 0.5,"cmd": "ok","id":$("#prog_name"),"instances": 2,"mem": 30}'
		if (errflag == false) {

			/*		
					$.ajax({
						type : "POST",
						//url : 'http://192.168.1.89:8080/v2/apps',
						url : 'http://192.168.1.89:8080/v2/apps',
						async : false, // 使用同步方式  
						// 1 需要使用JSON.stringify 否则格式为 a=2&b=3&now=14...  
						// 2 需要强制类型转换，否则格式为 {"a":"2","b":"3"}  
						data : JSON.stringify({
							id : $('input[name="prog_name"]').val(),
							cmd : $('input[name="prog_source"]').val(),
							cpus : parseInt($('input[name="prog_cpu"]').val()),
							instances : parseInt($('input[name="prog_instances"]')	.val()),
							mem : parseInt($('input[name="prog_mem"]').val()),
							now : new Date().getTime()
						// 注意不要在此行增加逗号  
						}),
						contentType : "application/json; charset=utf-8",
						dataType : "json",
						error: function(data) {
							$('#lab_answercheck').text("aaaaaaaaaaa");
			            },
						success : function(data) {
							$('#lab_answercheck').text("aaaaaaaaaaa");
						} // 注意不要在此行增加逗号  
					});*/
		}
	}
</script>

</head>
<body style="width: 1100px; background-color: #990000;">
	<p style="width: 100%; background-color: #000000;">
		<br>
	</p>
	<p align="left"
		style="margin-left: 10%; margin-right: 10%; background-color: #EEF2FB;">
		<br> <label
			style="font-size: 30px; color: black; margin-left: 40%">创建新项目</label><br>
		<br> <label
			style="line-height: 30px; color: gray; font-size: 15px; margin-left: 3%; margin-right: 3%;">项目描述了应用从代码源，通过集成测试，到可部署镜像的自动化构建过程。项目成功创建后，每当代码源有创建标签（tag）的行为，系统都会自动触发构建流程。构建成功后，会将创建的应用镜像自动发布到您的镜像仓库中，在那可以进一步管理和部署您的应用。</label>
	</p>
	<br>
	<br>
	<form action="createaloader">
		<input id="url_war" name="url_war" class="input" /> <input
			type="submit" value="上传war包" />
	</form>
	<form id="frm_prog_creat" name="Request" method="post"
		action="createpost">
		<table align="center"
			style="background-color: #EEF2FB; margin-left: 35%; font-family: PMingLiu, Helvetica, sans-serif; font-size: 12px; height: 36px; line-height: 36px; color: #000000;">
			<tr align="center">
				<td><label style="font-size: 13px">&nbsp;&nbsp;项目名称：</label></td>
				<td><input id="prog_name" type="text" name="prog_name"
					class="input" value="${prog_name}" /><label>&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
			</tr>
			<tr align="center">
				<td><label style="font-size: 13px">&nbsp;&nbsp;代码源：</label></td>
				<td><input id="prog_source" type="text" name="prog_source"
					class="input" value="${prog_source}" /><label>&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
			</tr>
			<tr align="center">
				<td><label style="font-size: 13px">&nbsp;&nbsp;申请内存：</label></td>
				<td><input id="prog_mem" type="text" name="prog_mem"
					class="input" value="${prog_mem}" /><label>&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
			</tr>
			<tr align="center">
				<td><label style="font-size: 13px">&nbsp;&nbsp;申请存储：</label></td>
				<td><input id="prog_diskspace" type="text"
					name="prog_diskspace" class="input" value="${prog_diskspace}" /><label>&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
			</tr>
			<tr align="center">
				<td><label style="font-size: 13px">&nbsp;&nbsp;申请处理器：</label></td>
				<td><input id="prog_cpu" type="text" name="prog_cpu"
					class="input" value="${prog_cpu}" /><label>&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
			</tr>
			<tr align="center">
				<td><label style="font-size: 13px">&nbsp;&nbsp;容器个数：</label></td>
				<td><input id="prog_instances" type="text"
					name="prog_instances" class="input" value="${prog_instances}" /><label>&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
			</tr>
			<br>
		</table>
		<div class="btn_all">
			<tr align="center">
				<td><input id="login_string_loginBtn_right" type="submit"
					name="progcreatBtn" value="创建" class="btn"
					onclick="javascript:submit()" /></td>
				<td><input id="login_string_cancelBtn_right" type="reset"
					name="cancelBtn" value="清空" class="btn" /></td>
			</tr>
		</div>
	</form>






</body>
</html>