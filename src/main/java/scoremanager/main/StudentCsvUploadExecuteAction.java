package scoremanager.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import tool.Action;

public class StudentCsvUploadExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        List<String> errors = new ArrayList<>();

        // ===== CSV file check =====
        Part csvPart = req.getPart("csvFile");
        if (csvPart == null || csvPart.getSize() == 0) {
            errors.add("CSVファイルを選択してください");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("studentCsvUploadFail.jsp")
               .forward(req, res);
            return;
        }

        // ===== logged-in teacher (same as StudentList) =====
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        String schoolCd = teacher.getSchool().getCd();

        // ===== valid class list from CLASS_NUM =====
        ClassNumDao classDao = new ClassNumDao();
        List<String> validClasses = classDao.getClassNumsBySchool(schoolCd);

        List<Student> students = new ArrayList<>();

        // ===== CSV read =====
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(csvPart.getInputStream(), "UTF-8"))) {

            String line;
            boolean header = true;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                row++;

                if (header) {
                    header = false;
                    continue;
                }

                String[] c = line.split(",");
                if (c.length != 5) {
                    errors.add(row + "行目：CSV形式が不正です");
                    continue;
                }

                try {
                    c[0] = c[0].replace("\uFEFF", ""); // BOM対策

                    String classNum = c[3].trim();

                    // ✅ CLASS_NUM validation
                    if (!validClasses.contains(classNum)) {
                        errors.add(row + "行目：クラス番号「" + classNum + "」はこの学校に存在しません");
                        continue;
                    }

                    Student s = new Student();
                    s.setEntYear(Integer.parseInt(c[0].trim()));
                    s.setNo(c[1].trim());
                    s.setName(c[2].trim());
                    s.setClassNum(classNum);
                    s.setAttend(
                        "true".equals(c[4].trim()) ||
                        "1".equals(c[4].trim())
                    );
                    s.setSchool(teacher.getSchool());

                    students.add(s);

                } catch (Exception e) {
                    errors.add(row + "行目：データ変換に失敗しました");
                }
            }
        }

        // ===== error handling =====
        if (!errors.isEmpty() || students.isEmpty()) {
            if (students.isEmpty()) {
                errors.add("登録対象のデータがありません");
            }
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("studentCsvUploadFail.jsp")
               .forward(req, res);
            return;
        }

        // ===== insert =====
        StudentDao dao = new StudentDao();
        dao.insertBatch(students, schoolCd);

        req.getRequestDispatcher("studentCsvUploadComplete.jsp")
           .forward(req, res);
    }
}