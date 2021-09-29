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

import java.sql.Date;
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
    private static final String CREATE_TABLE_SESSION = "CREATE TABLE "+TABLE_SESSION+" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_FUND_ID+" INTEGER," +KEY_NAME+" TEXT," +KEY_DEF_MONEY+" FLOAT, "+KEY_DATE_BEGIN+" DATE, "+KEY_DATE_END+" DATE)";
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
    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);
        sqLiteDatabase.execSQL(CREATE_TABLE_SESSION);
        sqLiteDatabase.execSQL(CREATE_TABLE_FUND);
        sqLiteDatabase.execSQL(CREATE_TABLE_SESSION_DETAIL);
        sqLiteDatabase.execSQL(CREATE_TABLE_EXPENSE);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_STUDENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_SESSION);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_STUDENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_SESSION_DETAIL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_EXPENSE);
        onCreate(sqLiteDatabase);
    }



    @SuppressLint("Range")
    public float getMoneySession(int id){
        int count=0;
        float def_money=0;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuerry = "SELECT * FROM " + TABLE_SESSION_DETAIL +" WHERE "+ KEY_SESSION_ID +"="+id;
        String selectQuerry1 = "SELECT * FROM " + TABLE_SESSION +" WHERE "+ KEY_ID +"="+id;
        LogUtil.LogD(LOG,selectQuerry);

        Cursor c = database.rawQuery(selectQuerry, null);
        Cursor c1 = database.rawQuery(selectQuerry1, null);

        if (c1 != null) def_money = c1.getFloat(c1.getColumnIndex(KEY_DEF_MONEY));
        if (c != null) count = c.getCount();

        return def_money*count;
    }

    @SuppressLint("Range")
    public float getTotalMoney(){
        float total_money=0;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuerry = "SELECT * FROM " + TABLE_SESSION ;

        LogUtil.LogD(LOG,selectQuerry);

        Cursor c = database.rawQuery(selectQuerry, null);
        if(c!=null) {
            c.moveToFirst();
            do{
                total_money += getMoneySession(c.getInt(c.getColumnIndex(KEY_ID)));
            } while(c.moveToNext()); // chuyen toi dong tiep theo
        }
        return  total_money;
    }

    @SuppressLint("Range")
    public float getSurplus(){
        float surplus=0;
        float total_expense=0;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuerry = "SELECT * FROM " + TABLE_EXPENSE ;

        LogUtil.LogD(LOG,selectQuerry);

        Cursor c = database.rawQuery(selectQuerry, null);
        if(c!=null) {
            c.moveToFirst();
            do{
                total_expense += c.getFloat(c.getColumnIndex(KEY_PRICE));
            } while(c.moveToNext()); // chuyen toi dong tiep theo
        }

        return  surplus - total_expense ;
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

    // them 1 expense vao bang expense
    public void insertExpense(@NonNull Expense expense) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        // dat cac gia tri cua expense can them cho bien values
        ContentValues values = new ContentValues();
        values.put(KEY_FUND_ID, expense.getFund().get_id());
        values.put(KEY_TITLE, expense.getTitle());
        values.put(KEY_DES, expense.getDes());
        values.put(KEY_PRICE, expense.getPrice());
        values.put(KEY_DATE_CREATE, expense.getDate_create().toString());
        // them vao CSDL
        database.insert(TABLE_EXPENSE, null, values);
    }

    // them 1 session vao bang sesion
    public void insertSession(@NonNull Session session) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        // dat cac gia tri cua expense va fund_id can them cho bien values
        ContentValues values = new ContentValues();
        values.put(KEY_FUND_ID, session.getFund().get_id());
        values.put(KEY_NAME, session.getName());
        values.put(KEY_DEF_MONEY, session.getDef_money());
        values.put(KEY_DATE_BEGIN, session.getDate_begin().toString());
        values.put(KEY_DATE_END, session.getDate_end().toString());

        // them vao CSDL
        database.insert(TABLE_SESSION, null, values);
    }

    // them 1 session detail vao bang sesionDetail
    public void insertSessionDetail(@NonNull SessionDetail sessionDetail) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        // dat cac gia tri cua sessionDetail can them cho bien values
        ContentValues values = new ContentValues();
        values.put(KEY_SESSION_ID,sessionDetail.getSession().get_id());
        values.put(KEY_STUDENT_ID,sessionDetail.getStudent().get_id());
        values.put(KEY_DATE_CREATE,sessionDetail.getDate_create().toString());

        // them vao CSDL
        database.insert(TABLE_SESSION_DETAIL, null, values);
    }

    // them 1 fund vao bang fund
    public void insertFund(@NonNull Fund fund) {
        // cap quyen ghi CSDL cho bien database
        SQLiteDatabase database = this.getWritableDatabase();
        // dat cac gia tri cua fund can them cho bien values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,fund.getName());
        // them vao CSDL
        database.insert(TABLE_FUND, null, values);
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

    // lay thong tin 1 fund ra tu CSDL
    @SuppressLint("Range")
    public Fund getFund(int id) {
        // cap quyen doc CSDL cho bien database
        SQLiteDatabase database = this.getReadableDatabase();
        // gan cau lenh SQL vao bien selectQuerry
        String selectQuery = "SELECT * FROM " + TABLE_FUND + " WHERE " + KEY_ID + " = " + id;
        // Log ra selectQuerry
        LogUtil.LogD(LOG, selectQuery);
        // doi tuong luu cac hang cua bang truy van
        Cursor c = database.rawQuery(selectQuery, null);
        // chuyen con tro den dong dau tien neu du lieu tra ve tu CSDL khong phai null
        if (c != null) {
            c.moveToFirst();
        }
        // dong goi thong tin vao 1 doi tuong fund
        Fund fund  = new Fund();
        fund.set_id(c.getInt(c.getColumnIndex(KEY_ID)));
        fund.setName(c.getString(c.getColumnIndex(KEY_NAME)));

        // tra ve 1 fund
        return fund;
    }

    // lay thong tin 1 session ra tu CSDL
    @SuppressLint("Range")
    public Session getSession(int id) {
        // cap quyen doc CSDL cho bien database
        SQLiteDatabase database = this.getReadableDatabase();
        // gan cau lenh SQL vao bien selectQuerry
        String selectQuery = "SELECT * FROM " + TABLE_SESSION + " WHERE " + KEY_ID + " = " + id;
        // Log ra selectQuerry
        LogUtil.LogD(LOG, selectQuery);
        // doi tuong luu cac hang cua bang truy van
        Cursor c = database.rawQuery(selectQuery, null);
        // chuyen con tro den dong dau tien neu du lieu tra ve tu CSDL khong phai null
        if (c != null) {
            c.moveToFirst();
        }
        // dong goi thong tin vao 1 doi tuong session
        Session session  = new Session();
        Fund fund = getFund(c.getInt(c.getColumnIndex(KEY_FUND_ID)));
        session.setFund(fund);
        session.set_id(c.getInt(c.getColumnIndex(KEY_ID)));
        session.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        session.setDef_money(c.getFloat(c.getColumnIndex(KEY_DEF_MONEY)));
        session.setDate_begin(new Date(c.getColumnIndex(KEY_DATE_BEGIN)));
        session.setDate_end(new Date(c.getColumnIndex(KEY_DATE_END)));

        // tra ve 1 session
        return session;
    }

    // lay thong tin 1 expense ra tu CSDL
    @SuppressLint("Range")
    public Expense getExpense(int id) {
        // cap quyen doc CSDL cho bien database
        SQLiteDatabase database = this.getReadableDatabase();
        // gan cau lenh SQL vao bien selectQuerry
        String selectQuery = "SELECT * FROM " + TABLE_EXPENSE + " WHERE " + KEY_ID + " = " + id;
        // Log ra selectQuerry
        LogUtil.LogD(LOG, selectQuery);
        // doi tuong luu cac hang cua bang truy van
        Cursor c = database.rawQuery(selectQuery, null);
        // chuyen con tro den dong dau tien neu du lieu tra ve tu CSDL khong phai null
        if (c != null) {
            c.moveToFirst();
        }
        // dong goi thong tin vao 1 doi tuong expense
        Expense expense  = new Expense();
        Fund fund = getFund(c.getInt(c.getColumnIndex(KEY_FUND_ID)));
        expense.setFund(fund);
        expense.set_id(c.getInt(c.getColumnIndex(KEY_ID)));
        expense.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
        expense.setDes(c.getString(c.getColumnIndex(KEY_DES)));
        expense.setDate_create(new Date(c.getColumnIndex(KEY_DATE_CREATE)));
        expense.setPrice(c.getFloat(c.getColumnIndex(KEY_PRICE)));

        // tra ve 1 expense
        return expense;
    }

    // lay thong tin 1 session detail ra tu CSDL
    @SuppressLint("Range")
    public SessionDetail getSessionDetail(int id) {
        // cap quyen doc CSDL cho bien database
        SQLiteDatabase database = this.getReadableDatabase();
        // gan cau lenh SQL vao bien selectQuerry
        String selectQuery = "SELECT * FROM " + TABLE_SESSION_DETAIL + " WHERE " + KEY_ID + " = " + id;
        // Log ra selectQuerry
        LogUtil.LogD(LOG, selectQuery);
        // doi tuong luu cac hang cua bang truy van
        Cursor c = database.rawQuery(selectQuery, null);
        // chuyen con tro den dong dau tien neu du lieu tra ve tu CSDL khong phai null
        if (c != null) {
            c.moveToFirst();
        }
        // dong goi thong tin vao 1 doi tuong expense
        SessionDetail sessionDetail  = new SessionDetail();
        sessionDetail.set_id(c.getInt(c.getColumnIndex(KEY_ID)));
        sessionDetail.setDate_create(new Date(c.getColumnIndex(KEY_DATE_CREATE)));

        Session session = getSession(c.getInt(c.getColumnIndex(KEY_SESSION_ID)));
        sessionDetail.setSession(session);

        Student student = getStudent(c.getInt(c.getColumnIndex(KEY_STUDENT_ID)));
        sessionDetail.setStudent(student);

        // tra ve 1 expense
        return sessionDetail;
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

    // lay thong tin tat ca session ra tu CSDL
    @SuppressLint("Range")
    public ArrayList<Session> getAllSession() {
        ArrayList<Session> arrSession = new ArrayList<Session>();

        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuerry = "SELECT * FROM " + TABLE_SESSION;

        LogUtil.LogD(LOG,selectQuerry);

        Cursor c = database.rawQuery(selectQuerry, null);

        if(c!=null) {
            c.moveToFirst();
            do{
                // dong goi thong tin vao 1 doi tuong session
                Session session = new Session();
                Fund fund = getFund(c.getInt(c.getColumnIndex(KEY_FUND_ID)));
                session.setFund(fund);
                session.set_id(c.getInt(c.getColumnIndex(KEY_ID)));
                session.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                session.setDef_money(c.getFloat(c.getColumnIndex(KEY_DEF_MONEY)));
                session.setDate_begin(new Date(c.getColumnIndex(KEY_DATE_BEGIN)));
                session.setDate_end(new Date(c.getColumnIndex(KEY_DATE_END)));

                arrSession.add(session);
            } while(c.moveToNext()); // chuyen toi dong tiep theo
        }

        // tra ve danh sach cac session
        return arrSession;
    }


    // lay thong tin tat ca expense ra tu CSDL
    @SuppressLint("Range")
    public ArrayList<Expense> getAllExpense() {
        ArrayList<Expense> arrExpense = new ArrayList<Expense>();

        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuerry = "SELECT * FROM " + TABLE_EXPENSE;

        LogUtil.LogD(LOG,selectQuerry);

        Cursor c = database.rawQuery(selectQuerry, null);

        if(c!=null) {
            c.moveToFirst();
            do{
                // dong goi thong tin vao 1 doi tuong expense
                Expense expense = new Expense();
                Fund fund = getFund(c.getInt(c.getColumnIndex(KEY_FUND_ID)));
                expense.setFund(fund);
                expense.set_id(c.getInt(c.getColumnIndex(KEY_ID)));
                expense.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                expense.setDes(c.getString(c.getColumnIndex(KEY_DES)));
                expense.setDate_create(new Date(c.getColumnIndex(KEY_DATE_CREATE)));
                expense.setPrice(c.getFloat(c.getColumnIndex(KEY_PRICE)));

                arrExpense.add(expense);
            } while(c.moveToNext()); // chuyen toi dong tiep theo
        }

        // tra ve danh sach cac expense
        return arrExpense;
    }

    // lay thong tin tat ca sessionDetail ra tu CSDL
    @SuppressLint("Range")
    public ArrayList<SessionDetail> getAllSessionDetail() {
        ArrayList<SessionDetail> arrSessionDetail = new ArrayList<SessionDetail>();

        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_SESSION_DETAIL;

        LogUtil.LogD(LOG,selectQuery);

        Cursor c = database.rawQuery(selectQuery, null);

        if(c!=null) {
            c.moveToFirst();
            do{
                // dong goi thong tin vao 1 doi tuong sessionDetail
                SessionDetail sessionDetail  = new SessionDetail();

                sessionDetail.set_id(c.getInt(c.getColumnIndex(KEY_ID)));
                sessionDetail.setDate_create(new Date(c.getColumnIndex(KEY_DATE_CREATE)));

                Session session = getSession(c.getInt(c.getColumnIndex(KEY_SESSION_ID)));
                sessionDetail.setSession(session);

                Student student = getStudent(c.getInt(c.getColumnIndex(KEY_STUDENT_ID)));
                sessionDetail.setStudent(student);

                arrSessionDetail.add(sessionDetail);
            } while(c.moveToNext()); // chuyen toi dong tiep theo
        }

        // tra ve danh sach cac sessionDetail
        return arrSessionDetail;
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

    // sua thong tin 1 session co ID = _id
    public void updateSession(@NonNull Session session, int _id) {
        // cap quyen ghi cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FUND_ID, session.getFund().get_id());
        values.put(KEY_NAME, session.getName());
        values.put(KEY_DEF_MONEY, session.getDef_money());
        values.put(KEY_DATE_BEGIN, session.getDate_begin().toString());
        values.put(KEY_DATE_END, session.getDate_end().toString());

        // sua session co ID = _id theo cac thong tin trong bien values
        database.update(TABLE_SESSION, values, KEY_ID + " = " + _id, null);
    }

    // sua thong tin 1 fund co ID = _id
    public void updateFund(@NonNull Fund fund, int _id) {
        // cap quyen ghi cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,fund.getName());

        // sua fund co ID = _id theo cac thong tin trong bien values
        database.update(TABLE_FUND, values, KEY_ID + " = " + _id, null);
    }

    // sua thong tin 1 expense co ID = _id
    public void updateExpense(@NonNull Expense expense, int _id) {
        // cap quyen ghi cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FUND_ID, expense.getFund().get_id());
        values.put(KEY_TITLE, expense.getTitle());
        values.put(KEY_DES, expense.getDes());
        values.put(KEY_PRICE, expense.getPrice());
        values.put(KEY_DATE_CREATE, expense.getDate_create().toString());

        // sua expense co ID = _id theo cac thong tin trong bien values
        database.update(TABLE_EXPENSE, values, KEY_ID + " = " + _id, null);
    }


    // sua thong tin 1 sessionDetail co ID = _id
    public void updateSessionDetail(@NonNull SessionDetail sessionDetail, int _id) {
        // cap quyen ghi cho bien database
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SESSION_ID,sessionDetail.getSession().get_id());
        values.put(KEY_STUDENT_ID,sessionDetail.getStudent().get_id());
        values.put(KEY_DATE_CREATE,sessionDetail.getDate_create().toString());

        // sua sessionDetail co ID = _id theo cac thong tin trong bien values
        database.update(TABLE_SESSION_DETAIL, values, KEY_ID + " = " + _id, null);
    }





    // xoa student co ID = _id
    public void deleteStudent(int _id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_STUDENT, KEY_ID + " = " + _id, null);
    }

    // xoa session co ID = _id
    public void deleteSession(int _id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_SESSION, KEY_ID + " = " + _id, null);
    }

    // xoa expense co ID = _id
    public void deleteExpense(int _id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_EXPENSE, KEY_ID + " = " + _id, null);
    }

    // xoa session detail co ID = _id
    public void deleteSessionDetail(int _id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_SESSION_DETAIL, KEY_ID + " = " + _id, null);
    }

}
