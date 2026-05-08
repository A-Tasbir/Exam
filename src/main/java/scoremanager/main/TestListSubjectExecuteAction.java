package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.SubjectTestRow;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
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

        String entYear = req.getParameter("admissionYear");
        String classNum = req.getParameter("classNo");
        String subjectCd = req.getParameter("subjectCode");

        req.setAttribute("selectedF1", entYear);
        req.setAttribute("selectedF2", classNum);
        req.setAttribute("selectedF3", subjectCd);

        setSelectOptions(req, schoolCd);

        List<SubjectTestRow> list = new ArrayList<>();

        // ================= EMPTY INPUT CHECK =================
        if (isBlank(entYear) && isBlank(classNum) && isBlank(subjectCd)) {

            req.setAttribute(
                "subjectErrorMessage",
                "入学年度とクラスと科目を選択してください"
            );

        } else {

            TestListSubjectDao dao = new TestListSubjectDao();
            list = dao.filter(schoolCd, entYear, classNum, subjectCd);

            // ================= NO RESULT CHECK =================
            if (list == null || list.isEmpty()) {
                req.setAttribute(
                    "subjectErrorMessage",
                    "学生情報が存在しませんでした"
                );
            }
        }

        req.setAttribute("subjectTestList", list);

        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }

    private void setSelectOptions(HttpServletRequest req, String schoolCd) throws Exception {

        int currentYear = java.time.Year.now().getValue();
        List<Integer> entYearList = new ArrayList<>();

        for (int y = currentYear - 10; y <= currentYear + 10; y++) {
            entYearList.add(y);
        }

        req.setAttribute("entYearList", entYearList);

        ClassNumDao classNumDao = new ClassNumDao();
        req.setAttribute("classList", classNumDao.filter(schoolCd));

        SubjectDao subjectDao = new SubjectDao();
        req.setAttribute("subjectList", subjectDao.filter(schoolCd));
    }

    private boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}