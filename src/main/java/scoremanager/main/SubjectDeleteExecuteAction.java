package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception {

        HttpSession session =
                req.getSession();

        Teacher teacher =
                (Teacher) session.getAttribute("user");

        String schoolCd =
                teacher.getSchool().getCd();

        String cd =
                req.getParameter("cd");

        SubjectDao dao =
                new SubjectDao();

        // 存在確認
        Subject subject =
                dao.get(schoolCd, cd);

        // 存在しない場合
        if (subject == null) {

            session.setAttribute(
                    "error",
                    "削除対象の科目が存在しません");

            res.sendRedirect(
                    "SubjectList.action");

            return;
        }

        // 削除実行
        dao.delete(schoolCd, cd);

        res.sendRedirect(
                "SubjectList.action");
    }
}