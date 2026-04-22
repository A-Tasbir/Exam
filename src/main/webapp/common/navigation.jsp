<%-- サイドバー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<ul class="nav nav-pills flex-column mb-auto px-4">

	<li class="nav-item my-3">
		<a href="Menu.action">メニュー</a>
	</li>

	<li class="nav-item mb-3">
		<a href="StudentList.action">学生管理</a>
	</li>

	<!-- 管理者のみ -->
	<c:if test="${user.admin}">
		<li class="nav-item mb-3">
			<a href="TeacherList.action">先生管理</a>
		</li>
	</c:if>

	<li class="nav-item">成績管理</li>

	<li class="nav-item mx-3 mb-3">
		<a href="TestRegist.action">成績登録</a>
	</li>

	<li class="nav-item mx-3 mb-3">
		<a href="TestList.action">成績参照</a>
	</li>

	<!-- ✅ MOVED UP: クラス管理 -->
	<li class="nav-item mb-3">
		<a href="ClassList.action">クラス管理</a>
	</li>

	<!-- 科目管理 (moved down) -->
	<li class="nav-item mb-3">
		<a href="SubjectList.action">科目管理</a>
	</li>

</ul>