<%-- メニューJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
				メニュー
			</h2>

			<div class="row text-center px-4 fs-3 my-5">

				<!-- 学生管理 -->
				<div class="menu-card student-card col d-flex align-items-center justify-content-center mx-2 rounded shadow">
					<a href="StudentList.action">学生管理</a>
				</div>

				<!-- 成績管理 -->
				<div class="menu-card score-card col d-flex align-items-center justify-content-center mx-2 rounded shadow">

					<div>

						<div>成績管理</div>

						<div>
							<a href="TestRegist.action">
								成績登録
							</a>
						</div>

						<div>
							<a href="TestList.action">
								成績参照
							</a>
						</div>

					</div>

				</div>

				<!-- クラス管理 -->
				<div class="menu-card class-card col d-flex align-items-center justify-content-center mx-2 rounded shadow">

					<a href="ClassList.action">
						クラス管理
					</a>

				</div>

				<!-- 科目管理 -->
				<div class="menu-card subject-card col d-flex align-items-center justify-content-center mx-2 rounded shadow">

					<a href="SubjectList.action">
						科目管理
					</a>

				</div>

				<!-- 先生管理（管理者のみ） -->
				<c:if test="${user.admin}">

					<div class="menu-card teacher-card col d-flex align-items-center justify-content-center mx-2 rounded shadow">

						<a href="TeacherList.action">
							先生管理
						</a>

					</div>

				</c:if>

			</div>

			<!-- 出欠管理 -->
			<div class="row g-4 mb-4 px-4">

				<div class="menu-card attendance-card col-md-3 d-flex align-items-center justify-content-center mx-2 rounded shadow">

					<div class="fs-3">
				
						<div>出欠管理</div>
				
						<div>
							<a href="AttendanceRegist.action">
								出欠登録
							</a>
						</div>
				
						<div>
							<a href="AttendanceList.action">
								出欠一覧
							</a>
						</div>
				
						<div>
							<a href="AttendanceRate.action">
								出欠率
							</a>
						</div>
				
					</div>
				
				</div>

			</div>

		</section>

	</c:param>

</c:import>