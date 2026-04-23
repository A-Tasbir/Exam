package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();

        // 入学年度プルダウン
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear + 1; i++) {
            entYearSet.add(i);
        }

        // クラスプルダウン
        List<String> classNumSet = new dao.ClassNumDao().filter(schoolCd);

        // 科目プルダウン
        List<bean.Subject> subjectSet = new SubjectDao().filter(schoolCd);

        // 回数プルダウン (1〜10)
        List<Integer> noSet = new ArrayList<>();
        for (int i = 1; i <= 10; i++) noSet.add(i);

        req.setAttribute("entYearSet", entYearSet);
        req.setAttribute("classNumSet", classNumSet);
        req.setAttribute("subjectSet", subjectSet);
        req.setAttribute("noSet", noSet);

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}