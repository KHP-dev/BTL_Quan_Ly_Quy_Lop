package com.example.quan_ly_quy_lop.ui.expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_quy_lop.databinding.FragmentExpenseBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Expense extends Fragment {

    private FragmentExpenseBinding binding;

    FloatingActionButton fab;
    ListView lsvExpense;

    ArrayList<ExpenseModel> expenseModels;
    ExpenseAdapter expenseAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentExpenseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        lsvExpense = binding.lsvExpense;
        fab = binding.fab;

        expenseModels = new ArrayList<>();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        initListView();

        return root;
    }

    void initListView() {

        expenseModels.add(new ExpenseModel("23/06/2000", "egrg rgreg regrg regrg greg regr ẻgrer regregr", "100000"));
        expenseAdapter = new ExpenseAdapter(getActivity(), expenseModels);
        lsvExpense.setAdapter(expenseAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}