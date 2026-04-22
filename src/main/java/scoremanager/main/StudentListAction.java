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

public class StudentListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String url = "student_list.jsp";

        List<Student> students = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス
        String f3 = req.getParameter("f3"); // 在学中

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String schoolCd = teacher.getSchool().getCd();

        StudentDao dao = new StudentDao();

        int entYear = 0;
        Boolean isAttend = null;

        if (f1 != null && !f1.isEmpty()) {
            entYear = Integer.parseInt(f1);
        }

        if (f3 != null && !f3.isEmpty()) {
            isAttend = true;
        }

        if (f2 != null && !f2.isEmpty() && entYear == 0) {
            errors.add("クラスを指定する場合は入学年度も指定してください");
        }

        if (errors.isEmpty()) {
            students = dao.filter(schoolCd, entYear, f2, isAttend);
        }

       
        int currentYear = java.time.Year.now().getValue();

        List<Integer> ent_year_set = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            ent_year_set.add(currentYear - i);
        }

        
        List<Student> allStudents = dao.filter(schoolCd, 0, null, null);

        List<String> class_num_set = new ArrayList<>();
        for (Student s : allStudents) {
            if (!class_num_set.contains(s.getClassNum())) {
                class_num_set.add(s.getClassNum());
            }
        }

        
        req.setAttribute("students", students);
        req.setAttribute("errors", errors);

        req.setAttribute("ent_year_set", ent_year_set);
        req.setAttribute("class_num_set", class_num_set);

        req.setAttribute("currentYear", currentYear);

        req.setAttribute("f1", f1);
        req.setAttribute("f2", f2);
        req.setAttribute("f3", f3);

        req.getRequestDispatcher(url).forward(req, res);
    }
}