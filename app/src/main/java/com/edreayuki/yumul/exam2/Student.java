package com.edreayuki.yumul.exam2;

public class Student {
    String fname, lname;
    Long e1, e2, ave;

    public Student(String fname, String lname, Long e1, Long e2, Long ave) {
        this.fname = fname;
        this.lname = lname;
        this.e1 = e1;
        this.e2 = e2;
        this.ave = ave;
    }

    public Student () {}

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Long getE1() {
        return e1;
    }

    public void setE1(Long e1) {
        this.e1 = e1;
    }

    public Long getE2() {
        return e2;
    }

    public void setE2(Long e2) {
        this.e2 = e2;
    }

    public Long getAve() {
        return ave;
    }

    public void setAve(Long ave) {
        this.ave = ave;
    }
}
