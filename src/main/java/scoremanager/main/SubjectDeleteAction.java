package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {
    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();
        String cd = req.getParameter("cd");

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(schoolCd, cd);

        // 存在しない場合
        if (subject == null) {
            req.setAttribute("error", "削除対象の科目が存在していません");
            req.getRequestDispatcher("SubjectList.action").forward(req, res);
            return;
        }

        // 確認画面へ
        req.setAttribute("subject", subject);
        req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
    }
}