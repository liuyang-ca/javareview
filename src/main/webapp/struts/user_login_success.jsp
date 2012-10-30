<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>

<h1>Login Success</h1>
<h5>request.getScheme() = <%out.println(request.getScheme());%></h5>
<h5>request.getServerName() = <%out.println(request.getServerName());%></h5>
<h5>request.getServerPort() = <%out.println(request.getServerPort());%></h5>
<h5>request.getContextPath() = <%out.println(request.getContextPath());%></h5>
<h5>request.getServletPath() = <%out.println(request.getServletPath());%></h5>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<h4>basePath = <%out.println(basePath);%></h4>


<a href="user_login">Logout</a>
<a href="<%out.println(basePath);%>/back/user_login.jsp">Logout2</a>
</body>
</html>