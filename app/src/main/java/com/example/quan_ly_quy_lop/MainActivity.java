package com.example.quan_ly_quy_lop;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.example.quan_ly_quy_lop.logUtil.LogUtil;
import com.example.quan_ly_quy_lop.models.DatabaseHelper;
import com.example.quan_ly_quy_lop.models.Student;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quan_ly_quy_lop.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        // test data base
        dbHelper = new DatabaseHelper(MainActivity.this);
        Student student = new Student("Manh Hung", "Ha Dong");
        dbHelper.insertStudent(student);
        student = new Student("Tuan Anh", "Cau Giay");
        dbHelper.insertStudent(student);
        ArrayList<Student> arrStudent = dbHelper.getAllStudent();
        LogUtil.LogD("dataStudent",arrStudent.get(0).getName());


    }


}