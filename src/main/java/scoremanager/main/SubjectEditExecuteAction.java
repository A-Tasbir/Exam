package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectEditExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        // パラメータ取得
        String oldCd = req.getParameter("oldCd");
        String cd = req.getParameter("oldCd");
        String name = req.getParameter("name");

        // 前後空白除去
        if (cd != null) {
            cd = cd.trim();
        }

        if (name != null) {
            name = name.trim();
        }

        // エラーメッセージ
        String error = "";
        String errorCd = "";
        String errorName = "";

        SubjectDao dao = new SubjectDao();

        // 変更前データ取得
        Subject old = dao.get("oom", oldCd);

        // データ存在確認
        if (old == null) {
            error = "科目が存在していません";
        }

        // =========================
        // 入力チェック
        // =========================

        // 科目名必須
        if (name == null || name.isEmpty()) {
            errorName = "科目名を入力してください";
        }

        // 科目名文字数
        if (name != null && name.length() > 20) {
            errorName = "科目名は20文字以内で入力してください";
        }

        // =========================
        // エラー時
        // =========================
        if (!error.isEmpty()
                || !errorCd.isEmpty()
                || !errorName.isEmpty()) {

            Subject subject = new Subject();

            subject.setSchoolCd("oom");
            subject.setCd(cd);
            subject.setName(name);

            // 入力値保持
            req.setAttribute("subject", subject);

            // エラーメッセージ設定
            req.setAttribute("error", error);
            req.setAttribute("errorCd", errorCd);
            req.setAttribute("errorName", errorName);

            // 編集画面へ戻る
            RequestDispatcher rd =
                    req.getRequestDispatcher("subject_edit.jsp");

            rd.forward(req, res);

            return;
        }

        // =========================
        // 更新処理
        // =========================
        Subject subject = new Subject();

        subject.setSchoolCd("oom");
        subject.setCd(cd);
        subject.setName(name);

        dao.update(subject, oldCd);

        // 完了メッセージ
        req.setAttribute(
                "message",
                "科目を変更しました。");

        // 完了画面へ
        RequestDispatcher rd =
                req.getRequestDispatcher(
                        "subject_update_done.jsp");

        rd.forward(req, res);
    }
}