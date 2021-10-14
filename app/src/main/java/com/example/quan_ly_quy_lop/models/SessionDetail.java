package com.example.quan_ly_quy_lop.models;


import java.util.Date;

public class SessionDetail {
    private int _id;
    private Student student;
    private Session session;
    private Date date_create;

    public SessionDetail(){
        super();
    }

    public SessionDetail( Student student, Session session, Date date_create) {
        this.student = student;
        this.session = session;
        this.date_create = date_create;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }
}
