package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        // パラメータ取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // 前後空白除去
        if (cd != null) {
            cd = cd.trim();
        }

        if (name != null) {
            name = name.trim();
        }

        // エラーメッセージ
        String errorCd = "";
        String errorName = "";

        // =========================
        // 入力チェック
        // =========================

        // 科目コード必須
        if (cd == null || cd.isEmpty()) {
            errorCd = "科目コードを入力してください";
        }

        // 科目名必須
        if (name == null || name.isEmpty()) {
            errorName = "科目名を入力してください";
        }

        // 科目コード文字数
        if (cd != null && cd.length() > 3) {
            errorCd = "科目コードは3文字以内で入力してください";
        }

        // 科目名文字数
        if (name != null && name.length() > 20) {
            errorName = "科目名は20文字以内で入力してください";
        }

        // =========================
        // 重複チェック
        // =========================
        SubjectDao dao = new SubjectDao();

        Subject old = dao.get("oom", cd);

        if (old != null) {
            errorCd = "科目コードが重複しています";
        }

        // =========================
        // エラー時
        // =========================
        if (!errorCd.isEmpty() || !errorName.isEmpty()) {

            // エラーメッセージ設定
            req.setAttribute("errorCd", errorCd);
            req.setAttribute("errorName", errorName);

            // 入力値保持
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);

            // 登録画面へ戻る
            RequestDispatcher rd =
                    req.getRequestDispatcher("subject_create.jsp");

            rd.forward(req, res);

            return;
        }

        // =========================
        // 登録処理
        // =========================
        Subject subject = new Subject();

        subject.setSchoolCd("oom");
        subject.setCd(cd);
        subject.setName(name);

        dao.insert(subject);

        // 完了メッセージ
        req.setAttribute(
                "message",
                "科目を登録しました。");

        // 完了画面へ
        RequestDispatcher rd =
                req.getRequestDispatcher("subject_done.jsp");

        rd.forward(req, res);
    }
}