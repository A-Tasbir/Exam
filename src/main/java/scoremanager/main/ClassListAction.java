package scoremanager.main;

import java.util.List;

import bean.ClassGroup;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String schoolCd = teacher.getSchool().getCd();

        ClassNumDao dao = new ClassNumDao();
        List<ClassGroup> classList = dao.getClassesWithStudents(schoolCd);

        req.setAttribute("classList", classList);

        req.getRequestDispatcher("classList.jsp").forward(req, res);
    }
}