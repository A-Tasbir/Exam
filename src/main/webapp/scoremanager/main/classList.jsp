<%-- クラス一覧JSP（学生付き） --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
<c:param name="title">クラス管理</c:param>

<c:param name="content">

<section class="me-4">

    <!-- HEADER -->
    <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
        クラス一覧
    </h2>

    <!-- ✅ NEW: REGISTRATION LINK -->
    <div class="px-4 mb-2 d-flex justify-content-end">
        <a href="ClassRegist.action">新規登録</a>
    </div>

    <!-- CONTENT -->
    <div class="px-4">

        <c:forEach var="c" items="${classList}">

            <!-- CLASS HEADER -->
            <div class="mb-3 p-2 bg-light border">
                <strong>クラス: ${c.classNum}</strong>
                <a href="ClassEdit.action?classNum=${c.classNum}">変更</a>
            </div>

            <!-- STUDENT TABLE -->
            <table class="table table-bordered mb-4">

                <thead class="table-secondary">
                    <tr>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>入学年度</th>
                        <th>在学</th>
                    </tr>
                </thead>

                <tbody>

                    <c:choose>

                        <c:when test="${not empty c.students}">

                            <c:forEach var="s" items="${c.students}">
                                <tr>
                                    <td>${s.no}</td>
                                    <td>${s.name}</td>
                                    <td>${s.entYear}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${s.attend}">○</c:when>
                                            <c:otherwise>×</c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>

                        </c:when>

                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="text-center text-muted">
                                    学生はいません
                                </td>
                            </tr>
                        </c:otherwise>

                    </c:choose>

                </tbody>

            </table>

        </c:forEach>

    </div>

</section>

</c:param>
</c:import>