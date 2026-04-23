<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
            <div class="px-4 mt-3">

                <c:if test="${!empty sessionScope.registError}">
    				<div class="text-danger mb-3">${sessionScope.registError}</div>
    				<c:remove var="registError" scope="session" />
				</c:if>
				<c:if test="${!empty error}">
    				<div class="text-danger mb-3">${error}</div>
				</c:if>
                <%-- 検索フォーム --%>
                <form method="get" action="TestRegistExecute.action" class="row border mx-3 mb-3 py-2 align-items-center rounded">
                    <div class="col-3">
                        <label class="form-label">入学年度</label>
                        <select name="entYear" class="form-select">
                            <option value="0">----------</option>
                            <c:forEach var="year" items="${entYearSet}">
                                <option value="${year}" <c:if test="${year==entYear}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2">
                        <label class="form-label">クラス</label>
                        <select name="classNum" class="form-select">
                            <option value="0">----------</option>
                            <c:forEach var="num" items="${classNumSet}">
                                <option value="${num}" <c:if test="${num==classNum}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select name="subjectCd" class="form-select">
                            <option value="0">----------</option>
                            <c:forEach var="subject" items="${subjectSet}">
                                <option value="${subject.cd}" <c:if test="${subject.cd==subjectCd}">selected</c:if>>${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2">
                        <label class="form-label">回数</label>
                        <select name="no" class="form-select">
                            <option value="0">----------</option>
                            <c:forEach var="n" items="${noSet}">
                                <option value="${n}" <c:if test="${n==no}">selected</c:if>>${n}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2 mt-4">
                        <button type="submit" class="btn btn-secondary">検索</button>
                    </div>
                </form>

                <%-- 検索結果 --%>
                <c:if test="${!empty tests}">
                    <div class="px-3 mb-2">科目：${subjectName}（${no}回）</div>
                    <form method="post" action="TestRegistExecute.action">
                        <input type="hidden" name="subjectCd" value="${subjectCd}" />
                        <input type="hidden" name="schoolCd" value="${schoolCd}" />
                        <input type="hidden" name="no" value="${no}" />
                        <input type="hidden" name="entYear" value="${entYear}" />
                        <input type="hidden" name="classNum" value="${classNum}" />
                        <table class="table table-hover mx-3">
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>点数</th>
                            </tr>
                            <c:forEach var="row" items="${tests}" varStatus="s">
                                <tr>
                                    <td>${row.entYear}</td>
                                    <td>${row.classNum}</td>
                                    <td>${row.studentNo}</td>
                                    <td>${row.studentName}</td>
                                    <td>
                                        <input type="hidden" name="studentNo_${s.index}" value="${row.studentNo}" />
                                        <input type="text" name="point_${s.index}" value="${row.point}"
                                               class="form-control" style="width:100px;" />
                                        <c:if test="${!empty row.pointError}">
                                            <div class="text-danger small">${row.pointError}</div>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <input type="hidden" name="count" value="${tests.size()}" />
                        <button type="submit" class="btn btn-secondary ms-3">登録して終了</button>
                    </form>
                </c:if>

            </div>
        </section>
    </c:param>
</c:import>