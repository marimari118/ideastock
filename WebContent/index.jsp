<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.5.1/css/all.css">
	<link rel="stylesheet" href="/css/layout.css">
	<link rel="stylesheet" href="/css/search.css">
	<link rel="stylesheet" href="/css/post.css">
	<title>IdeaStock</title>
</head>
<body>
	<%@ include file="/WEB-INF/includes/header-template.jsp" %>
	
	<div class="main-wrapper flex-fill">
		<main>
			<div class="form-search-container">
				<form action="/index" method="GET">
					<div class="flex-fill">
						<input class="flex-fill" type="text" name="search" value="${f:escapeXml(param.search)}">
					</div>
					
					<div>
						<button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
					</div>
				</form>
			</div>
			
			<%@ include file="/WEB-INF/includes/post-template.jsp" %>
		</main>
	</div>
	
	<%@ include file="/WEB-INF/includes/footer-template.jsp" %>
</body>
</html>