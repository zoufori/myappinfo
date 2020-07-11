<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/7
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>后台管理系统</title>
  <!-- Bootstrap -->
  <link href="${pageContext.request.contextPath }/static/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome -->
  <link href="${pageContext.request.contextPath }/static/css/font-awesome.min.css" rel="stylesheet">
  <!-- NProgress -->
  <link href="${pageContext.request.contextPath }/static/css/nprogress.css" rel="stylesheet">
  <!-- Animate.css -->
<%--  <link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet">--%>
  <!-- Custom Theme Style -->
  <link href="${pageContext.request.contextPath }/static/css/custom.min.css" rel="stylesheet">
</head>


<body class="login">
<div class="login_wrapper">
  <h1>APP信息管理平台</h1>
  <div>
      <a href="${ctx}/backenduser/backendlogin.html" class="btn btn-link">后台管理系统 入口</a>
    </div>
    <div>
      <a href="${ctx}/devuser/devlogin.html"  class="btn b  tn-link ">开发者平台 入口</a>
    </div>
    <div>
      <p>2018.9月 广州菁英-JT01</p>
    </div>
  </div>
  </body>
</html>
