package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        List<String> errors = new ArrayList<>();

        String classNum = req.getParameter("classNum");

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String schoolCd = teacher.getSchool().getCd();

        ClassNumDao dao = new ClassNumDao();

        if (classNum == null || classNum.isEmpty()) {
            errors.add("クラス番号を入力してください");
        }

        if (dao.exists(classNum, schoolCd)) {
            errors.add("すでに存在するクラスです");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("classRegist.jsp").forward(req, res);
            return;
        }

        ClassNum c = new ClassNum();
        c.setClassNum(classNum);
        c.setSchoolCd(schoolCd);

        dao.insert(c);

        req.getRequestDispatcher("classRegistComplete.jsp").forward(req, res);
    }
}