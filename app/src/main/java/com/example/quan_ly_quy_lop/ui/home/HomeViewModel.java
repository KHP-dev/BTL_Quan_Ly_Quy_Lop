package com.example.quan_ly_quy_lop.ui.home;

import android.content.Context;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quan_ly_quy_lop.MainActivity;
import com.example.quan_ly_quy_lop.R;
import com.example.quan_ly_quy_lop.models.DatabaseHelper;
import com.example.quan_ly_quy_lop.models.Student;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    Context context;
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("home");
    }

    public LiveData<String> getText() {
        return mText;
    }

}