package com.example.quanlysinhvienbcc;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout timKiem,themSV,danhSachSv,lichSu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        chuyenMan();
    }

    private void chuyenMan() {
        timKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Tim_sv.class));
            }
        });
        danhSachSv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Danh_Sach.class));
            }
        });
        themSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Them_Sv.class));
            }
        });
        lichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,lichSu.class));
            }
        });
    }

    private void anhXa() {
        timKiem = findViewById(R.id.timKiem);
        themSV = findViewById(R.id.themSV);
        danhSachSv = findViewById(R.id.danhSachSV);
        lichSu= findViewById(R.id.lichSu);
    }

}
