package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    public List<Subject> filter(String schoolCd) throws Exception {
        Connection con = getConnection();
        List<Subject> list = new ArrayList<>();
        String sql = "select * from SUBJECT where SCHOOL_CD = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, schoolCd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSchoolCd(rs.getString("SCHOOL_CD"));
                s.setCd(rs.getString("CD"));
                s.setName(rs.getString("NAME"));
                list.add(s);
            }
        } finally {
            con.close();
        }
        return list;
    }

    public Subject get(String schoolCd, String cd) throws Exception {
        Connection con = getConnection();
        Subject s = null;
        String sql = "select * from SUBJECT where SCHOOL_CD = ? and CD = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, schoolCd);
            ps.setString(2, cd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new Subject();
                s.setSchoolCd(rs.getString("SCHOOL_CD"));
                s.setCd(rs.getString("CD"));
                s.setName(rs.getString("NAME"));
            }
        } finally {
            con.close();
        }
        return s;
    }
}