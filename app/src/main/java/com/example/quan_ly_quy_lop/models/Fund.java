package com.example.quan_ly_quy_lop.models;

public class Fund {
    private int _id;
    private String name;

    public Fund() {
       super();
    }

    public Fund( String name) {
        this.name = name;
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
}
