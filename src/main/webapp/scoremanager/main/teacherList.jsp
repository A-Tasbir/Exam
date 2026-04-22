<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">先生管理</c:param>

<c:param name="content">

<section style="width:100%; max-width:100%;">

<div class="d-flex justify-content-between align-items-center bg-secondary bg-opacity-10 py-2 px-4 mb-3 w-100">
	<h2 class="h3 mb-0 fw-normal">先生管理</h2>
</div>

<!-- SUCCESS MESSAGE -->
<c:if test="${not empty sessionScope.message}">
	<div class="alert alert-success mx-4">
		${sessionScope.message}
	</div>
	<c:remove var="message" scope="session"/>
</c:if>

<div class="px-4 mb-2 d-flex justify-content-end">
	<a href="TeacherRegist.action">新規登録</a>
</div>

<div class="px-4 w-100">

<table class="table table-bordered table-hover w-100">

<thead class="table-secondary">
<tr>
	<th>ID</th>
	<th>名前</th>
	<th>学校</th>
	<th></th>
</tr>
</thead>

<tbody>

<c:choose>
	<c:when test="${not empty list}">
		<c:forEach var="t" items="${list}">

			<tr class="${t.admin ? 'table-warning' : ''}">

				<td>${t.id}</td>

				<td>
					<c:choose>
						<c:when test="${t.admin}">
							<span class="text-danger fw-bold">
								${t.name} (管理者)
							</span>
						</c:when>
						<c:otherwise>
							${t.name}
						</c:otherwise>
					</c:choose>
				</td>

				<td>${t.school.cd}</td>

				<td>
					<a href="TeacherEdit.action?id=${t.id}">変更</a>
				</td>

			</tr>

		</c:forEach>
	</c:when>

	<c:otherwise>
		<tr>
			<td colspan="4" class="text-center">
				先生情報が存在しませんでした
			</td>
		</tr>
	</c:otherwise>
</c:choose>

</tbody>

</table>

</div>

</section>

</c:param>
</c:import>