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

 

        // プルダウン再表示用
        setSelectOptions(req, schoolCd);

 

        req.setAttribute("selectedF1", entYear);
        req.setAttribute("selectedF2", classNum);
        req.setAttribute("selectedF3", subjectCd);

 

        // =========================
        // 入力チェック
        // =========================
        if (isBlank(entYear)
                || isBlank(classNum)
                || isBlank(subjectCd)) {

 

            req.setAttribute("errorMessage",
                    "入学年度・クラス・科目を選択してください");

 

            req.getRequestDispatcher("test_list_subject.jsp")
               .forward(req, res);
            return;
        }

 

        // =========================
        // 成績検索
        // =========================
        TestListSubjectDao dao = new TestListSubjectDao();

 

        List<SubjectTestRow> list =
                dao.filter(schoolCd, entYear, classNum, subjectCd);
        if (list == null || list.isEmpty()) {

 

            req.setAttribute("errorMessage",
                    "学生情報が存在しませんでした");

 

            req.getRequestDispatcher("test_list_subject.jsp")
               .forward(req, res);
            return;
        }

 

        req.setAttribute("testList", list);

 

        req.getRequestDispatcher("test_list_subject.jsp")
           .forward(req, res);
    }

 

    private void setSelectOptions(HttpServletRequest req,
                                  String schoolCd) throws Exception {

 

        int currentYear = java.time.Year.now().getValue();

 

        List<Integer> entYearList = new ArrayList<>();

 

        for (int y = currentYear - 10; y <= currentYear + 10; y++) {
            entYearList.add(y);
        }

 

        req.setAttribute("entYearList", entYearList);

 

        ClassNumDao classNumDao = new ClassNumDao();
        req.setAttribute("classList",
                classNumDao.filter(schoolCd));

 

        SubjectDao subjectDao = new SubjectDao();
        req.setAttribute("subjectList",
                subjectDao.filter(schoolCd));
    }

 

    /** null または空文字判定 */
    private boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}