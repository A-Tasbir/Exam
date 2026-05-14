package bean;

import java.io.Serializable;
import java.time.LocalDate;

public class Attendance implements Serializable {

    private String studentNo;
    private String schoolCd;
    private String classNum;
    private LocalDate date;
    private String status; // O=出席, X=欠席, T=遅刻

    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }

    public String getSchoolCd() { return schoolCd; }
    public void setSchoolCd(String schoolCd) { this.schoolCd = schoolCd; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}