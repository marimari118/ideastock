<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.5.1/css/all.css">
	<link rel="stylesheet" href="/css/layout.css">
	<link rel="stylesheet" href="/css/login.css">
	<title>IdeaStock - Signup</title>
</head>
<body>
	<%@ include file="/WEB-INF/includes/header-template.jsp"%>

	<div class="main-wrapper flex-fill">
		<main class="flex-fill">
			<div class="form-login-container">
				<form action="/register" method="POST">
					<div class="form-login-title">
						<h2>Signup</h2>
					</div>
					
					<div class="form-login-content">
						<div>
							<input type="text" name="name" placeholder="Username" value="${param.name}">
							<c:if test="${!empty errors.name}">
								<ul class="form-error">
									<c:forEach var="error" items="${errors.name}">
										<li>${error}</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
						
						<div>
							<input type="text" name="loginId" placeholder="Login ID" value="${param.loginId}">
							<c:if test="${!empty errors.loginId}">
								<ul class="form-error">
									<c:forEach var="error" items="${errors.loginId}">
										<li>${error}</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
	
						<div>
							<input type="password" name="password" placeholder="Password" value="${param.password}">
							<c:if test="${!empty errors.password}">
								<ul class="form-error">
									<c:forEach var="error" items="${errors.password}">
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
							<input type="submit" value="Signup">
						</div>
					</div>
				</form>
			</div>
		</main>
	</div>

	<%@ include file="/WEB-INF/includes/footer-template.jsp"%>
</body>
</html>