<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts">
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<!-- タイトル -->
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				出欠登録
			</h2>

			<div class="px-4 mt-3">

				<!-- エラーメッセージ -->
				<c:if test="${!empty sessionScope.attendError}">

					<div class="text-danger mb-3">
						${sessionScope.attendError}
					</div>

					<c:remove var="attendError" scope="session" />

				</c:if>

				<!-- =========================
				     検索フォーム
				========================= -->
				<form method="get"
					  action="AttendanceRegistExecute.action"
					  class="row border mx-3 mb-3 py-2 align-items-center rounded">

					<!-- 入学年度 -->
					<div class="col-3">

						<label class="form-label">
							入学年度
						</label>

						<select name="entYear" class="form-select">

							<option value="0">
								----------
							</option>

							<c:forEach var="year" items="${entYearSet}">

								<option value="${year}"
									<c:if test="${year == entYear}">
										selected
									</c:if>>

									${year}

								</option>

							</c:forEach>

						</select>

					</div>

					<!-- クラス -->
					<div class="col-3">

						<label class="form-label">
							クラス
						</label>

						<select name="classNum" class="form-select">

							<option value="0">
								----------
							</option>

							<c:forEach var="num" items="${classNumSet}">

								<option value="${num}"
									<c:if test="${num == classNum}">
										selected
									</c:if>>

									${num}

								</option>

							</c:forEach>

						</select>

					</div>

					<!-- 日付 -->
					<div class="col-3">

						<label class="form-label">
							日付
						</label>

						<input type="date"
							   name="date"
							   class="form-control"
							   value="${!empty date ? date : today}" />

					</div>

					<!-- 検索ボタン -->
					<div class="col-2 mt-4">

						<button type="submit"
								class="btn btn-secondary">

							検索

						</button>

					</div>

				</form>

				<!-- =========================
				     出欠登録一覧
				========================= -->
				<c:if test="${!empty students}">

					<form method="post"
						  action="AttendanceRegistExecute.action">

						<!-- hidden -->
						<input type="hidden"
							   name="classNum"
							   value="${classNum}" />

						<input type="hidden"
							   name="date"
							   value="${date}" />

						<table class="table table-hover w-100">

							<tr>
								<th>学生番号</th>
								<th>氏名</th>
								<th class="text-center">出席(O)</th>
								<th class="text-center">欠席(X)</th>
								<th class="text-center">遅刻(T)</th>
								<th class="text-center">早退(S)</th>
							</tr>

							<c:forEach var="s"
									   items="${students}"
									   varStatus="st">

								<tr>

									<!-- 学生番号 -->
									<td>
										${s.no}
									</td>

									<!-- 氏名 -->
									<td>
										${s.name}
									</td>

									<!-- hidden -->
									<input type="hidden"
										   name="studentNo_${st.index}"
										   value="${s.no}" />

									<!-- 出席 -->
									<td class="text-center">

										<input type="radio"
											   name="status_${st.index}"
											   value="O"

											<c:forEach var="a" items="${existing}">
												<c:if test="${a.studentNo == s.no && a.status == 'O'}">
													checked
												</c:if>
											</c:forEach> />

									</td>

									<!-- 欠席 -->
									<td class="text-center">

										<input type="radio"
											   name="status_${st.index}"
											   value="X"

											<c:forEach var="a" items="${existing}">
												<c:if test="${a.studentNo == s.no && a.status == 'X'}">
													checked
												</c:if>
											</c:forEach> />

									</td>

									<!-- 遅刻 -->
									<td class="text-center">

										<input type="radio"
											   name="status_${st.index}"
											   value="T"

											<c:forEach var="a" items="${existing}">
												<c:if test="${a.studentNo == s.no && a.status == 'T'}">
													checked
												</c:if>
											</c:forEach> />

									</td>

									<!-- 早退 -->
									<td class="text-center">

										<input type="radio"
											   name="status_${st.index}"
											   value="S"

											<c:forEach var="a" items="${existing}">
												<c:if test="${a.studentNo == s.no && a.status == 'S'}">
													checked
												</c:if>
											</c:forEach> />

									</td>

								</tr>

							</c:forEach>

						</table>

						<!-- 件数 -->
						<input type="hidden"
							   name="count"
							   value="${students.size()}" />

						<!-- 登録ボタン -->
						<button type="submit"
								class="btn btn-secondary ms-3">

							登録して終了

						</button>

					</form>

				</c:if>

			</div>

		</section>

	</c:param>

</c:import>