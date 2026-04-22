<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>

	<c:param name="content">

		<section class="me-4">

			<!-- HEADER -->
			<div class="d-flex justify-content-between align-items-center bg-secondary bg-opacity-10 py-2 px-4 mb-3">
				<h2 class="h3 mb-0 fw-normal">先生登録</h2>
				<a href="TeacherList.action">戻る</a>
			</div>

			<!-- ERROR (student style) -->
			<c:if test="${errors.size() > 0}">
				<div class="px-4 mb-2">
					<ul class="text-danger">
						<c:forEach var="error" items="${errors}">
							<li>${error}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>

			<form action="TeacherRegistExecute.action" method="post" class="px-4">

				<div class="mb-3">
					<label class="form-label">ID <span class="text-danger">*</span></label>
					<input type="text" class="form-control w-100" name="id"
						value="${id}" maxlength="10" required />
				</div>

				<div class="mb-3">
					<label class="form-label">パスワード <span class="text-danger">*</span></label>
					<input type="password" class="form-control w-100" name="password"
						value="${password}" maxlength="30" required />
				</div>

				<div class="mb-3">
					<label class="form-label">氏名 <span class="text-danger">*</span></label>
					<input type="text" class="form-control w-100" name="name"
						value="${name}" maxlength="30" required />
				</div>

				<!-- SCHOOL (LOCKED like student system rule) -->
				<div class="mb-3">
					<label class="form-label">学校</label>
					<p class="form-control-plaintext">${user.school.cd}</p>
				</div>

				<div class="form-check mb-3">
					<input class="form-check-input" type="checkbox" name="admin">
					<label class="form-check-label">管理者</label>
				</div>

				<div class="mt-4">
					<input type="submit" class="btn btn-primary" value="登録" />
				</div>

			</form>

		</section>

	</c:param>
</c:import>