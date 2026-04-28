    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">

<section style="width:100%; max-width:100%;">

    <!-- HEADER -->
    <div class="d-flex justify-content-between align-items-center bg-secondary bg-opacity-10 py-2 px-4 mb-3 w-100">
        <h2 class="h3 mb-0 fw-normal">成績一覧（学生）</h2>
    </div>

    <!-- SEARCH AREA -->
    <div class="px-4 mb-3 w-100 pb-3 pt-3" style="border:1px solid #000;">

        <form method="get" action="TestListSubjectExecute.action">
            <div class="d-flex align-items-center gap-4 mb-3">

                <div class="fw-bold" style="min-width:90px;">科目情報</div>

                <div class="d-flex align-items-end gap-3 flex-wrap w-100">

                    <div>
                        <label class="form-label mb-1">入学年度</label>
                        <select name="admissionYear" class="form-select" style="width:140px; height:38px;">
                            <option value="">----------</option>
                        </select>
                    </div>

                    <div>
                        <label class="form-label mb-1">クラス</label>
                        <select name="classNo" class="form-select" style="width:140px; height:38px;">
                            <option value="">----------</option>
                        </select>
                    </div>

                    <div>
                        <label class="form-label mb-1">科目</label>
                        <select name="subjectCode" class="form-select" style="width:300px; height:38px;">
                            <option value="">----------</option>
                        </select>
                    </div>

                    <div class="ms-auto">
                        <button type="submit" class="btn btn-secondary" style="width:90px; height:38px;">
                            検索
                        </button>
                    </div>

                 </div>
            </div>
        </form>

        <hr style="border-top:1px solid #000; opacity:1; margin:16px 0;">

        <form method="get" action="TestListStudentExecute.action">
            <div class="d-flex align-items-center gap-4">

                <div class="fw-bold" style="min-width:90px;">学生情報</div>

                <div>
                    <label class="form-label mb-1">学籍番号</label>
                    <input type="text" name="studentNo" value="${studentNo}"
                           class="form-control" style="width:220px; height:38px;">
                </div>

                <div class="pt-4">
                    <button type="submit" class="btn btn-secondary" style="width:90px; height:38px;">
                        検索
                    </button>
                </div>

            </div>
        </form>
    </div>

    <!-- NAME -->
    <c:if test="${not empty studentName}">
        <div class="px-4 mb-2">
            氏名：${studentName}（${studentNo}）
        </div>
    </c:if>

    <!-- ERROR MESSAGE -->
    <c:if test="${not empty errorMessage}">
        <div class="px-4 mb-3 text-dark">
            <div>${errorMessage}</div>
        </div>
    </c:if>

    <!-- RESULT TABLE (UPDATED) -->
    <c:if test="${not empty testList}">
        <div class="px-4 w-100">
            <table class="table table-bordered table-hover w-100">
                <thead class="table-secondary">
                    <tr>
                        <th style="width:40%;">科目名</th>
						<th style="width:20%;">科目コード</th>
						<th style="width:20%;">回数</th>
						<th style="width:20%;">点数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="t" items="${testList}">
                        <tr>
                            <td>${t.subjectName}</td>
                            <td>${t.subjectCd}</td>
                            <td>${t.no}</td>
                            <td>${t.point}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <!-- BACK -->
    <div class="px-4 mt-3">
        <a href="Menu.action">メニューへ戻る</a>
    </div>

</section>

    </c:param>
</c:import>