package io.github.akueisara.reportcard;

public class ReportCard {
    private int mStudentID;
    private String mStudentName;
    private String mCourse;
    private int mGrade;
    private String mSemester;

    public ReportCard(int studentID, String studentName, String course, int grade, String semester) {
        this.mStudentID = studentID;
        this.mStudentName = studentName;
        this.mCourse = course;
        this.mGrade = grade;
        this.mSemester = semester;
    }

    public int getStudentID() {
        return mStudentID;
    }

    public void setStudentID(int id) {
        mStudentID = id;
    }

    public String getStudentName() {
        return mStudentName;
    }

    public void setStudentName(String name) {
        mStudentName = name;
    }

    public String getCourse() {
        return mCourse;
    }

    public void setCourse(String course) {
        this.mCourse = course;
    }

    public int getGrade() {
        return mGrade;
    }

    public void setGrade(int grade) {
        this.mGrade = grade;
    }

    public String getSemester() {
        return mSemester;
    }

    public void setSemester(String semester) {
        this.mSemester = semester;
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
                "studentID=" + mStudentID +
                ", studentName='" + mStudentName + '\'' +
                ", course='" + mCourse + '\'' +
                ", grade=" + mGrade +
                ", semester='" + mSemester + '\'' +
                '}';
    }
}
