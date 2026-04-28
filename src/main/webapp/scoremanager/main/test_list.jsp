<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">

<section style="width:100%; max-width:100%;">

    <!-- HEADER -->
    <div class="d-flex justify-content-between align-items-center bg-secondary bg-opacity-10 py-2 px-4 mb-3 w-100">
        <h2 class="h3 mb-0 fw-normal">成績検索</h2>
    </div>

    <!-- ===================== SEARCH AREA ===================== -->
    <div class="px-4 mb-3 w-100 pb-3 pt-3" style="border:1px solid #000;">
	
        <!-- ===== TOP SEARCH（科目別）===== -->
        <form method="get" action="TestListSubjectExecute.action">
            <div class="d-flex align-items-center gap-4 mb-3">

                <div class="fw-bold" style="min-width:90px;">
                    科目情報
                </div>

                <div class="d-flex align-items-end gap-3 flex-wrap w-100">

                    <div>
                        <label class="form-label mb-1">入学年度</label>
                        <select name="admissionYear"
                                class="form-select"
                                style="width:140px; height:38px;">
                            <option value="">----------</option>
                            <c:forEach var="y" items="${entYearList}">
            					<option value="${y}">${y}</option>
        					</c:forEach>
                        </select>
                    </div>

                    <div>
                        <label class="form-label mb-1">クラス</label>
                        <select name="classNo"
                                class="form-select"
                                style="width:140px; height:38px;">
                            <option value="">----------</option>
                            <c:forEach var="c" items="${classList}">
					            <option value="${c}">${c}</option>
					        </c:forEach>
                        </select>
                    </div>

                    <div>
                        <label class="form-label mb-1">科目</label>
                        <select name="subjectCode"
                                class="form-select"
                                style="width:300px; height:38px;">
                            <option value="">----------</option>
                             <c:forEach var="sub" items="${subjectList}">
						        <option value="${sub.cd}">
						            ${sub.name}
						        </option>
						    </c:forEach>
                        </select>
                    </div>

                    <div class="ms-auto">
                        <button type="submit"
                                class="btn btn-secondary"
                                style="width:90px; height:38px;">
                            検索
                        </button>
                    </div>

                </div>
            </div>
        </form>

        <hr style="border-top:1px solid #000; opacity:1; margin:16px 0;">

        <!-- ===== BOTTOM SEARCH（学生別）===== -->
        <form method="get" action="TestListStudentExecute.action">
            <div class="d-flex align-items-center gap-4">

                <div class="fw-bold" style="min-width:90px;">
                    学生情報
                </div>

                <div>
                    <label class="form-label mb-1">学籍番号</label>
                    <input type="text"
                           name="studentNo"
                           class="form-control"
                           style="width:220px; height:38px;">
                </div>

                <div class="pt-4">
                    <button type="submit"
                            class="btn btn-secondary"
                            style="width:90px; height:38px;">
                        検索
                    </button>
                </div>

            </div>
        </form>

    </div>

    <!-- BACK -->
    <div class="px-4 mt-3">
        <a href="Menu.action">メニューへ戻る</a>
    </div>

</section>

    </c:param>
</c:import>