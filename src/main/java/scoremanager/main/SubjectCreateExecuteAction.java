package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res
	) throws Exception {

		String cd = req.getParameter("cd");

		String name = req.getParameter("name");

		Subject subject = new Subject();

		subject.setSchoolCd("oom");

		subject.setCd(cd);

		subject.setName(name);

		SubjectDao dao = new SubjectDao();

		dao.insert(subject);

		res.sendRedirect("SubjectList.action");
	}
}