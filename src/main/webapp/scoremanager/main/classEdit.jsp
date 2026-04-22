<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
<c:param name="title">クラス変更</c:param>

<c:param name="content">

<section class="me-4">

    <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
        クラス変更
    </h2>

    <div class="px-4">

        <c:if test="${not empty errors}">
            <div class="alert alert-danger">
                <c:forEach var="e" items="${errors}">
                    <div>${e}</div>
                </c:forEach>
            </div>
        </c:if>

        <form method="post" action="ClassEditExecute.action">

            <input type="hidden" name="oldClassNum" value="${classNum}">

            <div class="mb-3">
                <label>クラス番号</label>
                <input type="text" name="classNum" class="form-control"
                       value="${classNum}">
            </div>

            <button class="btn btn-secondary">変更</button>

        </form>

    </div>

</section>

</c:param>
</c:import>