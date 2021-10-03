package com.example.quan_ly_quy_lop.ui.expense;

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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


public class AddExpense extends AppCompatActivity {

    EditText edtContent;
    EditText edtDate;
    EditText edtMoney;

    AlertDialog alertDialog;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        edtContent = findViewById(R.id.edtContentExpense);
        edtDate = findViewById(R.id.edtDateExpense);
        edtMoney = findViewById(R.id.edtMoneyExpense);

        alertDialog = new AlertDialog.Builder(AddExpense.this).create();

        init();
    }

    void init() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();

        edtDate.setText(dtf.format(now));

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                edtDate.setText(day + "/" + month + "/" + year);
            }
        };

        edtMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edtMoney.removeTextChangedListener(this);

                try {
                    String ori = s.toString();
                    if (ori.contains(",")) {
                        ori = ori.replaceAll(",", "");
                    }

                    String fmS = XuLyChung.Dot(ori);

                    edtMoney.setText(fmS);
                    edtMoney.setSelection(edtMoney.getText().length());
                } catch (Exception e) {

                }

                edtMoney.addTextChangedListener(this);
            }
        });

        alertDialog.setTitle("Chú ý");
    }

    public void Done(View view) {
        if (ChuoiRong(edtContent.getText().toString())) {
            ThongBaoDuoi("Không được bỏ trống nội dung");
        }
        else if (ChuoiRong(edtMoney.getText().toString())) {
            ThongBaoDuoi("Không được bỏ trống tiền");
        }
        else {
            alertDialog.setMessage("Bạn có chắc chắn muốn thêm không?");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String chuanHoa = DeleteSpace(edtContent.getText().toString());
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

    public void Cancel(View view) {
        if (!ChuoiRong(edtContent.getText().toString()) || !ChuoiRong(edtMoney.getText().toString())) {
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

    public void PickDateExpense(View view) {
        LocalDate now = LocalDate.now();
        new DatePickerDialog(AddExpense.this, date, now.getYear(), now.getMonthValue(), now.getDayOfMonth()).show();
    }

    void ThongBaoDuoi(String input) {
        Toast.makeText(AddExpense.this, input, Toast.LENGTH_SHORT).show();
    }
}