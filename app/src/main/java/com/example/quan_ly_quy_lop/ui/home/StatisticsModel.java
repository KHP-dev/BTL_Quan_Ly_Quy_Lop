package com.example.quan_ly_quy_lop.ui.home;

public class StatisticsModel {
    String month;
    String income;
    String expense;
    String balance;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public StatisticsModel(String month, String income, String expense, String balance) {
        this.month = month;
        this.income = income;
        this.expense = expense;
        this.balance = balance;
    }

    public StatisticsModel(String income, String expense, String balance) {
        this.income = income;
        this.expense = expense;
        this.balance = balance;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
