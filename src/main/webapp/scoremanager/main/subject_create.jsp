<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		科目登録
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				科目登録
			</h2>

			<form action="SubjectCreateExecute.action" method="post">

				<div class="mb-3">

					<label class="form-label">
						科目コード
					</label>

					<input type="text"
						name="cd"
						class="form-control">

				</div>

				<div class="mb-3">

					<label class="form-label">
						科目名
					</label>

					<input type="text"
						name="name"
						class="form-control">

				</div>

				<button type="submit"
					class="btn btn-primary">

					登録

				</button>

			</form>

		</section>

	</c:param>

</c:import>