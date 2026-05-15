<%-- ヘッダー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-decoration-none">
	<h1 class="fs-1 m-0">得点管理システム</h1>
</div>

<c:if test="${user.isAuthenticated()}">
	<div class="nav align-items-center">

		<span class="nav-item px-2">
			${user.getName()}様
		</span>

		<button
			id="themeToggle"
			type="button"
			class="btn btn-sm btn-outline-dark mx-2">
			🌙
		</button>

		<a class="nav-item px-2" href="Logout.action">
			ログアウト
		</a>

	</div>
</c:if>