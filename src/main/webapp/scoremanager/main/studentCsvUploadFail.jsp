<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">CSV登録失敗</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                CSV登録に失敗しました
            </h2>

            <!-- エラーメッセージ -->
            <div class="px-4 mb-3">
                <p class="text-danger">
                    以下の理由により、CSV登録ができませんでした。
                </p>
                <ul class="text-danger">
                    <c:forEach var="e" items="${errors}">
                        <li>${e}</li>
                    </c:forEach>
                </ul>
            </div>

            <!-- 再操作 -->
            <div class="mt-4 px-4">
                <a href="StudentCsvUpload.action" class="me-4">
                    CSVファイルを再選択する
                </a>

                <a href="StudentList.action">
                    学生一覧へ戻る
                </a>
            </div>

        </section>
    </c:param>
</c:import>