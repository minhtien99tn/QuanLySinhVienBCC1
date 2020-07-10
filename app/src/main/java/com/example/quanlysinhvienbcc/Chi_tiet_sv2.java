package com.example.quanlysinhvienbcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
public class Chi_tiet_sv2 extends AppCompatActivity {
    TextView ten,sdt,ngaysinh,diachi,msv;
    TextView fb;
    RadioButton rdNam,rdNu;
    // Button luu,nhaplai;
    String table_name = "lichsu";
    ImageView imv;
    int id;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sv2);
        mContext = this;
        connect();
        // lấy id từ danh sách
        getDataFromDanhSach();
        setText();

    }

    private void setText() {
        Database_SV database_SV = new Database_SV(mContext);
        SinhVien nv = database_SV.nhanVien(id);
        ten.setText(nv.getTenSV());
        sdt.setText(nv.getSdt());
        ngaysinh.setText(nv.getNgaysinh());
        diachi.setText(nv.getDiaChi());
        msv.setText(nv.getMsv());
        fb.setText(nv.getFb());
        Linkify.addLinks(fb,Linkify.WEB_URLS);
        Bitmap bp= Utility.getBitmap(mContext, nv.getAnh());
        imv.setImageBitmap(bp);
        if (nv.getGioitinh()==0)
        {
            rdNu.setChecked(true);
        }
        else{
            rdNam.setChecked(true);
        }

    }
    // lấy id gửi từ bên danh sách nhân viên
    private void getDataFromDanhSach() {

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null)
        {
            id = bundle.getInt("id",0);
            Log.e("id nhan", String.valueOf(id));
        }
    }

    private void connect() {
        ten = findViewById(R.id.chitietTen);

        sdt = findViewById(R.id.chitietSDT);
        ngaysinh =  findViewById(R.id.chitietNS);

        diachi = findViewById(R.id.chitietDC);

        msv =  findViewById(R.id.chitietMsv);
        fb =  findViewById(R.id.chitietFb);

        rdNam = (RadioButton) findViewById(R.id.chitietNam);
        rdNu = (RadioButton) findViewById(R.id.chitietNu);

        imv = findViewById(R.id.chitietImpre);

    }
    private byte[] getByteArrayFromImageView(ImageView impre) {
        BitmapDrawable drawable = (BitmapDrawable) impre.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


}
