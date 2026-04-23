package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestListStudentDao extends Dao {

    // 学生存在チェック（学生別検索用）
    public boolean exists(String studentNo) throws Exception {

        Connection con = getConnection();

        String sql = "SELECT COUNT(*) FROM TEST WHERE STUDENT_NO = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, studentNo);

        ResultSet rs = ps.executeQuery();
        rs.next();

        boolean exists = rs.getInt(1) > 0;

        rs.close();
        ps.close();
        con.close();

        return exists;
    }
}
