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
        <link href="${ctx}/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
        <link href="${ctx}/css/themify-icons.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${ctx}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${ctx}/css/theme.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${ctx}/css/custom.css" rel="stylesheet" type="text/css" media="all" />
        <link href='http://fonts.googleapis.com/css?family=Lato:300,400%7CRaleway:100,400,300,500,600,700%7COpen+Sans:400,500,600' rel='stylesheet' type='text/css'>
    </head>
    <body>
				
		<div class="nav-container">
		</div>
		
		<div class="main-container">
					
			<footer class="footer-1 bg-dark">
				<div class="container">
					<div class="row">
						<div class="col-md-3 col-sm-6">
							<img alt="Logo" class="logo" src="img/logo-light.png">
						</div>
						<div class="col-md-3 col-sm-6">
							<div class="widget">
								<h6 class="title">Recent Posts</h6>
								<hr>
								<ul class="link-list recent-posts">
									<li>
										<a href="#">Hugging pugs is super trendy</a>
										<span class="date">February
											<span class="number">14, 2015</span>
										</span>
									</li>
									<li>
										<a href="#">Spinning vinyl is oh so cool</a>
										<span class="date">February
											<span class="number">9, 2015</span>
										</span>
									</li>
									<li>
										<a href="#">Superior theme design by pros</a>
										<span class="date">January
											<span class="number">27, 2015</span>
										</span>
									</li>
								</ul>
							</div>
							
						</div>
						<div class="col-md-3 col-sm-6">
							<div class="widget">
								<h6 class="title">Latest Updates</h6>
								<hr>
								<div class="twitter-feed">
									<div class="tweets-feed" data-feed-name="Comple_kanghyun">
									</div>
								</div>
							</div>
							
						</div>
						<div class="col-md-3 col-sm-6">
							<div class="widget">
								<h6 class="title">Instagram</h6>
								<hr>
								<div class="instafeed" data-user-name="">
									<ul id="instafeed"></ul>
								</div>
							</div>
							
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-6">
							<span class="sub">Â© Copyright 2016 - Comple</span>
						</div>
						<div class="col-sm-6 text-right">
							<ul class="list-inline social-list">
								<li>
									<a href="#">
										<i class="ti-twitter-alt"></i>
									</a>
								</li>
								<li>
									<a href="#">
										<i class="ti-facebook"></i>
									</a>
								</li>
								<li>
									<a href="#">
										<i class="ti-dribbble"></i>
									</a>
								</li>
								<li>
									<a href="#">
										<i class="ti-vimeo-alt"></i>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				
				<a class="btn btn-sm fade-half back-to-top inner-link" href="#top">Top</a>
			</footer>
		</div>
		
				
	<script src="${ctx}/js/jquery.min.js"></script>
        <script src="${ctx}/js/bootstrap.min.js"></script>
        <script src="${ctx}/js/twitterfetcher.min.js"></script>
        <script src="${ctx}/js/smooth-scroll.min.js"></script>
        <script src="${ctx}/js/parallax.js"></script>
        <script src="${ctx}/js/scripts.js"></script>
        <script src="${ctx}/js/instafeed.min.js"></script>
        <script type="text/javascript">
		    var userFeed = new Instafeed({
		        get: 'user',
		        userId: '3589142833',
		        accessToken: '3589142833.1677ed0.c1d1a9deb9164efa99916ae62339f6ef',
		        template: '<li><a href="{{link}}"><img src="{{image}}" /></a></li>'
		    });
		    userFeed.run();
		</script>
    </body>
</html>
				