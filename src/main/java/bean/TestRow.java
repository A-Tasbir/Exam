package bean;

public class TestRow {
    private String studentNo;
    private String studentName;
    private int entYear;
    private String classNum;
    private int point;
    private String pointError;

    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }

    public String getPointError() { return pointError; }
    public void setPointError(String pointError) { this.pointError = pointError; }
}