<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="header-wrapper">
	<header>
		<div class="header-left">
			<h1><a href="/index">IdeaStock</a></h1>
		</div>
		<div class="header-right">
			<div><a href="/ask">Ask</a></div>
			<div>
				<c:choose>
					<c:when test="${!empty user}">
						<a href="/logout">Logout</a>
					</c:when>
					
					<c:otherwise>
						<a href="/login">Login</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>
</div>