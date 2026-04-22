<%-- 学生変更完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生変更完了</h2>
			<div class="px-4">
				<p class="text-center" style="background-color:#66CC99">学生情報を変更しました</p>
				<a href="StudentList.action">学生一覧へ戻る</a>
			</div>
		</section>
	</c:param>
</c:import>
