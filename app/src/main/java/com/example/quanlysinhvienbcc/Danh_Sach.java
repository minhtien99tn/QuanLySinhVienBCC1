package com.example.quanlysinhvienbcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Danh_Sach extends AppCompatActivity {
    public ListView lvDanhSach;
    public ListSinhVienAdapter adapter;
    private List<SinhVien> arrayList;
    String table_name = "sinhvien";
    public int maSV ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh__sach);
        lvDanhSach =  findViewById(R.id.lvDanhSach);
        arrayList = new ArrayList<>();
        adapter = new ListSinhVienAdapter(this,R.layout.item);
        lvDanhSach.setAdapter(adapter);
        clickListView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(table_name);
    }

    private void clickListView() {
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int maSV = arrayList.get(position).getMaSV();
                ShowDiaLogConfirm(maSV);
            }
        });

    }

    private void ShowDiaLogConfirm(final  int maSV1) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        Button btSua = dialog.findViewById(R.id.lvSua);
        Button btXoa = dialog.findViewById(R.id.lvXoa);
        Button btchitiet = dialog.findViewById(R.id.lvchitiet);
        dialog.show();

        btSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSinhVien.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",maSV1);
                intent.putExtras(bundle);
                startActivity(intent);
                Log.e("id gui", String.valueOf(maSV1));
                dialog.cancel();
                adapter.notifyDataSetChanged();

            }
        });

        btXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Xoa(maSV1);
                dialog.cancel();
            }
        });

        btchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Chi_tiet_sv2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",maSV1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void Xoa(int mmasv) {

        Database_SV database_SV = new Database_SV(this);
        database_SV.delete_ID_NV(mmasv,table_name);
        adapter.notifyDataSetChanged();
        arrayList.clear();
        loadData(table_name);

    }

    private void loadData(String table_name) {
        Database_SV database_SV = new Database_SV(this);
        database_SV.getAllSinhVien(table_name, arrayList);
        Log.e("LOG", "cnt: "+arrayList.size());
        adapter.addAllStudent(arrayList);
        database_SV.close();
    }
}
