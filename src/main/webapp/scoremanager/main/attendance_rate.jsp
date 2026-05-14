<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">出欠率</h2>
            <div class="px-4 mt-3">

                <form method="get" action="AttendanceRate.action"
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
                    <div class="col-2 mt-4">
                        <button type="submit" class="btn btn-secondary">検索</button>
                    </div>
                </form>

                <c:if test="${!empty rows}">
                    <table class="table table-hover w-100">
                        <tr>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th class="text-center">出席</th>
                            <th class="text-center">欠席</th>
                            <th class="text-center">遅刻</th>
                            <th class="text-center">合計</th>
                            <th class="text-center">出席率</th>
                        </tr>
                        <c:forEach var="row" items="${rows}">
                            <tr>
                                <td>${row.studentNo}</td>
                                <td>${row.studentName}</td>
                                <td class="text-center">${row.present}</td>
                                <td class="text-center">${row.absent}</td>
                                <td class="text-center">${row.late}</td>
                                <td class="text-center">${row.total}</td>
                                <td class="text-center">${row.rate}%</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </section>
    </c:param>
</c:import>