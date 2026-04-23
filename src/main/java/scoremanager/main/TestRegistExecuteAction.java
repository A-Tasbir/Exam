package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import bean.TestRow;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();

        String entYearStr = req.getParameter("entYear");
        String classNum   = req.getParameter("classNum");
        String subjectCd  = req.getParameter("subjectCd");
        String noStr      = req.getParameter("no");

        // GET → 学生一覧を検索して表示
        if (req.getMethod().equalsIgnoreCase("GET")) {

            // バリデーション
            if (entYearStr == null || entYearStr.equals("0") ||
                classNum == null || classNum.equals("0") ||
                subjectCd == null || subjectCd.equals("0") ||
                noStr == null || noStr.equals("0")) {

                req.getSession().setAttribute("registError", "入学年度、クラス、科目、回数を選択してください。");
                res.sendRedirect("TestRegist.action");
                return;
            }

            int entYear = Integer.parseInt(entYearStr);
            int no = Integer.parseInt(noStr);

            // 学生一覧取得
            List<Student> students = new StudentDao().filter(schoolCd, entYear, classNum, true);

            // 既存点数取得
            List<Test> existingTests = new TestDao().filter(schoolCd, classNum, subjectCd, no);

            // 表示用リスト作成
            List<TestRow> tests = new ArrayList<>();
            for (Student s : students) {
                TestRow row = new TestRow();
                row.setStudentNo(s.getNo());
                row.setStudentName(s.getName());
                row.setEntYear(s.getEntYear());
                row.setClassNum(s.getClassNum());
                for (Test t : existingTests) {
                    if (t.getStudentNo().equals(s.getNo())) {
                        row.setPoint(t.getPoint());
                        break;
                    }
                }
                tests.add(row);
            }

            // 科目名取得
            Subject subject = new SubjectDao().get(schoolCd, subjectCd);
            String subjectName = subject != null ? subject.getName() : "";

            req.setAttribute("tests", tests);
            req.setAttribute("subjectName", subjectName);
            req.setAttribute("subjectCd", subjectCd);
            req.setAttribute("schoolCd", schoolCd);
            req.setAttribute("no", no);
            req.setAttribute("entYear", entYear);
            req.setAttribute("classNum", classNum);
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        // POST → 点数を保存
        int no = Integer.parseInt(noStr);
        int count = Integer.parseInt(req.getParameter("count"));

        boolean hasError = false;
        List<TestRow> tests = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String studentNo = req.getParameter("studentNo_" + i);
            String pointStr  = req.getParameter("point_" + i);

            TestRow row = new TestRow();
            row.setStudentNo(studentNo);

            // バリデーション (0〜100)
            try {
                int point = Integer.parseInt(pointStr);
                if (point < 0 || point > 100) {
                    row.setPointError("0〜100の数値を入力してください。");
                    row.setPoint(point);
                    hasError = true;
                } else {
                    row.setPoint(point);
                }
            } catch (NumberFormatException e) {
                row.setPointError("0〜100の数値を入力してください。");
                hasError = true;
            }
            tests.add(row);
        }

        if (hasError) {
            
            List<Student> students = new StudentDao().filter(
                schoolCd, Integer.parseInt(entYearStr), classNum, true);

            for (TestRow row : tests) {
                for (Student s : students) {
                    if (s.getNo().equals(row.getStudentNo())) {
                        row.setStudentName(s.getName());
                        row.setEntYear(s.getEntYear());
                        row.setClassNum(s.getClassNum());
                        break;
                    }
                }
            }

            Subject subject = new SubjectDao().get(schoolCd, subjectCd);
            String subjectName = subject != null ? subject.getName() : "";

            req.setAttribute("tests", tests);
            req.setAttribute("subjectName", subjectName);
            req.setAttribute("subjectCd", subjectCd);
            req.setAttribute("schoolCd", schoolCd);
            req.setAttribute("no", no);
            req.setAttribute("entYear", entYearStr);
            req.setAttribute("classNum", classNum);
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        // DB に保存
        for (TestRow row : tests) {
            if (row.getPointError() == null) {
                Test t = new Test();
                t.setStudentNo(row.getStudentNo());
                t.setSubjectCd(subjectCd);
                t.setSchoolCd(schoolCd);
                t.setNo(no);
                t.setPoint(row.getPoint());
                t.setClassNum(classNum);
                new TestDao().save(t);
            }
        }

        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}