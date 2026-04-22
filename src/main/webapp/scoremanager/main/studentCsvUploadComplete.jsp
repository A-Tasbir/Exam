<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">学生CSV登録完了</c:param>

    <c:param name="content">
        <section class="me-4">

            <!-- タイトル -->
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                学生CSV登録完了
            </h2>

            <!-- 完了メッセージ -->
            <div class="px-4">
                <p class="text-center py-2"
                   style="background-color: #66CC99;">
                    CSVファイルから学生情報を登録しました
                </p>
            </div>

            <!-- リンク -->
            <div class="mt-4 px-4">
                <a href="StudentCsvUpload.action" class="me-4">
                    もう一度CSVを取り込む
                </a>

                <a href="StudentList.action">
                    学生一覧へ戻る
                </a>
            </div>

        </section>
    </c:param>
</c:import>