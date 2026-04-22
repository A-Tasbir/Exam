<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>

	<c:param name="content">

	<section class="me-4">

		<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
			確認
		</h2>

		<div class="px-4">

			<p class="text-danger fw-bold">
				この変更を行うと、先生管理機能へのアクセス権を失います。<br>
				続行しますか？
			</p>

			<form method="post" action="TeacherEditFinal.action">

				<input type="hidden" name="id" value="${id}">
				<input type="hidden" name="password" value="${password}">
				<input type="hidden" name="name" value="${name}">
				<input type="hidden" name="admin" value="off">

				<button class="btn btn-danger">続行する</button>
				<a href="TeacherList.action" class="btn btn-secondary">キャンセル</a>

			</form>

		</div>

	</section>

	</c:param>
</c:import>