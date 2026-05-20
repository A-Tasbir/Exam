package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.RequestDispatcher;
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

		if (cd != null) {
			cd = cd.trim();
		}

		if (name != null) {
			name = name.trim();
		}

		String errorCd = "";
		String errorName = "";

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

		SubjectDao dao = new SubjectDao();

		Subject old = dao.get("oom", cd);

		if (old != null) {
			errorCd = "科目コードが重複しています";
		}

		if (!errorCd.isEmpty() || !errorName.isEmpty()) {

			req.setAttribute("errorCd", errorCd);
			req.setAttribute("errorName", errorName);

			req.setAttribute("cd", cd);
			req.setAttribute("name", name);

			RequestDispatcher rd =
					req.getRequestDispatcher("subject_create.jsp");

			rd.forward(req, res);

			return;
		}

		Subject subject = new Subject();

		subject.setSchoolCd("oom");
		subject.setCd(cd);
		subject.setName(name);

		dao.insert(subject);

		// 完了メッセージ
		req.setAttribute("message", "科目を登録しました。");

		// 完了画面へ
		RequestDispatcher rd =
				req.getRequestDispatcher("subject_done.jsp");

		rd.forward(req, res);
	}
}