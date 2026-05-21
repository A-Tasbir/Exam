package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TeacherListAction extends Action {

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {

        // セッションからログインユーザー取得
        Object obj = request.getSession().getAttribute("user");

        // ログイン確認
        if (!(obj instanceof Teacher)) {
            request.getRequestDispatcher("/error.jsp")
                   .forward(request, response);
            return;
        }

        Teacher loginUser = (Teacher) obj;

        // 管理者権限チェック
        if (!loginUser.isAdmin()) {
            request.getRequestDispatcher("/error.jsp")
                   .forward(request, response);
            return;
        }

        // 学校コード取得
        String schoolCd = loginUser.getSchool().getCd();

        // 教員一覧取得
        TeacherDao dao = new TeacherDao();
        List<Teacher> list = dao.findBySchool(schoolCd);

        // JSPへデータ渡し
        request.setAttribute("list", list);

        // 一覧画面表示
        request.getRequestDispatcher("/scoremanager/main/teacherList.jsp")
               .forward(request, response);
    }
}