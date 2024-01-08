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
	<link rel="stylesheet" href="/css/ask.css">
	<title>IdeaStock - Ask</title>
</head>
<body>
	<%@ include file="/WEB-INF/includes/header-template.jsp" %>
	
	<div class="main-wrapper flex-fill">
		<main class="flex-fill">
			<div class="form-ask-container">
				<form action="/post/question" method="POST">
					<p class="form-title">質問を投稿する</p>
					
					<div class="form-content">
						<div class="form-ask-title">
							<p>タイトル</p>
							<input type="text" name="title" value="${f:escapeXml(param.title)}">
						</div>
						
						<div class="form-ask-content">
							<p>質問内容</p>
							<textarea name="content">${f:escapeXml(param.content)}</textarea>
						</div>
	
						<div>
							<input type="submit" value="Login">
						</div>
					</div>
				</form>
			</div>
		</main>
	</div>
	
	<%@ include file="/WEB-INF/includes/footer-template.jsp" %>
</body>
</html>