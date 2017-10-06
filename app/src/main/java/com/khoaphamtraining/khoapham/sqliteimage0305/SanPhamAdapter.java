package com.khoaphamtraining.khoapham.sqliteimage0305;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 11/06/16.
 */
public class SanPhamAdapter extends ArrayAdapter<SanPham> {

    public SanPhamAdapter(Context context, int resource, List<SanPham> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.dong_san_pham, null);
        }
        SanPham p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txtTen = (TextView) view.findViewById(R.id.textViewTen);
            txtTen.setText(p.Ten);

            TextView txtGia = (TextView) view.findViewById(R.id.textViewGia);
            txtGia.setText("Gia: " + p.Gia);

            ImageView imgHinh = (ImageView) view.findViewById(R.id.imageViewHinhDS);
            // chuyen byte[] -> Bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(p.Hinh, 0, p.Hinh.length);
            imgHinh.setImageBitmap(bitmap);

        }
        return view;
    }

}
