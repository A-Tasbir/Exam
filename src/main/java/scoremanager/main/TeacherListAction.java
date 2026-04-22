package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TeacherListAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Object obj = request.getSession().getAttribute("user");

		if (!(obj instanceof Teacher)) {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}

		Teacher loginUser = (Teacher) obj;

		if (!loginUser.isAdmin()) {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}

		String schoolCd = loginUser.getSchool().getCd();

		TeacherDao dao = new TeacherDao();
		List<Teacher> list = dao.findBySchool(schoolCd);

		request.setAttribute("list", list);

		request.getRequestDispatcher("/scoremanager/main/teacherList.jsp")
			.forward(request, response);
	}
}