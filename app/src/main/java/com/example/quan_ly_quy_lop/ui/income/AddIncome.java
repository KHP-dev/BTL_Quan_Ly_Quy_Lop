package com.example.quan_ly_quy_lop.ui.income;

import static com.example.quan_ly_quy_lop.XuLyChung.ChuoiRong;
import static com.example.quan_ly_quy_lop.XuLyChung.DeleteSpace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quan_ly_quy_lop.R;
import com.example.quan_ly_quy_lop.XuLyChung;
import com.example.quan_ly_quy_lop.ui.expense.AddExpense;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class AddIncome extends AppCompatActivity {

    EditText edtContentIncome;
    EditText edtIncome;
    EditText edtMoneyIncome;

    AlertDialog alertDialog;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        edtContentIncome = findViewById(R.id.edtContentIncome);
        edtIncome = findViewById(R.id.edtDateStartIncome);
        edtMoneyIncome = findViewById(R.id.edtMoneyAddIncome);

        alertDialog = new AlertDialog.Builder(AddIncome.this).create();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                edtIncome.setText(day + "/" + month + "/" + year);
            }
        };

        init();
    }

    void init() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        edtIncome.setText(dtf.format(now));

        edtMoneyIncome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                edtMoneyIncome.removeTextChangedListener(this);

                try {
                    String ori = s.toString();
                    if (ori.contains(",")) {
                        ori = ori.replaceAll(",", "");
                    }

                    String fmS = XuLyChung.Dot(ori);

                    edtMoneyIncome.setText(fmS);
                    edtMoneyIncome.setSelection(edtMoneyIncome.getText().length());
                } catch (Exception e) {

                }

                edtMoneyIncome.addTextChangedListener(this);

            }
        });
    }

    public void Cancel(View view) {
        if (!ChuoiRong(edtContentIncome.getText().toString()) || !ChuoiRong(edtMoneyIncome.getText().toString())) {
            alertDialog.setMessage("Bạn có muốn hủy không?");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.dismiss();
                    finish();
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        }
        else finish();
    }

    public void Done(View view) {
        if (ChuoiRong(edtContentIncome.getText().toString())) {
            ThongBaoDuoi("Không được bỏ trống nội dung");
        }
        else if (ChuoiRong(edtMoneyIncome.getText().toString())) {
            ThongBaoDuoi("Không được bỏ trống tiền");
        }
        else {
            alertDialog.setMessage("Bạn có chắc chắn muốn thêm không?");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String chuanHoa = DeleteSpace(edtContentIncome.getText().toString());
                    alertDialog.dismiss();
                    finish();
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        }
    }

    public void PickDateIncome(View view) {
        LocalDate now = LocalDate.now();
        new DatePickerDialog(AddIncome.this, date, now.getYear(), now.getMonthValue(), now.getDayOfMonth()).show();
    }

    void ThongBaoDuoi(String input) {
        Toast.makeText(AddIncome.this, input, Toast.LENGTH_SHORT).show();
    }
}