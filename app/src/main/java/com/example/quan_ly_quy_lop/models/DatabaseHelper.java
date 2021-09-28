package com.example.quan_ly_quy_lop.models;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DatabaseHelper extends SQLiteOpenHelper {
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

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_name, null, DB_version);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DatabaseHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);
        sqLiteDatabase.execSQL(CREATE_TABLE_SESSION);
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);
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
}
