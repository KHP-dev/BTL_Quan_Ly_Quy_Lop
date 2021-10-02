package com.example.quan_ly_quy_lop.ui.income;

public class IncomeModel {
    int idImg;
    String content;

    public IncomeModel(int idImg, String content) {
        this.idImg = idImg;
        this.content = content;
    }

    public int getIdImg() {
        return idImg;
    }

    public void setIdImg(int idImg) {
        this.idImg = idImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
