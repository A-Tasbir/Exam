<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		科目変更
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				科目変更
			</h2>

			<form action="SubjectEditExecute.action" method="post">

				<div class="mb-3">

					<label class="form-label">
						科目コード
					</label>

					<input type="text"
						name="cd"
						value="${subject.cd}"
						class="form-control"
						readonly>

				</div>

				<div class="mb-3">

					<label class="form-label">
						科目名
					</label>

					<input type="text"
						name="name"
						value="${subject.name}"
						class="form-control">

				</div>

				<button type="submit"
					class="btn btn-primary">

					変更

				</button>

			</form>

		</section>

	</c:param>

</c:import>