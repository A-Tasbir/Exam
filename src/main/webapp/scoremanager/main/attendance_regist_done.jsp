<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">出欠席登録</h2>
            <div class="px-4 mt-3">
                <div class="alert alert-success text-center">登録が完了しました。</div>
                <div class="mt-3">
                    <a href="AttendanceRegist.action" class="me-3">出欠登録</a>
                    <a href="AttendanceList.action">出欠席一覧</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>