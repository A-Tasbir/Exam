package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassGroup;
import bean.ClassNum;
import bean.Student;

public class ClassNumDao extends Dao {

    /* =====================================================
     * CLASS LIST (StudentList用)
     * ===================================================== */
    public List<String> getClassNumsBySchool(String schoolCd) throws Exception {

        List<String> list = new ArrayList<>();

        String sql =
            "SELECT class_num FROM class_num " +
            "WHERE school_cd = ? ORDER BY class_num";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, schoolCd);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("class_num"));
            }
        }

        return list;
    }

    /* =====================================================
     * CLASS + STUDENTS (ClassList用)
     * ===================================================== */
    public List<ClassGroup> getClassesWithStudents(String schoolCd) throws Exception {

        List<ClassGroup> result = new ArrayList<>();

        Connection con = getConnection();

        String sql =
            "SELECT c.class_num, s.no, s.name, s.ent_year, s.is_attend " +
            "FROM class_num c " +
            "LEFT JOIN student s " +
            "ON c.class_num = s.class_num " +
            "AND c.school_cd = s.school_cd " +
            "WHERE c.school_cd = ? " +
            "ORDER BY c.class_num, s.no";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, schoolCd);

        ResultSet rs = ps.executeQuery();

        String currentClass = null;
        ClassGroup group = null;

        while (rs.next()) {

            String classNum = rs.getString("class_num");

            if (currentClass == null || !classNum.equals(currentClass)) {

                group = new ClassGroup();
                group.setClassNum(classNum);
                group.setStudents(new ArrayList<>());

                result.add(group);

                currentClass = classNum;
            }

            String studentNo = rs.getString("no");

            if (studentNo != null) {

                Student s = new Student();
                s.setNo(studentNo);
                s.setName(rs.getString("name"));
                s.setEntYear(rs.getInt("ent_year"));
                s.setAttend(rs.getBoolean("is_attend"));

                group.getStudents().add(s);
            }
        }

        rs.close();
        ps.close();
        con.close();

        return result;
    }
    
    public List<String> filter(String schoolCd) throws Exception {
        return getClassNumsBySchool(schoolCd);
    }

    /* =====================================================
     * EXISTS CHECK
     * ===================================================== */
    public boolean exists(String classNum, String schoolCd) throws Exception {

        String sql =
            "SELECT COUNT(*) FROM class_num " +
            "WHERE class_num = ? AND school_cd = ?";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, classNum);
            ps.setString(2, schoolCd);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }

        return false;
    }

    /* =====================================================
     * INSERT CLASS
     * ===================================================== */
    public void insert(ClassNum c) throws Exception {

        String sql =
            "INSERT INTO class_num (class_num, school_cd) VALUES (?, ?)";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, c.getClassNum());
            ps.setString(2, c.getSchoolCd());

            ps.executeUpdate();
        }
    }

    /* =====================================================
     * UPDATE CLASS (AND SYNC STUDENTS)
     * ===================================================== */
    public void update(String oldClassNum, String newClassNum, String schoolCd) throws Exception {

        Connection con = getConnection();

        try {
            con.setAutoCommit(false);

            // 1. update class table
            String sql1 =
                "UPDATE class_num SET class_num = ? " +
                "WHERE class_num = ? AND school_cd = ?";

            try (PreparedStatement ps = con.prepareStatement(sql1)) {
                ps.setString(1, newClassNum);
                ps.setString(2, oldClassNum);
                ps.setString(3, schoolCd);
                ps.executeUpdate();
            }

            // 2. update students in that class
            String sql2 =
                "UPDATE student SET class_num = ? " +
                "WHERE class_num = ? AND school_cd = ?";

            try (PreparedStatement ps = con.prepareStatement(sql2)) {
                ps.setString(1, newClassNum);
                ps.setString(2, oldClassNum);
                ps.setString(3, schoolCd);
                ps.executeUpdate();
            }

            con.commit();

        } catch (Exception e) {
            con.rollback();
            throw e;

        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }
}