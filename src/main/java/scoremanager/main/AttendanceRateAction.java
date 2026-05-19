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

        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear + 1; i++) entYearSet.add(i);
        req.setAttribute("entYearSet", entYearSet);

        List<Integer> monthSet = new ArrayList<>();
        for (int i = 1; i <= 12; i++) monthSet.add(i);
        req.setAttribute("monthSet", monthSet);

        String classNum      = req.getParameter("classNum");
        String entYearStr    = req.getParameter("entYear");
        String attendYearStr = req.getParameter("attendYear");
        String monthStr      = req.getParameter("month");

        req.setAttribute("entYear", entYearStr);
        req.setAttribute("attendYear", attendYearStr);
        req.setAttribute("classNum", classNum);
        req.setAttribute("month", monthStr);

        if (classNum != null && !classNum.equals("0")) {

            List<Student> allStudents = new StudentDao().filterAll(schoolCd);
            AttendanceDao attendanceDao = new AttendanceDao();

            int month = (monthStr != null && !monthStr.equals("0")) ?
                        Integer.parseInt(monthStr) : 0;
            int attendYear = (attendYearStr != null && !attendYearStr.equals("0")) ?
                             Integer.parseInt(attendYearStr) : 0;
            int entYear = (entYearStr != null && !entYearStr.equals("0")) ?
                          Integer.parseInt(entYearStr) : 0;

            List<java.util.Map<String, Object>> rows = new ArrayList<>();

            for (Student s : allStudents) {
                if (!s.getClassNum().equals(classNum)) continue;
                if (entYear > 0 && s.getEntYear() != entYear) continue;

                int total, present, absent, late, early;

                if (month > 0 && attendYear > 0) {
                    // 月・年 両方指定
                    total   = attendanceDao.countAllByMonth(s.getNo(), schoolCd, attendYear, month);
                    present = attendanceDao.countByStatusAndMonth(s.getNo(), schoolCd, "O", attendYear, month);
                    absent  = attendanceDao.countByStatusAndMonth(s.getNo(), schoolCd, "X", attendYear, month);
                    late    = attendanceDao.countByStatusAndMonth(s.getNo(), schoolCd, "T", attendYear, month);
                    early   = attendanceDao.countByStatusAndMonth(s.getNo(), schoolCd, "S", attendYear, month);

                } else if (attendYear > 0) {
                    // ✅ 月=全て、年だけ指定 → その年のみ
                    total   = attendanceDao.countAllByYear(s.getNo(), schoolCd, attendYear);
                    present = attendanceDao.countByStatusAndYear(s.getNo(), schoolCd, "O", attendYear);
                    absent  = attendanceDao.countByStatusAndYear(s.getNo(), schoolCd, "X", attendYear);
                    late    = attendanceDao.countByStatusAndYear(s.getNo(), schoolCd, "T", attendYear);
                    early   = attendanceDao.countByStatusAndYear(s.getNo(), schoolCd, "S", attendYear);

                } else {
                    // 年も月も全て → 全期間
                    total   = attendanceDao.countAll(s.getNo(), schoolCd);
                    present = attendanceDao.countByStatus(s.getNo(), schoolCd, "O");
                    absent  = attendanceDao.countByStatus(s.getNo(), schoolCd, "X");
                    late    = attendanceDao.countByStatus(s.getNo(), schoolCd, "T");
                    early   = attendanceDao.countByStatus(s.getNo(), schoolCd, "S");
                }

                double rate = total > 0 ?
                    (double)(present + late * 0.5 + early * 0.5) / total * 100 : 0;

                java.util.Map<String, Object> row = new java.util.HashMap<>();
                row.put("studentNo", s.getNo());
                row.put("studentName", s.getName());
                row.put("total", total);
                row.put("present", present);
                row.put("absent", absent);
                row.put("late", late);
                row.put("early", early);
                row.put("rate", String.format("%.1f", rate));
                rows.add(row);
            }

            req.setAttribute("rows", rows);

        } else if (classNum != null) {
            req.setAttribute("error", "クラスを選択してください。");
        }

        req.getRequestDispatcher("attendance_rate.jsp").forward(req, res);
    }
}