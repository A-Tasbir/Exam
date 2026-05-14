<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		科目管理
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				科目管理
			</h2>

			<c:if test="${not empty error}">

				<div class="alert alert-warning">

					${error}

				</div>

			</c:if>

			<div class="mb-3 text-end">

				<a href="SubjectCreate.action"
					class="btn btn-primary">

					新規登録

				</a>

			</div>

			<table class="table table-hover">

				<thead>

					<tr>

						<th>科目コード</th>

						<th>科目名</th>

						<th></th>

					</tr>

				</thead>

				<tbody>

					<c:forEach var="subject" items="${list}">

						<tr>

							<td>${subject.cd}</td>

							<td>${subject.name}</td>

							<td>

								<a href="SubjectEdit.action?cd=${subject.cd}">
									変更
								</a>

								&nbsp;

								<a href="SubjectDelete.action?cd=${subject.cd}"
									onclick="return confirm('本当に削除しますか？');">

									削除

								</a>

							</td>

						</tr>

					</c:forEach>

				</tbody>

			</table>

		</section>

	</c:param>

</c:import>