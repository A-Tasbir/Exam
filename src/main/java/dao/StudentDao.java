package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    /* ===============================
     * ResultSet → Student
     * =============================== */
    private Student mapStudent(ResultSet rs, School school) throws SQLException {
        Student student = new Student();
        student.setNo(rs.getString("no"));
        student.setName(rs.getString("name"));
        student.setEntYear(rs.getInt("ent_year"));
        student.setClassNum(rs.getString("class_num"));
        student.setAttend(rs.getBoolean("is_attend"));
        student.setSchool(school);
        return student;
    }

    /* ===============================
     * 学生1件取得
     * =============================== */
    public Student get(String no) throws Exception {
        Student student = null;
        Connection conn = getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                "SELECT * FROM student WHERE no = ?"
            );
            ps.setString(1, no);
            ResultSet rs = ps.executeQuery();

            SchoolDao schoolDao = new SchoolDao();
            if (rs.next()) {
                School school = schoolDao.get(rs.getString("school_cd"));
                student = mapStudent(rs, school);
            }
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return student;
    }

    /* ===============================
     * 学生一覧検索
     * =============================== */
    public List<Student> filter(
            String schoolCd, int entYear, String classNum, Boolean isAttend)
            throws Exception {

        List<Student> list = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement ps = null;

        try {
            StringBuilder sql = new StringBuilder(
                "SELECT * FROM student WHERE school_cd = ?");

            if (entYear != 0) sql.append(" AND ent_year = ?");
            if (classNum != null && !classNum.isEmpty()) sql.append(" AND class_num = ?");
            if (isAttend != null) sql.append(" AND is_attend = ?");
            sql.append(" ORDER BY no");

            ps = conn.prepareStatement(sql.toString());

            int idx = 1;
            ps.setString(idx++, schoolCd);
            if (entYear != 0) ps.setInt(idx++, entYear);
            if (classNum != null && !classNum.isEmpty()) ps.setString(idx++, classNum);
            if (isAttend != null) ps.setBoolean(idx++, isAttend);

            ResultSet rs = ps.executeQuery();
            SchoolDao schoolDao = new SchoolDao();
            School school = schoolDao.get(schoolCd);

            while (rs.next()) {
                list.add(mapStudent(rs, school));
            }
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return list;
    }

    /* ===============================
     * 学生登録（通常）
     * =============================== */
    public void insert(Student student) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                "INSERT INTO student "
              + "(no, name, ent_year, class_num, is_attend, school_cd) "
              + "VALUES (?, ?, ?, ?, ?, ?)"
            );
            ps.setString(1, student.getNo());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getEntYear());
            ps.setString(4, student.getClassNum());
            ps.setBoolean(5, student.isAttend());
            ps.setString(6, student.getSchool().getCd());
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    /* ===============================
     * 学生更新
     * =============================== */
    public void update(Student student) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                "UPDATE student SET name = ?, class_num = ?, is_attend = ? WHERE no = ?"
            );
            ps.setString(1, student.getName());
            ps.setString(2, student.getClassNum());
            ps.setBoolean(3, student.isAttend());
            ps.setString(4, student.getNo());
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    /* ===============================
     * CSV 重複チェック
     * =============================== */
    public List<String> findDuplicateNos(List<Student> students, String schoolCd)
            throws Exception {

        List<String> duplicates = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                "SELECT COUNT(*) FROM student WHERE no = ? AND school_cd = ?"
            );

            for (Student s : students) {
                ps.setString(1, s.getNo());
                ps.setString(2, schoolCd);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    duplicates.add(s.getNo());
                }
                rs.close();
            }
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return duplicates;
    }

    /* ===============================
     * CSV 一括登録（トランザクション）
     * =============================== */
    public void insertBatch(List<Student> students, String schoolCd)
            throws Exception {

        Connection conn = getConnection();
        PreparedStatement ps = null;

        try {
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(
                "INSERT INTO student "
              + "(no, name, ent_year, class_num, is_attend, school_cd) "
              + "VALUES (?, ?, ?, ?, ?, ?)"
            );

            for (Student s : students) {
                ps.setString(1, s.getNo());
                ps.setString(2, s.getName());
                ps.setInt(3, s.getEntYear());
                ps.setString(4, s.getClassNum());
                ps.setBoolean(5, s.isAttend());
                ps.setString(6, schoolCd);
                ps.addBatch();
            }

            ps.executeBatch();
            conn.commit();

        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}
