<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">

<section style="width:100%; max-width:100%;">

    <!-- HEADER -->
    <div class="d-flex justify-content-between align-items-center bg-secondary bg-opacity-10 py-2 px-4 mb-3 w-100 ">
        <h2 class="h3 mb-0 fw-normal">学生管理</h2>
    </div>

    <!-- NEW -->
    <div class="px-4 mb-2 d-flex justify-content-end">
        <a href="StudentCreate.action">新規登録</a>
    </div>

    <!-- FILTER -->
    <form method="get" action="StudentList.action"
        class="px-4 mb-3 w-100 pb-4 pt-2"
        style="border: 1px solid #000; padding:12px;">

        <div class="d-flex align-items-end justify-content-between flex-wrap w-100">

            <!-- 入学年度 -->
            <div>
                <label>入学年度</label>

                <select name="f1" class="form-select" style="width:250px;">
                    <option value="">----</option>

                    <c:forEach var="i" begin="0" end="10">
                        <c:set var="year" value="${currentYear - i}" />

                        <option value="${year}"
                            ${f1 eq year.toString() ? 'selected' : ''}>
                            ${year}
                        </option>
                    </c:forEach>

                </select>
            </div>

            <!-- クラス -->
            <div>
                <label>クラス</label>

                <select name="f2" class="form-select" style="width:250px;">
                    <option value="">----</option>

                    <c:forEach var="num" items="${class_num_set}">
                        <option value="${num}"
                            ${f2 eq num ? 'selected' : ''}>
                            ${num}
                        </option>
                    </c:forEach>

                </select>
            </div>

            <!-- 在学中 -->
            <div>
                <label>在学中</label><br>

                <input type="checkbox" name="f3" value="t"
                    ${not empty f3 ? 'checked' : ''} />
            </div>

            <!-- BUTTON -->
            <div>
                <button class="btn btn-secondary">絞り込み</button>
            </div>

        </div>
    </form>

    <!-- ERROR MESSAGE -->
    <c:if test="${not empty errors}">
        <div class="px-4 mb-2 text-dark">
            <c:forEach var="e" items="${errors}">
                <div>${e}</div>
            </c:forEach>
        </div>
    </c:if>

    <!-- NO RESULT MESSAGE -->
    <c:if test="${empty errors and empty students}">
        <div class="px-4 mb-2 text-dark">
            学生情報が存在しませんでした
        </div>
    </c:if>

    <!-- RESULTS + TABLE -->
    <c:if test="${empty errors and not empty students}">

        <!-- RESULTS -->
        <div class="px-4 mb-2">
            検索結果：${students.size()}件
        </div>

        <!-- TABLE -->
        <div class="px-4 w-100">
            <table class="table  table-hover w-100">

                <thead class="table-secondary">
                    <tr>
                        <th>入学年度</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>クラス</th>
                        <th>在学中</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="s" items="${students}">
                        <tr>
                            <td>${s.entYear}</td>
                            <td>${s.no}</td>
                            <td>${s.name}</td>
                            <td>${s.classNum}</td>

                            <td>
                                <c:choose>
                                    <c:when test="${s.attend}">○</c:when>
                                    <c:otherwise>×</c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <a href="StudentUpdate.action?no=${s.no}">
                                    変更
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>

            </table>
        </div>

    </c:if>

</section>

    </c:param>
</c:import>