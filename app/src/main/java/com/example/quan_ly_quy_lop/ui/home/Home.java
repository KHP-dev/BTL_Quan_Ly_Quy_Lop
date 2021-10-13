package com.example.quan_ly_quy_lop.ui.home;

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
import com.example.quan_ly_quy_lop.databinding.FragmentHomeBinding;
import com.example.quan_ly_quy_lop.ui.expense.Expense;

import java.util.ArrayList;
import java.util.Calendar;

public class Home extends Fragment {

    private FragmentHomeBinding binding;
    Spinner spnYear;
    ListView lsvStatistics;
    ArrayList<String> year;
    ArrayList<StatisticsModel> data;
    StatisticsAdapter statisticsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spnYear = binding.spnYear;
        lsvStatistics = binding.lsvStatistics;

        year = new ArrayList<>();
        data = new ArrayList<>();

        statisticsAdapter = new StatisticsAdapter(getActivity(), data);
        lsvStatistics.setAdapter(statisticsAdapter);

        initSpinner();
        initListView();

        return root;
    }

    void initSpinner() {

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
        data.add(new StatisticsModel("Tháng 1", "15000", "25000", "-10000"));
        data.add(new StatisticsModel("Tháng 2", "100000", "20000", "15000"));
        data.add(new StatisticsModel("Tháng 3", "100000", "1000", "100"));

        lsvStatistics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}