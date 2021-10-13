package com.example.quan_ly_quy_lop.ui.income;

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

import com.example.quan_ly_quy_lop.R;
import com.example.quan_ly_quy_lop.XuLyChung;
import com.example.quan_ly_quy_lop.databinding.FragmentIncomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Income extends Fragment {

    private FragmentIncomeBinding binding;
    Spinner spnIncome;
    ListView lsvIncome;
    FloatingActionButton fabIncome;

    ArrayList<String> year;
    ArrayList<IncomeModel> data;
    IncomeAdapter incomeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentIncomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spnIncome = binding.spnIncome;
        lsvIncome = binding.lsvIncome;
        fabIncome = binding.fabIncome;

        year = new ArrayList<>();
        data = new ArrayList<>();

        incomeAdapter = new IncomeAdapter(getActivity(), data);
        lsvIncome.setAdapter(incomeAdapter);

        fabIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddIncome.class);

                startActivity(intent);
            }
        });

        lsvIncome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Detail_Income.class);

                startActivity(intent);
            }
        });

        initSpinner();
        initListView();

        return root;
    }

    void initSpinner() {
        year.addAll(XuLyChung.CreateListYear());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, year);
        spnIncome.setAdapter(adapter);

        spnIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void initListView() {
        data.add(new IncomeModel(R.drawable.tich_green, "Từ ngày 01/01/2021"));
        data.add(new IncomeModel(R.drawable.tich_red, "Từ ngày 01/02/2021"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}