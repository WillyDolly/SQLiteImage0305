package com.khoaphamtraining.khoapham.sqliteimage0305;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DanhSachSanPham extends AppCompatActivity {

    ListView lvSP;
    ArrayList<SanPham> mangSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham);

        lvSP = (ListView) findViewById(R.id.listViewSanPham);
        mangSP = new ArrayList<SanPham>();

        // db khai bao public static o man hinh MainActivity
        Cursor sanPham = MainActivity.db.GetData("SELECT * FROM SanPham");
        while (sanPham.moveToNext()){
            mangSP.add(new SanPham(
                    sanPham.getString(1),
                    sanPham.getInt(2),
                    sanPham.getBlob(3)
            ));
        }

        SanPhamAdapter adapter = new SanPhamAdapter(
                getApplicationContext(),
                R.layout.dong_san_pham,
                mangSP
        );

        lvSP.setAdapter(adapter);
    }
}
