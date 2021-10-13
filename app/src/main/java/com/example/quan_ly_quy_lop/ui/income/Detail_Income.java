package com.example.quan_ly_quy_lop.ui.income;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.quan_ly_quy_lop.R;

import java.util.ArrayList;

public class Detail_Income extends AppCompatActivity {

    TextView txtMoney;
    TextView txtDaThu;
    TextView txtChuaThu;
    TableLayout tblListPeople;

    ArrayList<AddIncomeModel> data;
    AddIncomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_income);

        txtMoney = findViewById(R.id.txtMoneyPP);
        txtDaThu = findViewById(R.id.txtDaThu);
        txtChuaThu = findViewById(R.id.txtChuaThu);
        tblListPeople = findViewById(R.id.tblListPeople);

        data = new ArrayList<>();
        adapter = new AddIncomeAdapter(Detail_Income.this, data);

        initRow();
        init();
    }

    void init() {
        txtMoney.setText("Số tiền thu mỗi người: 50,000 VNĐ");
        txtDaThu.setText("Đã thu: 5 người");
        txtChuaThu.setText("Chưa thu: 10 người");

        for (int i = 0; i < adapter.getCount(); i++) {
            tblListPeople.addView(adapter.getView(i, null, tblListPeople));
        }
    }

    void initRow() {
        data.add(new AddIncomeModel("1", "Kiều Hoàng Phúc", true));
        data.add(new AddIncomeModel("2", "Phạm Hoàng Kim", true));
        data.add(new AddIncomeModel("3", "Cao Hải Nam", false));
        data.add(new AddIncomeModel("4", "Phạm Hoàng Kim", true));
        data.add(new AddIncomeModel("5", "Cao Hải Nam", false));
        data.add(new AddIncomeModel("5", "Cao Hải Nam", false));
        data.add(new AddIncomeModel("6", "Kiều Hoàng Phúc", true));
        data.add(new AddIncomeModel("6", "Kiều Hoàng Phúc", true));
        data.add(new AddIncomeModel("7", "Cao Hải Nam", false));
        data.add(new AddIncomeModel("7", "Cao Hải Nam", false));
        data.add(new AddIncomeModel("8", "Kiều Hoàng Phúc", true));
        data.add(new AddIncomeModel("8", "Kiều Hoàng Phúc", true));
        data.add(new AddIncomeModel("9", "Phạm Hoàng Kim", true));
        data.add(new AddIncomeModel("9", "Phạm Hoàng Kim", true));
    }

    public void Back(View view) {
        finish();
    }

    public void Done(View view) {
        finish();
    }
}