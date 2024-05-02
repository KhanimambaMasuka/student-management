package com.learning;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends MutableEntity {

    @Column(name = "student_no", nullable = false, unique = true)
    private String studentNo;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "cell_no", nullable = false)
    private String cellNo;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "current_score")
    private Integer currentScore;

    @Column(name = "average_score")
    private Integer averageScore;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    public Integer getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }
}
