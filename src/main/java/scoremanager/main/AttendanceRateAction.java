package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.AttendanceDao;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class AttendanceRateAction extends Action {

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
        String entYearStr = req.getParameter("entYear");

        req.setAttribute("entYear", entYearStr);

        if (classNum != null && !classNum.equals("0")) {

            List<Student> allStudents = new StudentDao().filterAll(schoolCd);
            AttendanceDao attendanceDao = new AttendanceDao();

            List<java.util.Map<String, Object>> rows = new ArrayList<>();
            for (Student s : allStudents) {
                if (!s.getClassNum().equals(classNum)) continue;
                // 入学年度 filter
                if (entYearStr != null && !entYearStr.equals("0")) {
                    if (s.getEntYear() != Integer.parseInt(entYearStr)) continue;
                }

                int total   = attendanceDao.countAll(s.getNo(), schoolCd);
                int present = attendanceDao.countByStatus(s.getNo(), schoolCd, "O");
                int absent  = attendanceDao.countByStatus(s.getNo(), schoolCd, "X");
                int late    = attendanceDao.countByStatus(s.getNo(), schoolCd, "T");
                double rate = total > 0 ? (double)(present + late * 0.5) / total * 100 : 0;

                java.util.Map<String, Object> row = new java.util.HashMap<>();
                row.put("studentNo", s.getNo());
                row.put("studentName", s.getName());
                row.put("total", total);
                row.put("present", present);
                row.put("absent", absent);
                row.put("late", late);
                row.put("rate", String.format("%.1f", rate));
                rows.add(row);
            }

            req.setAttribute("rows", rows);
            req.setAttribute("classNum", classNum);
        }

        req.getRequestDispatcher("attendance_rate.jsp").forward(req, res);
    }
}