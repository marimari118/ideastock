<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="posts-container">
	<p class="hits-post">
		<span class="hits-post-msg">
			<c:choose>
				<c:when test="${!empty param.search}">
					「${f:escapeXml(param.search)}」の検索結果
				</c:when>
				
				<c:otherwise>
					全ての質問
				</c:otherwise>
			</c:choose>
			
			: ${questions.size()}件
		</span>
	</p>
	
	<div class="posts">
		<c:forEach var="question" items="${questions}">
			<div class="post">
				<div class="post-header">
					<h2 class="post-title">
						<a href="/details?id=${question.id}">${f:escapeXml(question.post.title)}</a>
					</h2>
					<p class="post-author">質問者: ${f:escapeXml(question.author.name)}</p>
				</div>
				<div class="post-body">
					<p class="post-content">${f:escapeXml(question.post.content)}</p>
				</div>
				<div class="post-footer">
					<p class="post-created-at">投稿日時: ${question.post.createdAt}</p>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
