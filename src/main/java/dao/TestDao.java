package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {

    // 学校・クラス・科目・回数で検索
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

    // INSERT or UPDATE
    public void save(Test t) throws Exception {
        Connection con = getConnection();
        // 既存チェック
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
}