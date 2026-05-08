package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectEditAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String url = "subject_edit.jsp";

        String cd = req.getParameter("cd");

        SubjectDao dao = new SubjectDao();

        Subject subject = dao.get("oom", cd);

        req.setAttribute("subject", subject);

        req.getRequestDispatcher(url).forward(req, res);
    }
}