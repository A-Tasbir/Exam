package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.RequestDispatcher;
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

		// 存在確認
		Subject subject =
				dao.get(schoolCd, cd);

		// 存在しない場合
		if (subject == null) {

			req.setAttribute(
					"error",
					"削除対象の科目が存在していません");

			RequestDispatcher rd =
					req.getRequestDispatcher("SubjectList.action");

			rd.forward(req, res);

			return;
		}

		// 削除実行
		dao.delete(schoolCd, cd);

		res.sendRedirect("SubjectList.action");
	}
}