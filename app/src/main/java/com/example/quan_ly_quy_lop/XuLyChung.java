package com.example.quan_ly_quy_lop;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class XuLyChung {
    @NonNull
    // Chuẩn hóa tiền từ số sang có chấm Ví dụ: 1000 -> 1.000
    public static String Dot(String input) {

        Long val = Long.parseLong(input);

        DecimalFormat fm = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        fm.applyPattern("#,###,###,###");
        String fmS = fm.format(val);

        return fmS;
    }

    @NonNull
    // Tạo mảng gồm khoảng 5 năm trở về trước tính từ năm nay
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

    @NonNull
    public static ArrayList<String> CreateListMonth() {
        ArrayList<String> month = new ArrayList<>();
        month.add("All");

        for (int i = 1; i <= 12; i ++) {
            month.add(String.valueOf(i));
        }

        return month;
    }

    // Check xem EditText có nhập nội dung không
    // Nếu toàn khoảng trắng cũng tính là rỗng
    public static boolean ChuoiRong(String input) {
        return Pattern.matches("^\\s*$", input);
    }

    //Chuẩn hóa xâu
    public static String DeleteSpace(@NonNull String input) {
        String c = input.trim();
        if (Pattern.matches("^(\\S+\\s?)*\\S+$", c)) return c;
        else {
            c = c.replaceAll("\\s{2,}", " ");
            return c;
        }
    }
}
