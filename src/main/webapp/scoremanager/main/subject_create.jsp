<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		科目登録
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<!-- =========================
			     タイトル
			========================= -->
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				科目登録
			</h2>

			<!-- =========================
			     登録フォーム
			========================= -->
			<form action="SubjectCreateExecute.action"
				  method="post">

				<!-- 科目コード -->
				<div class="mb-3">

					<label class="form-label">
						科目コード
					</label>

					<input type="text"
						   name="cd"
						   value="${cd}"
						   maxlength="3"
						   required
						   autocomplete="off"
						   class="form-control">

					<!-- エラーメッセージ -->
					<c:if test="${not empty errorCd}">

						<div class="text-danger">

							${errorCd}

						</div>

					</c:if>

				</div>

				<!-- 科目名 -->
				<div class="mb-3">

					<label class="form-label">
						科目名
					</label>

					<input type="text"
						   name="name"
						   value="${name}"
						   maxlength="20"
						   required
						   autocomplete="off"
						   class="form-control">

					<!-- エラーメッセージ -->
					<c:if test="${not empty errorName}">

						<div class="text-danger">

							${errorName}

						</div>

					</c:if>

				</div>

				<!-- 登録ボタン -->
				<button type="submit"
						class="btn btn-primary">

					登録

				</button>

			</form>

		</section>

	</c:param>

</c:import>