package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.SubjectTestRow;

public class TestListSubjectDao extends Dao {

    public List<SubjectTestRow> filter(
        String schoolCd,
        String entYear,
        String classNum,
        String subjectCd
    ) throws Exception {

        List<SubjectTestRow> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
            "SELECT " +
            " s.ent_year, " +
            " s.class_num, " +
            " s.no, " +
            " s.name, " +
            " t1.point AS score1, " +
            " t2.point AS score2 " +
            "FROM student s " +

            // 1回目
            "LEFT JOIN test t1 " +
            " ON s.no = t1.student_no " +
            " AND t1.no = 1 " +
            " AND t1.subject_cd = ? " +

            // 2回目
            "LEFT JOIN test t2 " +
            " ON s.no = t2.student_no " +
            " AND t2.no = 2 " +
            " AND t2.subject_cd = ? " +

            "WHERE s.school_cd = ? " +
            "AND s.ent_year = ? " +
            "AND s.class_num = ? " +

            "ORDER BY s.no";

        PreparedStatement st = con.prepareStatement(sql);

        // パラメータ順
        st.setString(1, subjectCd);
        st.setString(2, subjectCd);
        st.setString(3, schoolCd);
        st.setString(4, entYear);
        st.setString(5, classNum);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            SubjectTestRow row = new SubjectTestRow();

            row.setEntYear(rs.getString("ent_year"));
            row.setClassNum(rs.getString("class_num"));
            row.setStudentNo(rs.getString("no"));
            row.setStudentName(rs.getString("name"));

            // NULL安全
            row.setScore1(rs.getObject("score1") != null ? rs.getInt("score1") : null);
            row.setScore2(rs.getObject("score2") != null ? rs.getInt("score2") : null);

            list.add(row);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}