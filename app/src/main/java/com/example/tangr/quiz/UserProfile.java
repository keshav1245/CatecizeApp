package com.example.tangr.quiz;


public class UserProfile {


    public  String mail,fname,lname,rollnos,stream,username;
    double marks;
    int attemptofquiz;

    public UserProfile(){}
    public UserProfile( String fname, String lname, String rollnos, String stream, String username) {

        this.fname = fname;
        this.lname = lname;
        this.rollnos = rollnos;
        this.stream = stream;
        this.username = username;

    }



    public UserProfile(String mail, String fname, String lname, String rollnos, String stream, String username, double marks, int attemptofquiz) {
        this.mail = mail;
        this.fname = fname;
        this.lname = lname;
        this.rollnos = rollnos;
        this.stream = stream;
        this.username = username;
        this.marks = marks;
        this.attemptofquiz = attemptofquiz;


    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

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

    public String getRollnos() {
        return rollnos;
    }

    public void setRollnos(String rollnos) {
        this.rollnos = rollnos;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
    public int getAttemptofquiz() {
        return attemptofquiz;
    }

    public void setAttemptofquiz(int attemptofquiz) {
        this.attemptofquiz = attemptofquiz;
    }

}
