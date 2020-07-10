package com.example.quanlysinhvienbcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tim_sv extends AppCompatActivity {

    public EditText getSearch;
    public ImageButton imgSearch;
    public String search;
    public ListView lvDanhSach;
    public List<SinhVien> arrayList;
    public ListSinhVienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_sv);

        lvDanhSach =  findViewById(R.id.listTimSV);
        getSearch =  findViewById(R.id.edtSearch);
        imgSearch =  findViewById(R.id.imgSearch);

        arrayList = new ArrayList<>();
        adapter = new ListSinhVienAdapter(Tim_sv.this,R.layout.item);
        lvDanhSach.setAdapter(adapter);
        Search();
        clickListView();
    }

    private void clickListView() {
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Tim_sv.this, ChiTietSinhVien.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",arrayList.get(position).getMaSV());
                intent.putExtras(bundle);
                startActivity(intent);
                Log.e("id gui", String.valueOf(arrayList.get(position).getMaSV()));
            }
        });

    }


    private void Search() {
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = getSearch.getText().toString();
                Log.e("id",search);
                setList(search);
            }
        });
    }

    private void setList(String search) {
        Database_SV database_SV = new Database_SV(Tim_sv.this);
        arrayList = new ArrayList<>();

        arrayList = database_SV.Search_NV(search);
        adapter.addAllStudent(arrayList);
        for (SinhVien nv: arrayList) {
            database_SV.themSinhVien(nv, "lichsu");
        }
        database_SV.close();
    }
}
