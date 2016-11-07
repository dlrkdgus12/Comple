<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${ctx}/css/themify-icons.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${ctx}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${ctx}/css/theme.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${ctx}/css/custom.css" rel="stylesheet" type="text/css" media="all" />
        <link href='http://fonts.googleapis.com/css?family=Lato:300,400%7CRaleway:100,400,300,500,600,700%7COpen+Sans:400,500,600' rel='stylesheet' type='text/css'>
    </head>
    <script>
	/* 유저 정보 불러오기 */
	$(document).ready(function(){
		if (getCookie("email").length > 0){
			$('#inputLoginid').val(getCookie("email"));
			$('input:checkbox[id="remember"]').prop('checked',true);
		}
		
	});
	/* 로그인 기능 시작 */
	function login() {
		/* 유효성 검사 */
		if ($('#inputLoginid').val() == "") {
			alert("아이디를 입력해주세요.");
			return false;
		}

		if ($('#inputPassword').val() == "") {
			alert("비밀번호를 입력해주세요.");
			return false;
		}

		/* 로그인 시작 */
		var parameter = "loginid=" + $('#inputLoginid').val() + "&passwd="
				+ $('#inputPassword').val();
		loadAJAX("${ctx}/login.json", parameter, handleLoginSuccess, handleLoginFailed);
	}
	function handleLoginFailed(reqeust,status,response){
		alert(reqeust +" "+ status +" "+ response.status);
		
		return false;
	}
	
	function handleLoginSuccess(request, status, response) {
		/* 로그인 성공시 */
		if (response.status == 1) {
			if ($('input:checkbox[id="remember"]').is(":checked")) {
				setCookie("email", $('#inputLoginid').val(), 30);
			}
			/* 리다이렉트 할 페이지 */
			location.href = '${ctx}/user/dashboard.n';
		} else if (response.status == 0) {
			alert("아이디나 비밀번호가 올바르지 않습니다.");
			return false;
		}
	}
	
	function setCookie(name, value, expireday){
		var today = new Date();
		today.setDate(today.getDate() + expireday);
		document.cookie = 
			name + "=" + escape(value) + "; path=/; expires=" + 
			today.toGMTString() + ";";
	}
	
	function getCookie(name){
		var nameOfCookie = name + "=";
		var x = 0;
		
		while (x <= document.cookie.length){
			var y = (x + nameOfCookie.length);
			if (document.cookie.substring(x, y) == nameOfCookie){
				if ((endOfCookie=document.cookie.indexOf(";", y)) == -1){
					endOfCookie = document.cookie.length;
				}
				return unescape(document.cookie.substring(y, endOfCookie));
			}
			x = document.cookie.indexOf("", x) + 1;
			if (x == 0)
				break;
		}
	}
	function onKeyDown()
	{
	     if(event.keyCode == 13 || event.which == 13)
	     {
	          login();
	     }
	}
</script>
    <body>	
		<div class="nav-container">
		    <nav>
		        <div class="nav-utility">
		            <div class="module left">
		                <i class="ti-email">&nbsp;</i>
		                <span class="sub">kanghyun@comple.com</span>
		            </div>
		            <div class="module left">
		                <i class="ti-email">&nbsp;</i>
		                <span class="sub">Jiny@comple.com</span>
		            </div>
		            <div class="module right">
		                <a class="btn btn-sm" href="${ctx}/login.do">Login</a>
		            </div>
		        </div>
		        <div class="nav-bar">
		            <div class="module left">
		            	<!-- 
		                <a href="${ctx}/">
		                    <img class="logo logo-light" alt="Foundry" src="img/logo-light.png">
		                    <img class="logo logo-dark" alt="Foundry" src="img/logo-dark.png">
		                </a>
		            	 -->
		            </div>
		            <div class="module widget-handle mobile-toggle right visible-sm visible-xs">
		                <i class="ti-menu"></i>
		            </div>
		            <div class="module-group right">
		                <div class="module left">
		                    <ul class="menu">
		                        <li>
		                            <a href="#">Home</a>
		                        </li>
		                        <li class="has-dropdown">
		                            <a href="#">
		                                Project
		                            </a>
		                            <ul class="mega-menu">
		                                <li>
		                                    <ul>
		                                        <li>
		                                            <span class="title">Column 1</span>
		                                        </li>
		                                        <li>
		                                            <a href="#">Single</a>
		                                        </li>
		                                    </ul>
		                                </li>
		                                <li>
		                                    <ul>
		                                        <li>
		                                            <span class="title">Column 2</span>
		                                        </li>
		                                        <li>
		                                            <a href="#">Single</a>
		                                        </li>
		                                    </ul>
		                                </li>
		                            </ul>
		                        </li>
		                        <li class="has-dropdown">
		                            <a href="#">
		                                Blog
		                            </a>
		                            <ul>
		                                <li class="has-dropdown">
		                                    <a href="#">
		                                        Second Level
		                                    </a>
		                                    <ul>
		                                        <li>
		                                            <a href="#">
		                                                Single
		                                            </a>
		                                        </li>
		                                    </ul>
		                                </li>
		                            </ul>
		                        </li>
		                    </ul>
		                </div>
		                
		                <div class="module widget-handle language left">
		                    <ul class="menu">
		                        <li class="has-dropdown">
		                            <a href="#">ENG</a>
		                            <ul>
		                                <li>
		                                    <a href="#">French</a>
		                                </li>
		                                <li>
		                                    <a href="#">Deutsch</a>
		                                </li>
		                            </ul>
		                        </li>
		                    </ul>
		                </div>
		            </div>
		            
		        </div>
		    </nav>
		
		    
		    
		
		    
		    
		
		    
		    
		
		    
		    
		
		    
		    
		
		    
		    
		
		    
		    
		
		    
		
		    
		    
		
		    
		
		    
		    
		
		    
		
		    
		    
		
		    
		    
		
		    
		    
		
		    
		    
		
		    
		    
		
		    
		    
		
		    
		    
		
		    
		    
		
		</div>
		
		<div class="main-container">
		</div>
		
				
	<script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/parallax.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
				