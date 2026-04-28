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

        // ← ★ここが重要
        String entYear = req.getParameter("admissionYear");
        String classNum = req.getParameter("classNo");
        String subjectCd = req.getParameter("subjectCode");

        TestListSubjectDao dao = new TestListSubjectDao();

        List<SubjectTestRow> list =
            dao.filter(schoolCd, entYear, classNum, subjectCd);

        req.setAttribute("testList", list);

        setSelectOptions(req, schoolCd);
        
        req.setAttribute("selectedF1", entYear);
        req.setAttribute("selectedF2", classNum);
        req.setAttribute("selectedF3", subjectCd);
        
        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
    
    private void setSelectOptions(HttpServletRequest req, String schoolCd) throws Exception {

        // 入学年度リスト（現在年度を基準に前後10年）
        int currentYear = java.time.Year.now().getValue();
        List<Integer> entYearList = new ArrayList<>();
        for (int y = currentYear - 10; y <= currentYear + 10; y++) {
            entYearList.add(y);
        }
        req.setAttribute("entYearList", entYearList);

        // クラス一覧（既存の ClassNumDao を使用）
        ClassNumDao classNumDao = new ClassNumDao();
        req.setAttribute("classList", classNumDao.filter(schoolCd));

        // 科目一覧（既存の SubjectDao を使用）
        SubjectDao subjectDao = new SubjectDao();
        req.setAttribute("subjectList", subjectDao.filter(schoolCd));
    }

    /** null または空文字かを判定 */
    private boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}