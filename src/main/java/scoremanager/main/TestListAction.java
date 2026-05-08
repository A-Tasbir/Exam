package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.SubjectTestRow;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;


public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        // セッションから学校コードを取得（他の既存Actionに倣う）
        Teacher teacher = (Teacher) session.getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();
        // セレクトボックスの選択肢は常にセット（初期表示・検索後どちらでも必要）
        setSelectOptions(req, schoolCd);

        String f = req.getParameter("f");

        if ("sj".equals(f)) {
            // イベント31: 科目情報条件で検索
            searchBySubject(req, res, schoolCd);

        } else if ("st".equals(f)) {
            // イベント32: 学生番号で検索
            searchByStudent(req, res);

        } else {
            // 初期表示
            req.getRequestDispatcher(
                "/scoremanager/main/test_list.jsp"
            ).forward(req, res);
        }
    }

   
    private void searchBySubject(
            HttpServletRequest req,
            HttpServletResponse res,
            String schoolCd) throws Exception {

    	String entYearStr = req.getParameter("admissionYear");
    	String classNum   = req.getParameter("classNo");
    	String subjectCd  = req.getParameter("subjectCode");
    	
        // 選択値を保持（エラー時もセレクトボックスの状態を維持）
        req.setAttribute("selectedF1", entYearStr);
        req.setAttribute("selectedF2", classNum);
        req.setAttribute("selectedF3", subjectCd);

        // バリデーション: 3項目すべて選択されているか（設計書 GRMR002エラー）
        if (isBlank(entYearStr) || isBlank(classNum) || isBlank(subjectCd)) {
            req.setAttribute("errorMessage", "入学年度・クラス・科目をすべて選択してください。");
            req.getRequestDispatcher(
                "/scoremanager/main/test_list.jsp"
            ).forward(req, res);
            return;
        }

        int entYear;
        try {
            entYear = Integer.parseInt(entYearStr);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "入学年度の値が正しくありません。");
            req.getRequestDispatcher(
                "/scoremanager/main/test_list.jsp"
            ).forward(req, res);
            return;
        }

        // DB検索（既存の TestDao.getSubjectTestList を使用）
        TestDao testDao = new TestDao();
        List<SubjectTestRow> list =
        	    testDao.getSubjectTestList(
        	        schoolCd,
        	        entYearStr,
        	        classNum,
        	        subjectCd
        	    );

        // 該当なし（設計書 GRMR002エラー）
        if (list.isEmpty()) {
            req.setAttribute("errorMessage", "検索条件に該当する成績情報が存在しません。");
            req.getRequestDispatcher(
                "/scoremanager/main/test_list.jsp"
            ).forward(req, res);
            return;
        }

        // 科目名を取得して表示用にセット
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(schoolCd, subjectCd);
        if (subject != null) {
            req.setAttribute("subjectName", subject.getName());
        }

        req.setAttribute("testList",   list);
        req.setAttribute("searchMode", "subject"); // JSPで表示切替に使用

        req.getRequestDispatcher(
            "/scoremanager/main/test_list.jsp"
        ).forward(req, res);
    }

    // =========================================================
    // イベント32: 学生番号で検索
    // =========================================================
    private void searchByStudent(
            HttpServletRequest req,
            HttpServletResponse res) throws Exception {

        String studentNo = req.getParameter("f4");

        // 選択値を保持
        req.setAttribute("f4", studentNo);

        // バリデーション: 学生番号が入力されているか
        if (isBlank(studentNo)) {
            req.setAttribute("errorMessage", "学生番号を入力してください。");
            req.getRequestDispatcher(
                "/scoremanager/main/test_list.jsp"
            ).forward(req, res);
            return;
        }

        // 成績データ存在チェック（既存の TestListStudentDao を使用）
        TestListStudentDao studentCheckDao = new TestListStudentDao();
        if (!studentCheckDao.exists(studentNo)) {
            req.setAttribute("errorMessage", "該当する学生の成績情報が存在しません。");
            req.getRequestDispatcher(
                "/scoremanager/main/test_list.jsp"
            ).forward(req, res);
            return;
        }

        // 成績一覧取得（科目名JOIN済み、既存の TestDao.findByStudentNo を使用）
        TestDao testDao = new TestDao();
        List<Test> list = testDao.findByStudentNo(studentNo);

        req.setAttribute("testList",   list);
        req.setAttribute("studentNo",  studentNo);
        req.setAttribute("searchMode", "student");

        req.getRequestDispatcher(
            "/scoremanager/main/test_list.jsp"
        ).forward(req, res);
    }

    // =========================================================
    // セレクトボックスの選択肢をリクエストにセット（共通処理）
    // =========================================================
    private void setSelectOptions(HttpServletRequest req, String schoolCd) throws Exception {

        // 入学年度リスト（現在年度を基準に前後10年）
        int currentYear = java.time.Year.now().getValue();
        List<Integer> entYearList = new ArrayList<>();
        for (int y = currentYear - 10; y <= currentYear + 10; y++) {
            entYearList.add(y);
        }
        req.setAttribute("entYearList", entYearList);

        // クラス一覧（既存の ClassNumDao を使用）
        ClassNumDao classNumDao = new ClassNumDao();
        req.setAttribute("classList", classNumDao.filter(schoolCd));

        // 科目一覧（既存の SubjectDao を使用）
        SubjectDao subjectDao = new SubjectDao();
        req.setAttribute("subjectList", subjectDao.filter(schoolCd));
    }

    /** null または空文字かを判定 */
    private boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}
