<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>comple - COMPUTER COUPLE</title>
</head>
<body>
		<jsp:include page="header.jsp" flush="false" />
		<c:choose>
				<c:when test="${option.equals('main')}">
					<jsp:include page="main.jsp" flush="false" />
				</c:when>
		</c:choose>
		<c:choose>
				<c:when test="${option.equals('login')}">
					<jsp:include page="login.jsp" flush="false" />
				</c:when>
		</c:choose>
		<jsp:include page="footer.jsp" flush="false" />
</body>
</html>