package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// ローカル変数の宣言 1
		String url = "";
		List<String> errors = new ArrayList<>();

		// リクエストパラメータの取得 2
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		String entYearStr = req.getParameter("entYear");
		String classNum = req.getParameter("classNum");
		String isAttendStr = req.getParameter("isAttend");

		// セッションからログイン中の教員を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// ビジネスロジック 4
		// 入学年度の未入力チェック
		if (entYearStr == null || entYearStr.isEmpty()) {
			errors.add("入学年度を選択してください");
		}

		// 学生番号の重複チェック
		if (errors.isEmpty()) {
			StudentDao studentDao = new StudentDao();
			if (studentDao.get(no) != null) {
				errors.add("学生番号が重複しています");
			}
		}

		if (!errors.isEmpty()) {
			// エラーがある場合は登録画面に戻る
			req.setAttribute("errors", errors);
			req.setAttribute("no", no);
			req.setAttribute("name", name);
			req.setAttribute("entYear", entYearStr);
			req.setAttribute("classNum", classNum);
			req.setAttribute("isAttend", isAttendStr);
			url = "student_create.jsp";
			req.getRequestDispatcher(url).forward(req, res);
			return;
		}

		// DBへデータ保存 5
		Student student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(Integer.parseInt(entYearStr));
		student.setClassNum(classNum);
		student.setAttend(isAttendStr != null && isAttendStr.equals("true"));
		student.setSchool(teacher.getSchool());

		StudentDao studentDao = new StudentDao();
		studentDao.insert(student);

		// JSPへフォワード 7
		url = "student_create_done.jsp";
		req.getRequestDispatcher(url).forward(req, res);
	}
}