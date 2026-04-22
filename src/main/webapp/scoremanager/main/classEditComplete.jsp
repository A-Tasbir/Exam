<%-- クラス変更完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>

	<c:param name="content">

		<section class="me-4">

			<!-- HEADER (same pattern as Student) -->
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				クラス変更完了
			</h2>

			<div class="px-4">

				<!-- SUCCESS MESSAGE (same style) -->
				<p class="text-center" style="background-color:#66CC99">
					クラス情報を変更しました
				</p>

				<!-- BACK LINK (same pattern as others) -->
				<a href="ClassList.action">クラス一覧へ戻る</a>

			</div>

		</section>

	</c:param>
</c:import>