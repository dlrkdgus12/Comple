<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script type="text/javascript" src="//code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script>
/* 유저 정보 불러오기 */
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
<div class="main-container">
   <section class="cover fullscreen image-bg overlay">
       <div class="background-image-holder">
           <img alt="image" class="background-image" src="img/home12.jpg" />
       </div>
       <div class="container v-align-transform">
           <div class="row">
               <div class="col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2">
                   <div class="feature bordered text-center">
                       <h4 class="uppercase">Login Here</h4>
                       <form class="text-left">
                           <input class="inputLoginid" type="text" placeholder="Username" />
                           <input class="inputPassword" type="password" placeholder="Password" />
                           <button type='submit' class='blue' onclick='login()'>LOG IN</button>
                       </form>
                   </div>
               </div>
           </div>
           <!--end of row-->
       </div>
       <!--end of container-->
   </section>
 </div>