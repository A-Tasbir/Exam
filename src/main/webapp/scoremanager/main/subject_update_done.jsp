<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
	uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">

		得点管理システム

	</c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-3 fw-normal
				bg-secondary bg-opacity-10
				py-2 px-4">

				科目変更完了

			</h2>

			<div class="alert alert-success text-center">

				${message}

			</div>

			<div class="mt-5 px-4">

				<a href="SubjectList.action">

					科目一覧へ戻る

				</a>

			</div>

		</section>

	</c:param>

</c:import>