package scoremanager.main;

import java.util.List;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectListAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res
	) throws Exception {

		String url = "subjectList.jsp";

		String schoolCd = "oom";

		SubjectDao dao = new SubjectDao();

		List<Subject> list =
				dao.filter(schoolCd);

		req.setAttribute("list", list);

		req.getRequestDispatcher(url)
				.forward(req, res);
	}
}