<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		科目管理
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<!-- =========================
			     タイトル
			========================= -->
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				科目管理
			</h2>

			<!-- エラーメッセージ -->
			<c:if test="${not empty error}">

				<div class="alert alert-warning">

					${error}

				</div>

			</c:if>

			<!-- 新規登録 -->
			<div class="mb-3 text-end">

				<a href="SubjectCreate.action"
				   class="btn btn-primary">

					新規登録

				</a>

			</div>

			<!-- 科目なし -->
			<c:if test="${empty list}">

				<div class="alert alert-info">

					科目情報がありません。

				</div>

			</c:if>

			<!-- =========================
			     科目一覧
			========================= -->
			<c:if test="${not empty list}">

				<table class="table table-hover">

					<thead>

						<tr>

							<th>
								科目コード
							</th>

							<th>
								科目名
							</th>

							<th>
							</th>

						</tr>

					</thead>

					<tbody>

						<c:forEach var="subject"
								   items="${list}">

							<tr>

								<!-- 科目コード -->
								<td>

									${subject.cd}

								</td>

								<!-- 科目名 -->
								<td>

									${subject.name}

								</td>

								<!-- 操作 -->
								<td>

									<!-- 変更 -->
									<a href="SubjectEdit.action?cd=${subject.cd}"
									   class="btn btn-sm btn-outline-primary">

										変更

									</a>

									<!-- 削除 -->
									<form action="SubjectDeleteExecute.action"
										  method="post"
										  style="display: inline;">

										<input type="hidden"
											   name="cd"
											   value="${subject.cd}">

										<button type="submit"
												class="btn btn-sm btn-outline-danger"
												onclick="return confirm('本当に削除しますか？');">

											削除

										</button>

									</form>

								</td>

							</tr>

						</c:forEach>

					</tbody>

				</table>

			</c:if>

		</section>

	</c:param>

</c:import>