package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {

    public Teacher login(String id, String password) throws Exception {

        Teacher teacher = get(id);

        if (teacher == null || teacher.getPassword() == null) {
            return null;
        }

        if (!teacher.getPassword().equals(password)) {
            return null;
        }

        return teacher;
    }

    public Teacher get(String id) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(
                "select id, password, name, school_cd, is_admin from teacher where id=?"
            );

            statement.setString(1, id);
            rs = statement.executeQuery();

            if (!rs.next()) return null;

            Teacher t = new Teacher();
            t.setId(rs.getString("id"));
            t.setPassword(rs.getString("password"));
            t.setName(rs.getString("name"));
            t.setAdmin(rs.getBoolean("is_admin"));

            School s = new School();
            s.setCd(rs.getString("school_cd"));
            t.setSchool(s);

            return t;

        } finally {
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    public List<Teacher> findBySchool(String schoolCd) throws Exception {

        List<Teacher> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(
                "select id, password, name, school_cd, is_admin from teacher where school_cd=?"
            );

            statement.setString(1, schoolCd);
            rs = statement.executeQuery();

            while (rs.next()) {

                Teacher t = new Teacher();
                t.setId(rs.getString("id"));
                t.setPassword(rs.getString("password"));
                t.setName(rs.getString("name"));
                t.setAdmin(rs.getBoolean("is_admin"));

                School s = new School();
                s.setCd(rs.getString("school_cd"));
                t.setSchool(s);

                list.add(t);
            }

        } finally {
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    public void update(Teacher t) throws Exception {

        // =========================
        // SAFETY CHECK (ADDED ONLY)
        // =========================
        if (t == null || t.getSchool() == null || t.getSchool().getCd() == null) {
            throw new RuntimeException("Teacher or School is null in update()");
        }

        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "update teacher set password=?, name=?, school_cd=?, is_admin=? where id=?"
            );

            statement.setString(1, t.getPassword());
            statement.setString(2, t.getName());
            statement.setString(3, t.getSchool().getCd());
            statement.setBoolean(4, t.isAdmin());
            statement.setString(5, t.getId());

            statement.executeUpdate();

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    public void insert(Teacher t) throws Exception {

        // =========================
        // SAFETY CHECK (ADDED ONLY)
        // =========================
        if (t == null || t.getSchool() == null || t.getSchool().getCd() == null) {
            throw new RuntimeException("Teacher or School is null in insert()");
        }

        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "insert into teacher (id, password, name, school_cd, is_admin) values (?, ?, ?, ?, ?)"
            );

            statement.setString(1, t.getId());
            statement.setString(2, t.getPassword());
            statement.setString(3, t.getName());
            statement.setString(4, t.getSchool().getCd());
            statement.setBoolean(5, t.isAdmin());

            statement.executeUpdate();

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    // =========================
    // ADDED (REQUIRED BY YOUR ACTION)
    // =========================
    public boolean exists(String id) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(
                "select id from teacher where id=?"
            );

            statement.setString(1, id);
            rs = statement.executeQuery();

            return rs.next();

        } finally {
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
}