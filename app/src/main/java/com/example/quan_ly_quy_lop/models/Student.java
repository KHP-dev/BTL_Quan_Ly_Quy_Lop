package com.example.quan_ly_quy_lop.models;

public class Student {
    private int _id;
    private String name;
    private String sv_code;

    public Student(){
        super();
    }

    public Student(String name, String sv_code) {
        this.name = name;
        this.sv_code = sv_code;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSv_code() {
        return sv_code;
    }

    public void setSv_code(String sv_code) {
        this.sv_code = sv_code;
    }
}
