package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeacherEditExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        TeacherDao dao = new TeacherDao();
        HttpSession session = req.getSession();
        Teacher loginUser = (Teacher) session.getAttribute("user");

        List<String> errors = new ArrayList<>();

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String adminParam = req.getParameter("admin");

        if (id == null || id.isEmpty()) {
            req.getRequestDispatcher("TeacherList.action").forward(req, res);
            return;
        }

        if (password == null || password.isEmpty()) errors.add("パスワードを入力してください");
        if (name == null || name.isEmpty()) errors.add("名前を入力してください");

        boolean isSelf = loginUser != null && loginUser.getId().equals(id);
        boolean removingAdmin = (adminParam == null);

        // 🔥 SELF DEMOTION DETECTED → CONFIRM PAGE
        if (isSelf && removingAdmin) {

            req.setAttribute("id", id);
            req.setAttribute("password", password);
            req.setAttribute("name", name);

            req.getRequestDispatcher("/scoremanager/main/teacherAdminConfirm.jsp")
                .forward(req, res);
            return;
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("teacher", dao.get(id));

            req.getRequestDispatcher("/scoremanager/main/teacherEdit.jsp")
                .forward(req, res);
            return;
        }

        Teacher t = new Teacher();
        t.setId(id);
        t.setPassword(password);
        t.setName(name);

        School s = new School();
        s.setCd(loginUser.getSchool().getCd());
        t.setSchool(s);

        t.setAdmin(adminParam != null);

        dao.update(t);

        res.sendRedirect("TeacherList.action");
    }
}