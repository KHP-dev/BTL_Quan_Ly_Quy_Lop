package com.example.quan_ly_quy_lop.models;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.quan_ly_quy_lop.logUtil.LogUtil;
import com.example.quan_ly_quy_lop.ui.home.HomeViewModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";
    private static final String DB_name = "MyDataBase.db";
    private static final int DB_version = 1;
    // table name
    private static final String TABLE_STUDENT = "student";
    private static final String TABLE_FUND = "fund";
    private static final String TABLE_EXPENSE = "expense";
    private static final String TABLE_SESSION = "session";
    private static final String TABLE_SESSION_DETAIL = "sessionDetail";
    // col share
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE_CREATE = "date_create";
    private static final String KEY_FUND_ID = "_id_fund";
    // table student
    private static final String KEY_SV_CODE = "sv_code";
    // table Expense
    private static final String KEY_TITLE = "title";
    private static final String KEY_DES = "des";
    private static final String KEY_PRICE = "price";
    // table Session
    private static final String KEY_DEF_MONEY = "def_money";
    private static final String KEY_DATE_END = "date_end";
    private static final String KEY_DATE_BEGIN = "date_begin";
    // table Session Detail
    private static final String KEY_SESSION_ID = "_id_session";
    private static final String KEY_STUDENT_ID = "_id_student";
    // create table student
    private static final String CREATE_TABLE_STUDENT ="CREATE TABLE "+TABLE_STUDENT+" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_NAME+" TEXT,"+KEY_SV_CODE+" TEXT)";
    private static final String CREATE_TABLE_FUND = "CREATE TABLE "+TABLE_FUND+" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_NAME+" TEXT)";
    private static final String CREATE_TABLE_SESSION = "CREATE TABLE "+TABLE_SESSION+" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_FUND_ID+" INTEGER," +KEY_DEF_MONEY+" FLOAT, "+KEY_DATE_BEGIN+" DATE, "+KEY_DATE_END+" DATE)";
    private static final String CREATE_TABLE_SESSION_DETAIL = "CREATE TABLE "+TABLE_SESSION_DETAIL+" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_SESSION_ID+" INTEGER,"+KEY_STUDENT_ID+" INTEGER,"+KEY_DATE_CREATE+" DATE)";
    private static final String CREATE_TABLE_EXPENSE = "CREATE TABLE "+TABLE_EXPENSE+" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_FUND_ID+" INTEGER,"+KEY_TITLE+" TEXT, "+KEY_DES+" TEXT, "+KEY_PRICE+" FLOAT, "+KEY_DATE_CREATE+" DATE)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, DB_name, factory, DB_version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DatabaseHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, DB_name, DB_version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);
        sqLiteDatabase.execSQL(CREATE_TABLE_SESSION);
        sqLiteDatabase.execSQL(CREATE_TABLE_FUND);
        sqLiteDatabase.execSQL(CREATE_TABLE_SESSION_DETAIL);
        sqLiteDatabase.execSQL(CREATE_TABLE_EXPENSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_STUDENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_SESSION);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_STUDENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_SESSION_DETAIL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_EXPENSE);
        onCreate(sqLiteDatabase);
    }


    // them 1 sudent vao bang students
    public void insertStudent(@NonNull Student student) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        // dat cac gia tri cua student can them cho bien values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_SV_CODE, student.getSv_code());
        // them vao CSDL
        database.insert(TABLE_STUDENT, null, values);
    }

    // lay thong tin 1 student ra tu CSDL
    @SuppressLint("Range")
    public Student getStudent(int id) {
        // cap quyen doc CSDL cho bien database
        SQLiteDatabase database = this.getReadableDatabase();
        // gan cau lenh SQL vao bien selectQuerry
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT + " WHERE " + KEY_ID + " = " + id;
        // Log ra selectQuerry
        LogUtil.LogD(LOG, selectQuery);
        // doi tuong luu cac hang cua bang truy van
        Cursor c = database.rawQuery(selectQuery, null);
        // chuyen con tro den dong dau tien neu du lieu tra ve tu CSDL khong phai null
        if (c != null) {
            c.moveToFirst();
        }

        // dong goi thong tin vao 1 doi tuong student
        Student student = new Student();
        student.set_id(c.getInt(c.getColumnIndex(KEY_ID)));
        student.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        student.setSv_code(c.getString(c.getColumnIndex(KEY_SV_CODE)));

        // tra ve 1 student
        return student;
    }

    // lay thong tin tat ca student ra tu CSDL
    @SuppressLint("Range")
    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> arrStudent = new ArrayList<Student>();

        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuerry = "SELECT * FROM " + TABLE_STUDENT;

        LogUtil.LogD(LOG,selectQuerry);

        Cursor c = database.rawQuery(selectQuerry, null);

        if(c!=null) {
            c.moveToFirst();
            do{
                // dong goi thong tin vao 1 doi tuong student
                Student student = new Student();

                student.set_id(c.getInt(c.getColumnIndex(KEY_ID)));
                student.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                student.setSv_code(c.getString(c.getColumnIndex(KEY_SV_CODE)));

                arrStudent.add(student);
            } while(c.moveToNext()); // chuyen toi dong tiep theo
        }

        // tra ve danh sach cac student
        return arrStudent;
    }

    // sua thong tin 1 student co ID = _id
    public void updateStudent(@NonNull Student student, int _id) {
        // cap quyen ghi cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_SV_CODE, student.getSv_code());

        // sua student co ID = _id theo cac thong tin trong bien values
        database.update(TABLE_STUDENT, values, KEY_ID + " = " + _id, null);
    }

    // xoa student co ID = _id
    public void deleteStudent(int _id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_STUDENT, KEY_ID + " = " + _id, null);
    }

}
