package com.example.quanlysinhvienbcc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import java.util.InputMismatchException;

public class Them_Sv extends AppCompatActivity {
    private static final int REQUEST_CODE_FOLDER = 123 ;
    private EditText editTen,edtSDT,editDiaChi,editMsv,editNgaySinh,editFb;
    private RadioButton radNam,radNu;
    Button butNhapLai, butNhap;
    ImageButton btThemANh;
    ImageView impre;
    String imgSelected = "";
    final  int REQUEST_CHOOSE_PHOTO = 321;
    Database_SV database_SV;
    String table_name = "sinhvien";
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them__sv);
        mContext = this;
        imgSelected = "";
        anhXa();
        database_SV = new Database_SV(this);
        Event();
        themAnh();
    }

    private void themAnh() {
        btThemANh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap img = BitmapFactory.decodeStream(inputStream);
                impre.setImageBitmap(img);
                imgSelected = Utility.copyImageToCache(mContext, inputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void anhXa() {
        btThemANh =findViewById(R.id.btThemANh);
        impre = findViewById(R.id.impre);
        editTen = findViewById(R.id.editTen);
        edtSDT =  findViewById(R.id.edtSDT);
        editDiaChi = findViewById(R.id.editDiaChi);
        editMsv= findViewById(R.id.editMsv);
        editNgaySinh= findViewById(R.id.editNgaySinh);
        editFb = findViewById(R.id.editFb);
        radNam= findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        butNhap = findViewById(R.id.butNhap);
        butNhapLai = findViewById(R.id.butNhapLai);
    }
    private  void Event(){
        butNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fb ;
                String ngaySinh;
                String tenSV;
                String diaChi ;
                String SDT ;
                String msv;
                int gt = 0;
                try {
                    fb = editFb.getText().toString();
                    ngaySinh = editNgaySinh.getText().toString();
                    tenSV = editTen.getText().toString().trim();
                    diaChi = editDiaChi.getText().toString();
                    SDT = edtSDT.getText().toString();
                    msv = editMsv.getText().toString();
                }catch (InputMismatchException e)
                {
                    fb ="";
                    ngaySinh = "";
                    tenSV = "";
                    diaChi ="";
                    SDT = "";
                    msv ="";
                }

//                byte[] anh = getByteArrayFromImageView(impre);

                if (TextUtils.isEmpty(imgSelected)){
                    imgSelected = "anh2.PNG";
                    Utility.copyDrawableToCache(mContext, mContext.getResources().getDrawable(R.drawable.anh2), imgSelected);
                }

                if (radNam.isChecked() == true)
                    gt = 1;
                Log.e("Ten", tenSV);
                if (tenSV.equals("")) {
                    editTen.setError("Vui lòng nhập dữ liệu!");
                } else {
                    SinhVien sv = new SinhVien(tenSV, SDT, diaChi, ngaySinh, msv, fb, gt, imgSelected);
                    database_SV.themSinhVien(sv, table_name);
                    database_SV.close();
                    imgSelected = "";
                    Toast.makeText(Them_Sv.this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show();
                }
            }
        });
        butNhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Them_Sv.this,"Đã nhập lại",Toast.LENGTH_SHORT).show();
                edtSDT.setText("");
                editTen.setText("");
                editMsv.setText("");
                editDiaChi.setText("");
                editNgaySinh.setText("");
                editFb.setText("");
                radNam.setChecked(false);
                radNu.setChecked(false);
            }
        });

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

