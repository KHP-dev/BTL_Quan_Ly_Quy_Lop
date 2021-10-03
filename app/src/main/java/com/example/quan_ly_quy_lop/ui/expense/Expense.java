package com.example.quan_ly_quy_lop.ui.expense;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_quy_lop.XuLyChung;
import com.example.quan_ly_quy_lop.databinding.FragmentExpenseBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Expense extends Fragment {

    private FragmentExpenseBinding binding;

    FloatingActionButton fabExpense;
    ListView lsvExpense;
    Spinner spnYear;

    ArrayList<String> year;
    ArrayList<ExpenseModel> data;
    ExpenseAdapter expenseAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentExpenseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        lsvExpense = binding.lsvExpense;
        fabExpense = binding.fabExpense;
        spnYear = binding.spnExpense;

        data = new ArrayList<>();
        year = new ArrayList<>();

        expenseAdapter = new ExpenseAdapter(getActivity(), data);
        lsvExpense.setAdapter(expenseAdapter);

        fabExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddExpense.class);

                startActivity(intent);
            }
        });

        initSpinnerYear();
        initListView();

        return root;
    }

    void initSpinnerYear() {
        year.addAll(XuLyChung.CreateListYear());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, year);
        spnYear.setAdapter(adapter);

        spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void initListView() {
        data.add(new ExpenseModel("23/06/2000", "egrg rgreg regrg regrg greg regr ẻgrer regregr", "100000"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}