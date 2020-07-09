package com.example.disa.user;

public class EntityUser {

    String NIM,email,fullname,username,faculty,skorquiz;

    public EntityUser(){

    }


    public EntityUser(String NIM, String email, String fullname, String username, String faculty, String skorquiz) {
        this.NIM = NIM;
        this.email = email;
        this.fullname = fullname;
        this.username = username;
        this.faculty = faculty;
        this.skorquiz = skorquiz;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getSkorquiz() {
        return skorquiz;
    }

    public void setSkorquiz(String skorquiz) {
        this.skorquiz = skorquiz;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
