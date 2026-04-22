package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String url = "student_update.jsp";

        // パラメータ取得
        String no = req.getParameter("no");

        // 学生取得
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(no);

        // ログイン中の教師取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher != null) {
            String schoolCd = teacher.getSchool().getCd();

            // クラス一覧取得（CLASS_NUM）
            ClassNumDao classDao = new ClassNumDao();
            List<String> class_num_set = classDao.getClassNumsBySchool(schoolCd);

            req.setAttribute("class_num_set", class_num_set);
        }

        req.setAttribute("student", student);
        req.getRequestDispatcher(url).forward(req, res);
    }
}