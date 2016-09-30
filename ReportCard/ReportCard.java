package io.github.akueisara.reportcard;

/**
 * Created by akueisara on 9/28/2016.
 */
public class ReportCard {
    private int studentID;
    private String studentName;
    private String course;
    private int grade;
    private String semester;

    public ReportCard(int studentID, String studentName, String course, int grade, String semester) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.course = course;
        this.grade = grade;
        this.semester = semester;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int id) {
        studentID = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String name) {
        studentName = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getLetterGrade(int grade){

        String letterGrade;

        if(grade > 79 && grade < 101){
            letterGrade = "A";
        } else if (grade > 69 && grade < 50){
            letterGrade = "B";
        } else if (grade > 59 && grade < 70){
            letterGrade = "C";
        }  else if (grade > 49 && grade < 60){
            letterGrade = "D";
        } else {
            letterGrade = "F";
        }

        return letterGrade;
    }

    @Override
    public String toString() {
        return "ReportCard{" +
                "studentID=" + studentID +
                ", studentName='" + studentName + '\'' +
                ", course='" + course + '\'' +
                ", grade=" + grade +
                ", semester='" + semester + '\'' +
                '}';
    }
}
