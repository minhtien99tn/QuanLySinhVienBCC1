package com.example.quanlysinhvienbcc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database_SV extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "QUANLYSINHVIEN.db";
    private static  final String TABLE_NAME = "sinhvien";
    private static  final String ID = "ID";
    private static  final String TEN = "TEN";
    private static  final String SDT = "SDT";
    private static  final String DIACHI = "DIACHi";
    private static  final String MSV = "MSV";
    private static  final String NGAYSINH = "NGAYSINH ";
    private static  final String FB = "FB";
    private static  final String GIOITINH = "GIOITINH";
    private static final String ANH = "ANH";
    private Context context;

    public Database_SV(@Nullable Context myContext) {
        super(myContext,DATABASE_NAME, null, 1);
        this.context = myContext;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = " CREATE TABLE IF NOT EXISTS sinhvien(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TEN TEXT, " +
                "SDT TEXT, " +
                "DIACHI TEXT, " +
                "NGAYSINH TEXT, " +
                "MSV TEXT, " +
                "FACEBOOK TEXT, " +
                "GIOITINH INTEGER, " +
                "ANH TEXT)";
        sqLiteDatabase.execSQL(sql);
        String sql1 = " CREATE TABLE IF NOT EXISTS lichsu(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TEN TEXT, " +
                "SDT TEXT, " +
                "DIACHI TEXT, " +
                "NGAYSINH TEXT, " +
                "MSV TEXT, " +
                "FACEBOOK TEXT, " +
                "GIOITINH INTEGER, " +
                "ANH TEXT)";
        sqLiteDatabase.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public void themSinhVien(SinhVien mSinhVien, String table){
        SQLiteDatabase database = this.getWritableDatabase();

        String sql = "INSERT INTO "+table+" VALUES(null,?,?,?,?,?,?,?,?)";
        SQLiteStatement stm = database.compileStatement(sql);
        stm.clearBindings();

        stm.bindString(8, mSinhVien.getAnh());
        stm.bindString(7, String.valueOf(mSinhVien.getGioitinh()));
        stm.bindString(6, mSinhVien.getFb());
        stm.bindString(5, mSinhVien.getMsv());
        stm.bindString(4, mSinhVien.getNgaysinh());
        stm.bindString(3, mSinhVien.getDiaChi());
        stm.bindString(2, mSinhVien.getSdt());
        stm.bindString(1, mSinhVien.getTenSV());
        stm.executeInsert();
        database.close();
    }

    public void getAllSinhVien(String table_name, List<SinhVien> studentList) {
        studentList.clear();
        String query = "SELECT * FROM " + table_name;
        Log.e("tb",table_name);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SinhVien sinhVien;
        if(cursor!=null && cursor.moveToFirst()) {
            Log.e("log", "cnt: "+cursor.getCount() + " colum"+cursor.getColumnCount());
            do{
                sinhVien = new SinhVien(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8)
                );
                studentList.add(sinhVien);
            }

            while (cursor.moveToNext());
        }
        cursor.close();
    }
    // tìm kiếm danh sách những nhân viên có tên gần giống
    public List<SinhVien> Search_NV(String search)
    {
        List<SinhVien>  arrayList = new ArrayList<>();
        search.trim();
        String query = "SELECT * FROM " + TABLE_NAME +" WHERE TEN LIKE '%"+search+"%'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor!=null && cursor.moveToFirst())
        {
            do{
                SinhVien sinhVien = new SinhVien(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8)
                );
                //trả lại 1 mảng danh sách
                arrayList.add(sinhVien);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }
    // trả về 1 sinh viên
    public SinhVien nhanVien(int id){
        String query = "SELECT * FROM " + TABLE_NAME +" WHERE ID = +'"+id+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SinhVien sinhVien = null;
        if (cursor!=null)
        {
            cursor.moveToFirst();
            sinhVien = new SinhVien(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getInt(7),
                    cursor.getString(8)
            );
        }
        return sinhVien;
    }
    // sua tt
    public void update(int id, SinhVien mSinhVien) {
        SQLiteDatabase db = this.getWritableDatabase();

        String ten = mSinhVien.getTenSV();
        String sdt = mSinhVien.getSdt();
        String diachi = mSinhVien.getDiaChi();
        String ngaysinh = mSinhVien.getNgaysinh();
        String msv = mSinhVien.getMsv();
        String fb = mSinhVien.getFb();
        Integer gioitinh = mSinhVien.getGioitinh();
        String anh = mSinhVien.getAnh();

        String sql = "UPDATE  sinhvien SET TEN = '"+ten+"', SDT ='"+sdt+"'  ,DIACHI = '"+diachi+"' , NGAYSINH = '"+ngaysinh+"' ," +
                "MSV= '"+msv+"', FACEBOOK ='"+fb+"' ,GIOITINH = '"+gioitinh+"' , ANH ='"+anh+"' WHERE ID = '"+id+"'";

        SQLiteStatement stm = db.compileStatement(sql);
        stm.executeUpdateDelete();
        db.close();
    }
    public void deleteNV(String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name,null ,null);
        db.close();
    }

    public void delete_ID_NV(int masv,String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name, ID + " = ?", new String[] { String.valueOf(masv) });
        db.close();
    }

}

