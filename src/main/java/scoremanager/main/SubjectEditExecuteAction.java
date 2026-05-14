package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectEditExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res
	) throws Exception {

		String oldCd = req.getParameter("oldCd");
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");

		if (cd != null) {
			cd = cd.trim();
		}

		if (name != null) {
			name = name.trim();
		}

		String errorCd = "";
		String errorName = "";

		SubjectDao dao = new SubjectDao();

		Subject old = dao.get("oom", oldCd);

		if (old == null) {

			errorName = "科目が存在していません";

		}

		if (cd == null || cd.isEmpty()) {
			errorCd = "科目コードを入力してください";
		}

		if (name == null || name.isEmpty()) {
			errorName = "科目名を入力してください";
		}

		if (cd != null && cd.length() > 3) {
			errorCd = "科目コードは3文字以内で入力してください";
		}

		if (name != null && name.length() > 20) {
			errorName = "科目名は20文字以内で入力してください";
		}

		if (!oldCd.equals(cd)) {

			Subject exists =
					dao.get("oom", cd);

			if (exists != null) {
				errorCd = "科目コードが重複しています";
			}
		}

		if (!errorCd.isEmpty() || !errorName.isEmpty()) {

			Subject subject = new Subject();

			subject.setSchoolCd("oom");
			subject.setCd(cd);
			subject.setName(name);

			req.setAttribute("subject", subject);

			req.setAttribute("errorCd", errorCd);
			req.setAttribute("errorName", errorName);

			RequestDispatcher rd =
					req.getRequestDispatcher("subject_edit.jsp");

			rd.forward(req, res);

			return;
		}

		Subject subject = new Subject();

		subject.setSchoolCd("oom");
		subject.setCd(cd);
		subject.setName(name);

		dao.update(subject, oldCd);

		res.sendRedirect("SubjectList.action");
	}
}