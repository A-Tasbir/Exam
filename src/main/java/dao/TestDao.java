package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.SubjectTestRow;
import bean.Test;

public class TestDao extends Dao {

    // ========= 既存メソッド（変更なし） =========

    // 学校・クラス・科目・回数で検索（科目別）
    public List<Test> filter(String schoolCd, String classNum, String subjectCd, int no) throws Exception {

        Connection con = getConnection();
        List<Test> list = new ArrayList<>();

        String sql = "select * from TEST where SCHOOL_CD = ? and CLASS_NUM = ? and SUBJECT_CD = ? and NO = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, schoolCd);
            ps.setString(2, classNum);
            ps.setString(3, subjectCd);
            ps.setInt(4, no);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Test t = new Test();
                t.setStudentNo(rs.getString("STUDENT_NO"));
                t.setSubjectCd(rs.getString("SUBJECT_CD"));
                t.setSchoolCd(rs.getString("SCHOOL_CD"));
                t.setNo(rs.getInt("NO"));
                t.setPoint(rs.getInt("POINT"));
                t.setClassNum(rs.getString("CLASS_NUM"));
                list.add(t);
            }
        } finally {
            con.close();
        }

        return list;
    }

    // INSERT or UPDATE（登録用）
    public void save(Test t) throws Exception {

        Connection con = getConnection();

        String checkSql = "select count(*) from TEST where STUDENT_NO=? and SUBJECT_CD=? and SCHOOL_CD=? and NO=?";
        try (PreparedStatement ps = con.prepareStatement(checkSql)) {

            ps.setString(1, t.getStudentNo());
            ps.setString(2, t.getSubjectCd());
            ps.setString(3, t.getSchoolCd());
            ps.setInt(4, t.getNo());

            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                // UPDATE
                String sql = "update TEST set POINT=? where STUDENT_NO=? and SUBJECT_CD=? and SCHOOL_CD=? and NO=?";
                try (PreparedStatement ps2 = con.prepareStatement(sql)) {
                    ps2.setInt(1, t.getPoint());
                    ps2.setString(2, t.getStudentNo());
                    ps2.setString(3, t.getSubjectCd());
                    ps2.setString(4, t.getSchoolCd());
                    ps2.setInt(5, t.getNo());
                    ps2.executeUpdate();
                }
            } else {
                // INSERT
                String sql = "insert into TEST values (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps2 = con.prepareStatement(sql)) {
                    ps2.setString(1, t.getStudentNo());
                    ps2.setString(2, t.getSubjectCd());
                    ps2.setString(3, t.getSchoolCd());
                    ps2.setInt(4, t.getNo());
                    ps2.setInt(5, t.getPoint());
                    ps2.setString(6, t.getClassNum());
                    ps2.executeUpdate();
                }
            }
        } finally {
            con.close();
        }
    }

    // ========= ✅ 追加メソッド（学生別検索用） =========

    // 学籍番号で検索（学生別成績参照）
    public List<Test> findByStudentNo(String studentNo) throws Exception {

        Connection con = getConnection();
        List<Test> list = new ArrayList<>();

        String sql =
            "SELECT t.STUDENT_NO, t.SUBJECT_CD, s.NAME AS SUBJECT_NAME, " +
            "t.SCHOOL_CD, t.NO, t.POINT, t.CLASS_NUM " +
            "FROM TEST t " +
            "JOIN SUBJECT s ON t.SUBJECT_CD = s.CD " +
            "WHERE t.STUDENT_NO = ? " +
            "ORDER BY t.SUBJECT_CD, t.NO";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentNo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Test t = new Test();
                t.setStudentNo(rs.getString("STUDENT_NO"));
                t.setSubjectCd(rs.getString("SUBJECT_CD"));
                t.setSubjectName(rs.getString("SUBJECT_NAME")); // ✅ ONLY ADDITION
                t.setSchoolCd(rs.getString("SCHOOL_CD"));
                t.setNo(rs.getInt("NO"));
                t.setPoint(rs.getInt("POINT"));
                t.setClassNum(rs.getString("CLASS_NUM"));
                list.add(t);
            }
        } finally {
            con.close();
        }
		return list;
    }
    public List<SubjectTestRow> getSubjectTestList(
            String schoolCd,
            String entYear,
            String classNum,
            String subjectCd)
            throws Exception {

        String sql =
            "SELECT s.ENT_YEAR, s.CLASS_NUM, s.STUDENT_NO, s.NAME, " +
            "MAX(CASE WHEN t.NO = 1 THEN t.POINT END) AS POINT1, " +
            "MAX(CASE WHEN t.NO = 2 THEN t.POINT END) AS POINT2 " +
            "FROM STUDENT s " +
            "LEFT JOIN TEST t " +
            "ON s.STUDENT_NO = t.STUDENT_NO " +
            "AND t.SUBJECT_CD = ? " +
            "WHERE s.SCHOOL_CD = ? " +   // ★追加
            "AND s.ENT_YEAR = ? " +
            "AND s.CLASS_NUM = ? " +
            "GROUP BY s.ENT_YEAR, s.CLASS_NUM, s.STUDENT_NO, s.NAME " +
            "ORDER BY s.STUDENT_NO";

        List<SubjectTestRow> list = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subjectCd);
            ps.setString(2, schoolCd);
            ps.setString(3, entYear);
            ps.setString(4, classNum);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SubjectTestRow row = new SubjectTestRow();

                // ★ Stringで統一
                row.setEntYear(rs.getString("ENT_YEAR"));
                row.setClassNum(rs.getString("CLASS_NUM"));
                row.setStudentNo(rs.getString("STUDENT_NO"));
                row.setStudentName(rs.getString("NAME"));

                // ★ メソッド名合わせる
                row.setScore1((Integer) rs.getObject("POINT1"));
                row.setScore2((Integer) rs.getObject("POINT2"));

                list.add(row);
            }
        }
        return list;
    }
}
