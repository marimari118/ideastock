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
	<link rel="stylesheet" href="/css/details.css">
	<title>IdeaStock - Details</title>
</head>
<body>
	<%@ include file="/WEB-INF/includes/header-template.jsp" %>
	
	<div class="main-wrapper flex-fill">
		<main class="flex-fill">
			<div class="posts-container">
				<div class="questions posts">
					<div class="question post flex-fill">
						<p class="group-title">質問</p>
						
						<div class="post-header">
							<h2 class="post-title">${f:escapeXml(question.post.title)}</h2>
							<p class="post-author">質問者: ${f:escapeXml(question.author.name)}</p>
						</div>
						<div class="post-body">
							<p class="post-content">${f:escapeXml(question.post.content)}</p>
						</div>
						<div class="post-footer">
							<p class="post-created-at">投稿日時: ${question.post.createdAt}</p>
							<div class="post-actions">
								<form action="/answer" method="GET">
									<input type="hidden" name="id" value="${question.id}">
									<input type="submit" value="回答">
								</form>
								
								<c:if test="${question.author.id == user.id}">
									<form action="/delete/question" method="POST">
										<input type="hidden" name="id" value="${question.id}">
										<input class="button-delete" type="submit" value="削除">
									</form>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				
				<p class="count-post">
					<span class="count-post-msg">
						回答: ${answers.size()}件
					</span>
				</p>
				
				<div class="answers posts">
					<c:forEach var="answer" items="${answers}">
						<div class="answer post">
							<div class="post-header">
								<h2 class="post-title">${f:escapeXml(answer.post.title)}</h2>
								<p class="post-author">質問者: ${f:escapeXml(answer.author.name)}</p>
							</div>
							<div class="post-body">
								<p class="post-content">${f:escapeXml(answer.post.content)}</p>
							</div>
							<div class="post-footer">
								<p class="post-created-at">投稿日時: ${answer.post.createdAt}</p>
								<div class="post-actions">
									<c:if test="${answer.author.id == user.id}">
										<form action="/delete/answer" method="POST">
											<input type="hidden" name="id" value="${answer.id}">
											<input class="button-delete" type="submit" value="削除">
										</form>
									</c:if>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</main>
	</div>
	
	<%@ include file="/WEB-INF/includes/footer-template.jsp" %>
</body>
</html>