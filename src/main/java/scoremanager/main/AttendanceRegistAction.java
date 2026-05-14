package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class AttendanceRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();

        // クラスプルダウン
        List<String> classNumSet = new ClassNumDao().filter(schoolCd);
        req.setAttribute("classNumSet", classNumSet);
        
        
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear + 1; i++) entYearSet.add(i);
        req.setAttribute("entYearSet", entYearSet);

        // 今日の日付をデフォルト
        req.setAttribute("today", java.time.LocalDate.now().toString());

        req.getRequestDispatcher("attendance_regist.jsp").forward(req, res);
    }
}