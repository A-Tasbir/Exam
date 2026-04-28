package scoremanager.main;

import java.util.List;

import bean.SubjectTestRow;
import bean.Teacher;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String schoolCd = teacher.getSchool().getCd();

        // ← ★ここが重要
        String entYear = req.getParameter("admissionYear");
        String classNum = req.getParameter("classNo");
        String subjectCd = req.getParameter("subjectCode");

        TestListSubjectDao dao = new TestListSubjectDao();

        List<SubjectTestRow> list =
            dao.filter(schoolCd, entYear, classNum, subjectCd);

        req.setAttribute("testList", list);

        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}