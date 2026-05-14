package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Attendance;

public class AttendanceDao extends Dao {

    // 出欠登録 (INSERT or UPDATE)
    public void save(Attendance a) throws Exception {
        Connection con = getConnection();
        String checkSql = "SELECT COUNT(*) FROM ATTENDANCE WHERE STUDENT_NO=? AND DATE=?";
        try (PreparedStatement ps = con.prepareStatement(checkSql)) {
            ps.setString(1, a.getStudentNo());
            ps.setDate(2, Date.valueOf(a.getDate()));
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                String sql = "UPDATE ATTENDANCE SET STATUS=? WHERE STUDENT_NO=? AND DATE=?";
                try (PreparedStatement ps2 = con.prepareStatement(sql)) {
                    ps2.setString(1, a.getStatus());
                    ps2.setString(2, a.getStudentNo());
                    ps2.setDate(3, Date.valueOf(a.getDate()));
                    ps2.executeUpdate();
                }
            } else {
                String sql = "INSERT INTO ATTENDANCE VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps2 = con.prepareStatement(sql)) {
                    ps2.setString(1, a.getStudentNo());
                    ps2.setString(2, a.getSchoolCd());
                    ps2.setString(3, a.getClassNum());
                    ps2.setDate(4, Date.valueOf(a.getDate()));
                    ps2.setString(5, a.getStatus());
                    ps2.executeUpdate();
                }
            }
        } finally {
            con.close();
        }
    }

    // 日付・クラスで出欠一覧取得
    public List<Attendance> filter(String schoolCd, String classNum, LocalDate date) throws Exception {
        Connection con = getConnection();
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM ATTENDANCE WHERE SCHOOL_CD=? AND CLASS_NUM=? AND DATE=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, schoolCd);
            ps.setString(2, classNum);
            ps.setDate(3, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setStudentNo(rs.getString("STUDENT_NO"));
                a.setSchoolCd(rs.getString("SCHOOL_CD"));
                a.setClassNum(rs.getString("CLASS_NUM"));
                a.setDate(rs.getDate("DATE").toLocalDate());
                a.setStatus(rs.getString("STATUS"));
                list.add(a);
            }
        } finally {
            con.close();
        }
        return list;
    }

    // 学生別出欠一覧取得
    public List<Attendance> filterByStudent(String studentNo, String schoolCd) throws Exception {
        Connection con = getConnection();
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM ATTENDANCE WHERE STUDENT_NO=? AND SCHOOL_CD=? ORDER BY DATE";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, studentNo);
            ps.setString(2, schoolCd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setStudentNo(rs.getString("STUDENT_NO"));
                a.setSchoolCd(rs.getString("SCHOOL_CD"));
                a.setClassNum(rs.getString("CLASS_NUM"));
                a.setDate(rs.getDate("DATE").toLocalDate());
                a.setStatus(rs.getString("STATUS"));
                list.add(a);
            }
        } finally {
            con.close();
        }
        return list;
    }

    // 出欠率計算用
    public int countByStatus(String studentNo, String schoolCd, String status) throws Exception {
        Connection con = getConnection();
        int count = 0;
        String sql = "SELECT COUNT(*) FROM ATTENDANCE WHERE STUDENT_NO=? AND SCHOOL_CD=? AND STATUS=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, studentNo);
            ps.setString(2, schoolCd);
            ps.setString(3, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) count = rs.getInt(1);
        } finally {
            con.close();
        }
        return count;
    }

    // 合計出欠数
    public int countAll(String studentNo, String schoolCd) throws Exception {
        Connection con = getConnection();
        int count = 0;
        String sql = "SELECT COUNT(*) FROM ATTENDANCE WHERE STUDENT_NO=? AND SCHOOL_CD=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, studentNo);
            ps.setString(2, schoolCd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) count = rs.getInt(1);
        } finally {
            con.close();
        }
        return count;
    }
}