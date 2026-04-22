<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
<c:param name="title">クラス登録</c:param>

<c:param name="content">

<div class="px-4">

    <h2 class="mb-3">クラス登録</h2>

    <c:if test="${not empty errors}">
        <div class="alert alert-danger">
            <c:forEach var="e" items="${errors}">
                <div>${e}</div>
            </c:forEach>
        </div>
    </c:if>

    <form method="post" action="ClassRegistExecute.action">

        <div class="mb-3">
            <label>クラス番号</label>
            <input type="text" name="classNum" class="form-control">
        </div>

        <button class="btn btn-secondary">登録</button>

    </form>

</div>

</c:param>
</c:import>