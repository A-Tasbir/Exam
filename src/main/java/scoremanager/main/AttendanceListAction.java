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

public class AttendanceListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();

        List<String> classNumSet = new ClassNumDao().filter(schoolCd);
        req.setAttribute("classNumSet", classNumSet);

        // 入学年度プルダウン
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear + 1; i++) entYearSet.add(i);
        req.setAttribute("entYearSet", entYearSet);

        String classNum   = req.getParameter("classNum");
        String date       = req.getParameter("date");
        String entYearStr = req.getParameter("entYear");

        req.setAttribute("entYear", entYearStr);

        if (classNum != null && !classNum.equals("0") && date != null && !date.isEmpty()) {

            List<Attendance> attendances = new AttendanceDao().filter(schoolCd, classNum, LocalDate.parse(date));
            List<Student> allStudents = new StudentDao().filterAll(schoolCd);

            List<java.util.Map<String, Object>> rows = new ArrayList<>();
            for (Student s : allStudents) {
                if (!s.getClassNum().equals(classNum)) continue;
                // 入学年度 filter
                if (entYearStr != null && !entYearStr.equals("0")) {
                    if (s.getEntYear() != Integer.parseInt(entYearStr)) continue;
                }
                java.util.Map<String, Object> row = new java.util.HashMap<>();
                row.put("studentNo", s.getNo());
                row.put("studentName", s.getName());
                String status = "−";
                for (Attendance a : attendances) {
                    if (a.getStudentNo().equals(s.getNo())) {
                        status = a.getStatus();
                        break;
                    }
                }
                row.put("status", status);
                rows.add(row);
            }

            req.setAttribute("rows", rows);
            req.setAttribute("classNum", classNum);
            req.setAttribute("date", date);
        } else if (classNum != null || date != null) {
            req.setAttribute("error", "クラスと日付を選択してください。");
        }

        req.getRequestDispatcher("attendance_list.jsp").forward(req, res);
    }
}