package scoremanager.main;

import java.util.List;

import bean.Test;
import dao.TestDao;
import dao.TestListStudentDao;
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
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        // 学生存在チェック
        TestListStudentDao checkDao = new TestListStudentDao();
        if (!checkDao.exists(studentNo)) {
            req.setAttribute("errorMessage", "該当する学生が存在しません");
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        // ★ 成績取得（これが今まで無かった）
        TestDao testDao = new TestDao();
        List<Test> list = testDao.findByStudentNo(studentNo);

        // JSP に渡す
        req.setAttribute("studentNo", studentNo);
        req.setAttribute("testList", list);

        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}