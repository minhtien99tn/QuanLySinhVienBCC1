package com.example.quanlysinhvienbcc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ChiTietSinhVien extends AppCompatActivity {
    private static final int REQUEST_CODE_FOLDER = 1234 ;
    EditText ten,sdt,ngaysinh,diachi,chitietMsv,chitietFb;
    RadioButton rdNam,rdNu;
    Button luu,nhaplai;
    String table_name = "lichsu";
    ImageView imv;
    ImageButton chitietThemAnh;
    int id;
    SinhVien mSinhVien;
    String imgSelected = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sinh_vien);
        connect();
        // lấy id từ danh sách
        getDataFromDanhSach();
        themAnh();
        setText();
        click();
        imgSelected = "";

    }

    private void setText() {
        Database_SV database_SV = new Database_SV(ChiTietSinhVien.this);
        mSinhVien = database_SV.nhanVien(id);
        ten.setText(mSinhVien.getTenSV());
        sdt.setText(mSinhVien.getSdt());
        ngaysinh.setText(mSinhVien.getNgaysinh());
        diachi.setText(mSinhVien.getDiaChi());
        chitietMsv.setText(mSinhVien.getMsv());
        chitietFb.setText(mSinhVien.getFb());


        Bitmap bp= Utility.getBitmap(getApplicationContext(), mSinhVien.getAnh());
        imv.setImageBitmap(bp);

        if (mSinhVien.getGioitinh()==0)
        {
            rdNu.setChecked(true);
        }
        else{
            rdNam.setChecked(true);
        }
//        database_nv.themNhanVien(nv);
//        database_nv.close();
    }
    // lấy id gửi từ bên danh sách nhân viên
    private void getDataFromDanhSach() {

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null)
        {
            id = bundle.getInt("id",0);
            // Log.e("id nhan", String.valueOf(id));
        }
    }
    private void click() {
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database_SV database_SV = new Database_SV(ChiTietSinhVien.this);
                int gt = 1;
                String fb = chitietFb.getText().toString();
                String ngaySinh = ngaysinh.getText().toString();
                String tenNV = ten.getText().toString();
                String diaChi = diachi.getText().toString();
                String SDT = sdt.getText().toString();
                String msv = chitietMsv.getText().toString();
                if(rdNu.isChecked()==true)
                {
                    gt =0;
                }
                if (TextUtils.isEmpty(imgSelected)){
                    imgSelected = mSinhVien.getAnh();
                } else {
                    Utility.deleteImage(getApplicationContext(), mSinhVien.getAnh());
                }
                //  Log.e("Ten",tenNV);
                SinhVien nv = new SinhVien(tenNV,SDT,diaChi,ngaySinh,msv,fb,gt,imgSelected);
                database_SV.update(id,nv);
                database_SV.close();
                Toast.makeText(ChiTietSinhVien.this,"Đã lưu!",Toast.LENGTH_SHORT).show();

            }
        });
        nhaplai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ten.setText(mSinhVien.getTenSV());
                sdt.setText(mSinhVien.getSdt());
                ngaysinh.setText(mSinhVien.getNgaysinh());
                diachi.setText(mSinhVien.getDiaChi());
                chitietMsv.setText(mSinhVien.getMsv());
                chitietFb.setText(mSinhVien.getFb());
                rdNam.setChecked(false);
                rdNu.setChecked(false);
            }
        });


    }
    private void connect() {
        ten = (EditText) findViewById(R.id.chitietTen);

        sdt = (EditText) findViewById(R.id.chitietSDT);
        ngaysinh = (EditText) findViewById(R.id.chitietNS);

        diachi = (EditText) findViewById(R.id.chitietDC);

        chitietMsv = (EditText) findViewById(R.id.chitietMsv);
        chitietFb = (EditText) findViewById(R.id.chitietFb);

        rdNam = (RadioButton) findViewById(R.id.chitietNam);
        rdNu = (RadioButton) findViewById(R.id.chitietNu);

        luu = (Button) findViewById(R.id.chitietLuu);
        nhaplai = (Button) findViewById(R.id.chitietNhapLai);

        imv = findViewById(R.id.chitietImpre);
        chitietThemAnh =findViewById(R.id.chitietThemANh);


    }
    private byte[] getByteArrayFromImageView(ImageView imv) {
        BitmapDrawable drawable = (BitmapDrawable) imv.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private void themAnh() {
        chitietThemAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
                Toast.makeText(ChiTietSinhVien.this,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imv.setImageBitmap(bitmap);

                imgSelected = Utility.copyImageToCache(getApplicationContext(), inputStream);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}

