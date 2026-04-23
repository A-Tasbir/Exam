package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Test;
import dao.StudentDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        String studentNo = req.getParameter("studentNo");

        // 入力チェック
        if (studentNo == null || studentNo.isEmpty()) {
            req.setAttribute("errorMessage", "学籍番号を入力してください");
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // ✅ 学生取得（STUDENTテーブル）
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(studentNo);

        if (student == null) {
            req.setAttribute("errorMessage", "該当する学生が存在しません");
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // ✅ 氏名をセット
        req.setAttribute("studentNo", studentNo);
        req.setAttribute("studentName", student.getName());

        // 成績取得（TESTテーブル）
        TestDao testDao = new TestDao();
        List<Test> list = testDao.findByStudentNo(studentNo);

        // 成績なし
        if (list.isEmpty()) {
            req.setAttribute("errorMessage", "成績情報が存在しませんでした");
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // 成績あり
        req.setAttribute("testList", list);
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}
