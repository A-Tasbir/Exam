<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts">
    	<style>
@media print {
    header, nav, footer, .row.border, button, .btn { 
        display: none !important; 
    }
    header.d-flex {
        display: none !important;
    }
    /* header ထဲက div တွေ hide */
    header > div {
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
    .col-3 {
        display: none !important;
    }
    .row {
        display: block !important;
    }
    #wrapper {
        padding: 0 !important;
    }
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
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">出欠一覧</h2>
            <div class="px-4 mt-3">

                <form method="get" action="AttendanceList.action"
                      class="row border mx-3 mb-3 py-2 align-items-center rounded">
                    <div class="col-3">
                        <label class="form-label">入学年度</label>
                        <select name="entYear" class="form-select">
                            <option value="0">----------</option>
                            <c:forEach var="year" items="${entYearSet}">
                                <option value="${year}" <c:if test="${year==entYear}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-3">
                        <label class="form-label">クラス</label>
                        <select name="classNum" class="form-select">
                            <option value="0">----------</option>
                            <c:forEach var="num" items="${classNumSet}">
                                <option value="${num}" <c:if test="${num==classNum}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-3">
                        <label class="form-label">日付</label>
                        <input type="date" name="date" class="form-control" value="${date}" />
                    </div>
                    <div class="col-2 mt-4">
                        <button type="submit" class="btn btn-secondary">検索</button>
                    </div>
                </form>
                <c:if test="${!empty error}">
    				<div class="text-danger px-3 mb-3">${error}</div>
				</c:if>

                <c:if test="${!empty rows}">
                    <table class="table table-hover w-100">
                        <tr>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th class="text-center">出欠</th>
                        </tr>
                        <c:forEach var="row" items="${rows}">
                            <tr>
                                <td>${row.studentNo}</td>
                                <td>${row.studentName}</td>
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${row.status=='O'}">○ 出席</c:when>
                                        <c:when test="${row.status=='X'}">× 欠席</c:when>
                                        <c:when test="${row.status=='T'}">△ 遅刻</c:when>
                                        <c:when test="${row.status=='S'}">▽ 早退</c:when>
                                        <c:otherwise>− 未登録</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="mt-3 ms-3">
    					<button type="button" class="btn btn-outline-secondary" onclick="window.print()">印刷</button>
					</div>
                </c:if>
            </div>
        </section>
    </c:param>
</c:import>