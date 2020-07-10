package com.example.quanlysinhvienbcc;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListSinhVienAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<SinhVien> sinhVienList;

    public ListSinhVienAdapter(Context context, int myLayout) {
        this.myContext = context;
        this.myLayout = myLayout;
        this.sinhVienList = new ArrayList<>();
    }

    public void addAllStudent(List<SinhVien> list){
        sinhVienList.clear();
        Log.e("log", "cnt "+list.size());
        sinhVienList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView==null)
        {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(myContext).inflate(R.layout.item,parent,false);


            holder.AnhSV = (ImageView) convertView.findViewById(R.id.itemsAnh);

            holder.tenSV  = convertView.findViewById(R.id.itemsTenSV);
            holder.Msv = convertView.findViewById(R.id.itemsMsv);
            holder.Fb = convertView.findViewById(R.id.itemsFb);


            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        SinhVien nv = sinhVienList.get(position);

//        Bitmap imgNhanVien = BitmapFactory.decodeByteArray(nv.getAnh(),0,nv.getAnh().length);
//        holder.AnhNV.setImageBitmap(imgNhanVien);

        holder.tenSV.setText(nv.getTenSV());
        holder.Fb.setText(nv.getFb());
        holder.Msv.setText(nv.getMsv());
        Bitmap bp= Utility.getBitmap(myContext, nv.getAnh());
        holder.AnhSV.setImageBitmap(bp);


//
//        Bitmap bitmap = BitmapFactory.decodeByteArray(Anh,0,Anh.length);
//        holder.AnhNV.setImageBitmap(bitmap);
//
//        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(myLayout,null);


        return convertView;
    }
    // view holder giúp listview custom không phải load lại danh sách
    // nếu dùng listview custom cũ thì load lại danh sách rất lag
    public class ViewHolder{
        ImageView AnhSV;
        TextView tenSV,Fb,Msv;

    }

}

