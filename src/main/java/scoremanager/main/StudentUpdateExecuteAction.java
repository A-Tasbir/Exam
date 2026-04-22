package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// ローカル変数の宣言 1
		String url = "";
		List<String> errors = new ArrayList<>();

		// リクエストパラメータの取得 2
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		String classNum = req.getParameter("classNum");
		String isAttendStr = req.getParameter("isAttend");

		// ビジネスロジック 4
		// 氏名の未入力チェック（HTML required属性でも制御するが、サーバ側でも確認）
		if (name == null || name.isEmpty()) {
			errors.add("このフィールドを入力して下さい");
		}

		if (!errors.isEmpty()) {
			// エラーがある場合は変更画面に戻る
			StudentDao studentDao = new StudentDao();
			Student student = studentDao.get(no);
			req.setAttribute("student", student);
			req.setAttribute("errors", errors);
			url = "student_update.jsp";
			req.getRequestDispatcher(url).forward(req, res);
			return;
		}

		// DBへデータ保存 5
		Student student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setClassNum(classNum);
		student.setAttend(isAttendStr != null && isAttendStr.equals("true"));

		StudentDao studentDao = new StudentDao();
		studentDao.update(student);

		// JSPへフォワード 7
		url = "student_update_done.jsp";
		req.getRequestDispatcher(url).forward(req, res);
	}
}