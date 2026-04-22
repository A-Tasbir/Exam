<%-- 学生変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>

	<c:param name="content">
		<section class="me-4">
			<div class="d-flex justify-content-between align-items-center bg-secondary bg-opacity-10 py-2 px-4 mb-3">
				<h2 class="h3 mb-0 fw-normal">学生変更</h2>
				<a href="StudentList.action">戻る</a>
			</div>

			<%-- エラーメッセージ --%>
			<c:if test="${errors.size() > 0}">
				<div class="px-4 mb-2">
					<ul class="text-danger">
						<c:forEach var="error" items="${errors}">
							<li>${error}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>

			<form action="StudentUpdateExecute.action" method="post" class="px-4">
				<%-- 学生番号は変更不可 --%>
				<input type="hidden" name="no" value="${student.no}" />

				<div class="mb-3">
					<label class="form-label">学生番号</label>
					<p class="form-control-plaintext">${student.no}</p>
				</div>
				<div class="mb-3">
					<label class="form-label">入学年度</label>
					<p class="form-control-plaintext">${student.entYear}</p>
				</div>
				<div class="mb-3">
					<label class="form-label">氏名 <span class="text-danger">*</span></label>
					<input type="text" class="form-control w-100" name="name"
						value="${student.name}" maxlength="10" required />
				</div>
				<<div class="mb-3">
   					 <label class="form-label">クラス番号</label>
   					 <select name="classNum" class="form-select w-100">
        				<option value="">選択してください</option>
        				<c:forEach var="c" items="${class_num_set}">
            			<option value="${c}"
                		<c:if test="${student.classNum == c}">selected</c:if>>
               			 ${c}
            </option>
        </c:forEach>
    </select>
</div>
				<div class="mb-3 d-flex align-items-center">
				<label for="isAttend" class="form-label me-2 mb-0">
					在学中
				</label>

				<input class="form-check-input" type="checkbox" name="isAttend" value="true" id="isAttend"
		   			<c:if test="${student.attend}">checked</c:if> />
				</div>
				<div class="mt-4">
					<input type="submit" class="btn btn-primary" value="変更" />
				</div>
			</form>
		</section>
	</c:param>
</c:import>
