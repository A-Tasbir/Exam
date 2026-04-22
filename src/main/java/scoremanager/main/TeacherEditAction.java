package scoremanager.main;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TeacherEditAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String id = req.getParameter("id");

		TeacherDao dao = new TeacherDao();
		Teacher t = dao.get(id);

		req.setAttribute("teacher", t);

		req.getRequestDispatcher("/scoremanager/main/teacherEdit.jsp")
			.forward(req, res);
	}
}