package scoremanager.main;

import java.util.List;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        // 遷移先JSP
        String url = "subjectList.jsp";

        // 学校コード
        String schoolCd = "oom";

        // 科目一覧取得
        SubjectDao dao = new SubjectDao();

        List<Subject> list =
                dao.filter(schoolCd);

        // JSPへデータ渡し
        req.setAttribute("list", list);

        // 画面表示
        req.getRequestDispatcher(url)
           .forward(req, res);
    }
}