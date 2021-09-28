package com.example.quan_ly_quy_lop.models;

public class Fund {
    int _id;
    String name;

    public Fund() {
       super();
    }

    public Fund(int _id, String name) {
        this._id = _id;
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
