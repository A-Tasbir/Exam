package scoremanager.main;

import bean.School;
import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeacherEditFinalAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        TeacherDao dao = new TeacherDao();
        HttpSession session = req.getSession();

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        Teacher loginUser = (Teacher) session.getAttribute("user");

        if (loginUser == null) {
            res.sendRedirect("index.jsp");
            return;
        }

        Teacher t = new Teacher();
        t.setId(id);
        t.setPassword(password);
        t.setName(name);

        School s = new School();
        s.setCd(loginUser.getSchool().getCd());
        t.setSchool(s);

        // self-demotion always results in false
        t.setAdmin(false);

        // update DB
        dao.update(t);

        // logout
        session.invalidate();

        
        res.sendRedirect(req.getContextPath() + "/scoremanager/index.jsp");
    }
}