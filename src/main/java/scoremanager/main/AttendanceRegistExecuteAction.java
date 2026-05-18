package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Attendance;
import bean.Student;
import bean.Teacher;
import dao.AttendanceDao;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class AttendanceRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        req.setCharacterEncoding("UTF-8");

        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();

        // 入学年度プルダウン
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear + 1; i++) entYearSet.add(i);

        // GET → 学生一覧表示
        if (req.getMethod().equalsIgnoreCase("GET")) {

            String classNum   = req.getParameter("classNum");
            String date       = req.getParameter("date");
            String entYearStr = req.getParameter("entYear");

            if (classNum == null || classNum.equals("0") || date == null || date.isEmpty()) {
                req.getSession().setAttribute("attendError", "入学年度、クラスと日付を選択してください。");
                res.sendRedirect("AttendanceRegist.action");
                return;
            }

            // 学生一覧取得 (入学年度でfilter)
            List<Student> allStudents = new StudentDao().filterAll(schoolCd);
            List<Student> students = new ArrayList<>();
            for (Student s : allStudents) {
                if (!s.getClassNum().equals(classNum)) continue;
                if (entYearStr != null && !entYearStr.equals("0")) {
                    if (s.getEntYear() != Integer.parseInt(entYearStr)) continue;
                }
                students.add(s);
            }

            // 既存出欠取得
            List<Attendance> existing = new AttendanceDao().filter(schoolCd, classNum, LocalDate.parse(date));

            req.setAttribute("students", students);
            req.setAttribute("existing", existing);
            req.setAttribute("classNum", classNum);
            req.setAttribute("date", date);
            req.setAttribute("entYear", entYearStr);
            req.setAttribute("entYearSet", entYearSet);
            req.setAttribute("classNumSet", new ClassNumDao().filter(schoolCd));
            req.setAttribute("today", java.time.LocalDate.now().toString());
            req.getRequestDispatcher("attendance_regist.jsp").forward(req, res);
            return;
        }

        // POST → 出欠保存
        String classNum = req.getParameter("classNum");
        String date     = req.getParameter("date");
        int count       = Integer.parseInt(req.getParameter("count"));

        for (int i = 0; i < count; i++) {
            String studentNo = req.getParameter("studentNo_" + i);
            String status    = req.getParameter("status_" + i);

            if (studentNo == null || studentNo.trim().isEmpty()) continue;
            if (status == null) status = "X";

            Attendance a = new Attendance();
            a.setStudentNo(studentNo);
            a.setSchoolCd(schoolCd);
            a.setClassNum(classNum);
            a.setDate(LocalDate.parse(date));
            a.setStatus(status);
            new AttendanceDao().save(a);
        }

        req.getRequestDispatcher("attendance_regist_done.jsp").forward(req, res);
    }
}