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
			<div class="form-post-container">
				<form action="/post/question" method="POST">
					<p class="form-title">質問を投稿する</p>
					
					<div class="form-content">
						<div class="form-post-title">
							<p>タイトル</p>
							<input type="text" name="title" value="${f:escapeXml(param.title)}">
							<c:if test="${!empty requestParam.errors.title}">
								<ul class="form-error">
									<c:forEach var="error" items="${errors.title}">
										<li>${error}</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
						
						<div class="form-post-content">
							<p>質問内容</p>
							<textarea name="content">${f:escapeXml(param.content)}</textarea>
							<c:if test="${!empty errors.content}">
								<ul class="form-error">
									<c:forEach var="error" items="${errors.content}">
										<li>${error}</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
						
						<c:if test="${!empty errors.other}">
							<ul class="form-error">
								<c:forEach var="error" items="${errors.other}">
									<li>${error}</li>
								</c:forEach>
							</ul>
						</c:if>
	
						<div>
							<input type="submit" value="投稿">
						</div>
					</div>
				</form>
			</div>
		</main>
	</div>
	
	<%@ include file="/WEB-INF/includes/footer-template.jsp" %>
</body>
</html>