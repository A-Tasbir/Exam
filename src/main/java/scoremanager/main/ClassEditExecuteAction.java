package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassEditExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        // エラーメッセージ
        List<String> errors = new ArrayList<>();

        // パラメータ取得
        String oldClassNum =
                req.getParameter("oldClassNum");

        String newClassNum =
                req.getParameter("classNum");

        // セッションからログインユーザー取得
        HttpSession session = req.getSession();

        Teacher teacher =
                (Teacher) session.getAttribute("user");

        // 学校コード取得
        String schoolCd =
                teacher.getSchool().getCd();

        ClassNumDao dao = new ClassNumDao();

        // =========================
        // 入力チェック
        // =========================
        if (newClassNum == null
                || newClassNum.isEmpty()) {

            errors.add("クラス番号を入力してください");
        }

        // =========================
        // エラー時
        // =========================
        if (!errors.isEmpty()) {

            req.setAttribute("errors", errors);

            // 入力値保持
            req.setAttribute(
                    "classNum",
                    oldClassNum);

            req.getRequestDispatcher("classEdit.jsp")
               .forward(req, res);

            return;
        }

        // =========================
        // 更新処理
        // =========================
        dao.update(
                oldClassNum,
                newClassNum,
                schoolCd);

        // 完了画面表示
        req.getRequestDispatcher(
                "classEditComplete.jsp")
           .forward(req, res);
    }
}