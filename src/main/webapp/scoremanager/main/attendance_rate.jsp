<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts">

		<style>

			/* =========================
			   出席率アラート色
			========================= */

			.alert-red {
				background-color: #c0392b !important;
				color: #fff !important;
			}

			.alert-yellow {
				background-color: #b7950b !important;
				color: #fff !important;
			}

			/* =========================
			   印刷設定
			========================= */

			@media print {

				header,
				nav,
				footer,
				.row.border,
				button,
				.btn {
					display: none !important;
				}

				header.d-flex,
				header > div,
				.col-3 {
					display: none !important;
				}

				main {
					width: 100% !important;
					min-height: auto !important;
					padding: 0 !important;
				}

				.col-9 {
					width: 100% !important;
					border: none !important;
					flex: 0 0 100% !important;
					max-width: 100% !important;
				}

				.row {
					display: block !important;
				}

				#wrapper,
				.container {
					padding: 0 !important;
				}

				input[type="text"] {
					border: none !important;
					box-shadow: none !important;
				}
			}

		</style>

	</c:param>

	<c:param name="content">

		<section class="me-4">

			<!-- タイトル -->
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				出欠率
			</h2>

			<div class="px-4 mt-3">

				<!-- =========================
				     検索フォーム
				========================= -->
				<form method="get"
					  action="AttendanceRate.action"
					  class="row border mx-3 mb-3 py-2 align-items-center rounded">

					<!-- 入学年度 -->
					<div class="col-2">

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
					<div class="col-2">

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

					<!-- 出欠年 -->
					<div class="col-2">

						<label class="form-label">
							出欠年
						</label>

						<select name="attendYear" class="form-select">

							<option value="0">
								全て
							</option>

							<c:forEach var="year" items="${entYearSet}">

								<option value="${year}"
									<c:if test="${year == attendYear}">
										selected
									</c:if>>
									${year}
								</option>

							</c:forEach>

						</select>

					</div>

					<!-- 月 -->
					<div class="col-2">

						<label class="form-label">
							月
						</label>

						<select name="month" class="form-select">

							<option value="0">
								全て
							</option>

							<c:forEach var="m" items="${monthSet}">

								<option value="${m}"
									<c:if test="${m == month}">
										selected
									</c:if>>
									${m}月
								</option>

							</c:forEach>

						</select>

					</div>

					<!-- 検索ボタン -->
					<div class="col-2 mt-4">

						<button type="submit"
								class="btn btn-secondary">

							検索

						</button>

					</div>

				</form>

				<!-- エラーメッセージ -->
				<c:if test="${!empty error}">

					<div class="text-danger px-3 mb-3">
						${error}
					</div>

				</c:if>
				<!-- データなし -->
				<c:if test="${searched and empty rows}">
    				<div class="alert alert-danger ms-3">
    					該当するデータがありません。
					</div>
				</c:if>

				<!-- =========================
				     一覧表示
				========================= -->
				<c:if test="${!empty rows}">

					<!-- アラート凡例 -->
					<div class="mb-2 ms-3 d-flex gap-3 small">

						<span>
							<span style="
								background:#c0392b;
								padding:2px 8px;
								border-radius:3px;
								color:#fff;">
								■
							</span>

							出席率80%以下（要注意）
						</span>

						<span>
							<span style="
								background:#b7950b;
								padding:2px 8px;
								border-radius:3px;
								color:#fff;">
								■
							</span>

							出席率90%以下（注意）
						</span>

					</div>

					<!-- 出欠率一覧 -->
					<table class="table table-hover w-100">

						<tr>
							<th>学生番号</th>
							<th>氏名</th>
							<th class="text-center">出席</th>
							<th class="text-center">欠席</th>
							<th class="text-center">遅刻</th>
							<th class="text-center">早退</th>
							<th class="text-center">合計</th>
							<th class="text-center">出席率</th>
						</tr>

						<c:forEach var="row" items="${rows}">

							<c:choose>

								<c:when test="${row.rate <= 80}">
									<tr class="alert-red">
								</c:when>

								<c:when test="${row.rate <= 90}">
									<tr class="alert-yellow">
								</c:when>

								<c:otherwise>
									<tr>
								</c:otherwise>

							</c:choose>

								<td>${row.studentNo}</td>
								<td>${row.studentName}</td>

								<td class="text-center">
									${row.present}
								</td>

								<td class="text-center">
									${row.absent}
								</td>

								<td class="text-center">
									${row.late}
								</td>

								<td class="text-center">
									${row.early}
								</td>

								<td class="text-center">
									${row.total}
								</td>

								<td class="text-center fw-bold">
									${row.rateStr}%
								</td>

							</tr>

						</c:forEach>

					</table>

					<!-- 印刷ボタン -->
					<div class="mt-3 ms-3">

						<button type="button"
								class="btn btn-outline-secondary"
								onclick="window.print()">

							印刷

						</button>

					</div>

				</c:if>

			</div>

		</section>

	</c:param>

</c:import>