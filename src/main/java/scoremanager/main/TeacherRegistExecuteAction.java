package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TeacherRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        List<String> errors = new ArrayList<>();
        TeacherDao dao = new TeacherDao();

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        // validation
        if (id == null || id.isEmpty()) errors.add("IDを入力してください");
        if (password == null || password.isEmpty()) errors.add("パスワードを入力してください");
        if (name == null || name.isEmpty()) errors.add("名前を入力してください");

        // duplicate check
        if (errors.isEmpty() && dao.exists(id)) {
            errors.add("このIDはすでに使用されています");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("id", id);
            req.setAttribute("name", name);
            req.getRequestDispatcher("teacherRegist.jsp").forward(req, res);
            return;
        }

        // =========================
        // FIX: SAFE + DAO-COMPATIBLE
        // =========================
        Object obj = req.getSession().getAttribute("user");

        if (!(obj instanceof Teacher)) {
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        Teacher loginUser = (Teacher) obj;

        School school = loginUser.getSchool();

        if (school == null || school.getCd() == null) {
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        // =========================
        // CREATE ENTITY (DAO MATCH)
        // =========================
        Teacher t = new Teacher();
        t.setId(id);
        t.setPassword(password);
        t.setName(name);

        School s = new School();
        s.setCd(school.getCd());
        t.setSchool(s);

        t.setAdmin(req.getParameter("admin") != null);

        dao.insert(t);

        res.sendRedirect("teacherRegistComplete.jsp");
    }
}