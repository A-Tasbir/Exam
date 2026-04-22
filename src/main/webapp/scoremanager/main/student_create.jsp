<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>

	<c:param name="content">
		<section class="me-4">
			<div class="d-flex justify-content-between align-items-center bg-secondary bg-opacity-10 py-2 px-4 mb-3">
				<h2 class="h3 mb-0 fw-normal">学生登録</h2>
			
			</div>
			<div class="px-4 mb-3 text-end">
    			<a href="StudentCsvUpload.action">CSVファイルを取り込む</a>
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

			<form action="StudentCreateExecute.action" method="post" class="px-4">
				<div class="mb-3">
					<label class="form-label">入学年度 <span class="text-danger">*</span></label>
					<select class="form-select w-100" name="entYear">
						<option value="">選択してください</option>
						<c:forEach begin="${currentYear - 10}" end="${currentYear + 10}" var="year">
    						<option value="${year}"
        					<c:if test="${entYear == year}">selected</c:if>>
       						 ${year}
    						</option>
						</c:forEach>
					</select>
				</div>
				<div class="mb-3">
					<label class="form-label">学生番号 <span class="text-danger">*</span></label>
					<input type="text" class="form-control w-100" name="no"
						value="${no}" maxlength="10" required />
				</div>
				<div class="mb-3">
					<label class="form-label">氏名 <span class="text-danger">*</span></label>
					<input type="text" class="form-control w-100" name="name"
						value="${name}" maxlength="10" required />
				</div>
				
				<div class="mb-3">
    				<label class="form-label">クラス番号</label>
    				<select name="classNum" class="form-select w-100">
        				<option value="">選択してください</option>
        				<c:forEach var="c" items="${class_num_set}">
            			<option value="${c}"
                		<c:if test="${classNum == c}">selected</c:if>>
                		${c}
            </option>
        </c:forEach>
    </select>
</div>

				
				<div class="mt-4">
					<input type="submit" class="btn btn-secondary" value="登録して終了" />
					
				</div>
				<div class="mt-4">
    				<a href="StudentList.action">戻る</a>
				</div>
			</form>
		</section>
	</c:param>
</c:import>
