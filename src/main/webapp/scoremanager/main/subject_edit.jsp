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

			<form action="SubjectEditExecute.action"
				method="post">

				<input type="hidden"
					name="oldCd"
					value="${subject.cd}">

				<div class="mb-3">

					<label class="form-label">
						科目コード
					</label>

					<input type="text"
						name="cd"
						value="${subject.cd}"
						maxlength="3"
						required
						autocomplete="off"
						class="form-control">

					<c:if test="${not empty errorCd}">

						<div class="text-danger">

							${errorCd}

						</div>

					</c:if>

				</div>

				<div class="mb-3">

					<label class="form-label">
						科目名
					</label>

					<input type="text"
						name="name"
						value="${subject.name}"
						maxlength="20"
						required
						autocomplete="off"
						class="form-control">

					<c:if test="${not empty errorName}">

						<div class="text-danger">

							${errorName}

						</div>

					</c:if>

				</div>

				<button type="submit"
					class="btn btn-primary">

					変更

				</button>

			</form>

		</section>

	</c:param>

</c:import>