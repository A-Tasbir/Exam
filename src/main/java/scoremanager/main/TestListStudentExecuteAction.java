package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        String studentNo = req.getParameter("studentNo");

        // =========================
        // 入力チェック
        // =========================
        if (studentNo == null || studentNo.isEmpty()) {
            req.setAttribute("errorMessage", "学籍番号を入力してください");
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // =========================
        // 学生取得
        // =========================
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(studentNo);

        if (student == null) {
            req.setAttribute("errorMessage", "該当する学生が存在しません");
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // 学校コード（安全取得）
        String schoolCd = null;
        if (student.getSchool() != null) {
            schoolCd = student.getSchool().getCd();
        }

        // =========================
        // 🔽 dropdownデータ（test_list.jspと完全同じ構造）
        // =========================

        int currentYear = java.time.Year.now().getValue();
        List<Integer> entYearList = new ArrayList<>();

        for (int y = currentYear - 10; y <= currentYear + 10; y++) {
            entYearList.add(y);
        }
        req.setAttribute("entYearList", entYearList);

        ClassNumDao classNumDao = new ClassNumDao();
        if (schoolCd != null) {
            req.setAttribute("classList", classNumDao.filter(schoolCd));
        } else {
            req.setAttribute("classList", new ArrayList<>());
        }

        SubjectDao subjectDao = new SubjectDao();
        if (schoolCd != null) {
            req.setAttribute("subjectList", subjectDao.filter(schoolCd));
        } else {
            req.setAttribute("subjectList", new ArrayList<>());
        }

        // =========================
        // 成績取得
        // =========================
        TestDao testDao = new TestDao();
        List<Test> list = testDao.findByStudentNo(studentNo);

        if (list.isEmpty()) {
            req.setAttribute("errorMessage", "成績情報が存在しませんでした");
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // =========================
        // 表示データ
        // =========================
        req.setAttribute("studentNo", studentNo);
        req.setAttribute("studentName", student.getName());
        req.setAttribute("testList", list);

        // =========================
        // JSPへ
        // =========================
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}