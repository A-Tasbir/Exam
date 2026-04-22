package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassEditExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        List<String> errors = new ArrayList<>();

        String oldClassNum = req.getParameter("oldClassNum");
        String newClassNum = req.getParameter("classNum");

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String schoolCd = teacher.getSchool().getCd();

        ClassNumDao dao = new ClassNumDao();

        if (newClassNum == null || newClassNum.isEmpty()) {
            errors.add("クラス番号を入力してください");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("classNum", oldClassNum);
            req.getRequestDispatcher("classEdit.jsp").forward(req, res);
            return;
        }

        dao.update(oldClassNum, newClassNum, schoolCd);

        req.getRequestDispatcher("classEditComplete.jsp").forward(req, res);
    }
}