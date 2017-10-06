package com.khoaphamtraining.khoapham.sqliteimage0305;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by admin on 11/06/16.
 */
public class SQLite extends SQLiteOpenHelper {

    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void INSERT_SANPHAM(String ten, int gia, byte[] hinh){

        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO SanPham VALUES(null, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, ten);
        statement.bindLong(2, gia);
        statement.bindBlob(3, hinh);

        statement.executeInsert();
    }

    public Cursor GetData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
