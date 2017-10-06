package com.khoaphamtraining.khoapham.sqliteimage0305;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    Button btnThem, btnDanhSach;
    EditText edtTen, edtGia;
    ImageView imgHinh;
    int REQUEST_CODE = 123;
    public static  SQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        db = new SQLite(this, "BanHang.sqlite", null, 1);

        db.QueryData("CREATE TABLE IF NOT EXISTS SanPham(id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR, Gia INTEGER, Hinh BLOB)");


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtTen.getText().toString();
                int gia = Integer.parseInt(edtGia.getText().toString());

                // chuyen hinh tu Bitmap -> byte[]
                db.INSERT_SANPHAM(ten, gia, ImageView_To_Byte(imgHinh));

                Toast.makeText(MainActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                edtTen.setText("");
                edtGia.setText("");
                imgHinh.setImageResource(R.drawable.camera);
            }
        });

        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

        btnDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DanhSachSanPham.class);
                startActivity(intent);
            }
        });


    }

    public byte[] ImageView_To_Byte(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa(){
        btnThem = (Button) findViewById(R.id.buttonThem);
        edtGia = (EditText) findViewById(R.id.editTextGia);
        edtTen = (EditText) findViewById(R.id.editTextTen);
        imgHinh = (ImageView) findViewById(R.id.imageViewHinh);
        btnDanhSach = (Button) findViewById(R.id.buttonDanhSach);
    }
}
