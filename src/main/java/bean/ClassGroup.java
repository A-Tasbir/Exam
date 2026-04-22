package bean;

import java.io.Serializable;
import java.util.List;

public class ClassGroup implements Serializable {

    private String classNum;
    private List<Student> students;

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}