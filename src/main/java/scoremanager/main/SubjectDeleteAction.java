package scoremanager.main;

import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res
	) throws Exception {

		String cd = req.getParameter("cd");

		String schoolCd = "oom";

		SubjectDao dao = new SubjectDao();

		dao.delete(schoolCd, cd);

		res.sendRedirect("SubjectList.action");
	}
}