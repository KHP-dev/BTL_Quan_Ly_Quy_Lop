package com.example.quan_ly_quy_lop;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;

public class XuLyChung {
    @NonNull
    // Chuẩn hóa tiền từ số sang có chấm Ví dụ: 1000 -> 1.000
    public static String Dot(String input) {
        String money = input;
        String dot = "";
        int nguyen = money.length() / 3;
        if (nguyen > 0) {

            if (money.length() % 3 != 0) {
                dot += money.substring(0, money.length() - nguyen * 3) + ".";
                money = money.substring(money.length() - nguyen * 3);
            }

            for (int so = 0; so < nguyen - 1; so++) {
                dot += money.substring(0, 3) + ".";
                money = money.substring(3);
            }

            dot += money;
        }
        else dot = money;
        return dot;
    }

    // Cắt xuống dòng tránh 1 dòng dài tràn dòng
    @NonNull
    public static String BreakLine(String input) {
        String content = input;
        String breakLine = "";
        while (content.length() > 20) {
            int pos = 20;
            if (content.charAt(20) == ' ') {
                breakLine += content.substring(0, 20) + "\n";
                content = content.substring(20 + 1);
            }
            else {
                pos = 20;
                while (pos < content.length()) {
                    if (content.charAt(pos) != ' ') pos--;
                    else {
                        breakLine += content.substring(0, pos) + "\n";
                        content = content.substring(pos + 1);
                        break;
                    };
                }
            }
            if (pos >= content.length()) break;
        }
        breakLine += content;
        return breakLine;
    }

    @NonNull
    public static ArrayList<String> CreateListYear () {
        ArrayList<String> year = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        year.add("All");

        for (int i = 4; i >= 0; i--) {
            year.add(String.valueOf(currentYear));
            currentYear--;
        }

        return year;
    }
}
