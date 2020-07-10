package com.example.quanlysinhvienbcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class lichSu extends AppCompatActivity {

    LinearLayout xoalichsu;
    ListView lvLichSu;
    List<SinhVien> arrayList ;
    ListSinhVienAdapter adapter;
    String table_name = "lichsu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);

        lvLichSu = (ListView) findViewById(R.id.lvLichsu);
        xoalichsu = (LinearLayout) findViewById(R.id.xoatatca);
        arrayList = new ArrayList<>();
        adapter = new ListSinhVienAdapter(this,R.layout.item);
        lvLichSu.setAdapter(adapter);
        loadData();
        adapter.notifyDataSetChanged();
        xoalichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDiaLogConfirm();

            }
        });


    }
    private void ShowDiaLogConfirm() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_confirm_delete_layout);
        Button btHuy = dialog.findViewById(R.id.lichsu_huy);
        Button btXoa = dialog.findViewById(R.id.lichsu_delete);
        dialog.show();
        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database_SV database_SV = new Database_SV(lichSu.this);
                database_SV.deleteNV(table_name);
                adapter.notifyDataSetChanged();
                arrayList.clear();
                Toast.makeText(lichSu.this,"Đã xóa!",Toast.LENGTH_SHORT).show();
                database_SV.close();
                dialog.cancel();
            }
        });

    }

    private void loadData() {
        Database_SV database_SV = new Database_SV(this);
        database_SV.getAllSinhVien(table_name, arrayList);
        adapter.addAllStudent(arrayList);
        adapter.notifyDataSetChanged();
        database_SV.close();
    }


}
