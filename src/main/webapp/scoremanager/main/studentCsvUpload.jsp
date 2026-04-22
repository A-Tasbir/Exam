<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">学生CSV登録</c:param>

    <c:param name="content">
        <section class="me-4">

            <!-- タイトル -->
            <div class="d-flex justify-content-between align-items-center
                        bg-secondary bg-opacity-10 py-2 px-4 mb-3">
                <h2 class="h3 mb-0 fw-normal">学生CSV登録</h2>
                <a href="StudentRegist.action">戻る</a>
            </div>

            <!-- エラーメッセージ -->
            <c:if test="${not empty errors}">
                <div class="px-4 mb-3">
                    <ul class="text-danger">
                        <c:forEach var="e" items="${errors}">
                            <li>${e}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <!-- ★★★★★ 重要：multipart/form-data + POST ★★★★★ -->
            <form action="StudentCsvUploadExecute.action"
                  method="post"
                  enctype="multipart/form-data"
                  class="px-4">

                <div class="mb-3">
                    <label class="form-label">
                        CSVファイル <span class="text-danger">*</span>
                    </label>
                    <input type="file"
                           name="csvFile"
                           accept=".csv"
                           class="form-control"
                           required />
                </div>

                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">
                        取り込み
                    </button>
                </div>

            </form>

        </section>
    </c:param>
</c:import>