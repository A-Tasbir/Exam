package scoremanager.main;

import java.time.Year;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String url = "student_create.jsp";

        // 現在年
        int currentYear = Year.now().getValue();
        req.setAttribute("currentYear", currentYear);

        // ログイン教師取得（既存ルール）
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // クラス一覧取得（CLASS_NUM テーブル）
        if (teacher != null) {
            String schoolCd = teacher.getSchool().getCd();

            ClassNumDao classDao = new ClassNumDao();
            List<String> class_num_set = classDao.getClassNumsBySchool(schoolCd);

            req.setAttribute("class_num_set", class_num_set);
        }

        req.getRequestDispatcher(url).forward(req, res);
    }
}