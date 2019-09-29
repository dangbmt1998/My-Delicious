package com.example.ng.foodsh;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.List;

public class DiaDiemAdapter extends BaseAdapter {
    private Home context;
    private int layout;


    private List<DiaDiem> diaDiemList;
    public DiaDiemAdapter(Home context, int layout, List<DiaDiem> diaDiemList) {
        this.context = context;
        this.layout = layout;
        this.diaDiemList = diaDiemList;
    }

    @Override
    public int getCount() {
        return diaDiemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView txtTenDiaDiem, txtDiaChi;
        ImageView imgDelete, imgEdit, imginfo,imghinh;

    }
    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder holder;
        Context context1;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtTenDiaDiem    = (TextView) view.findViewById(R.id.tvTendiadiem);
            holder.txtDiaChi        = (TextView) view.findViewById(R.id.diachi);
            holder.imgDelete        = (ImageView) view.findViewById(R.id.imageViewDelete);
            holder.imgEdit          = (ImageView) view.findViewById(R.id.imageViewEdit);
            holder.imginfo          = (ImageView) view.findViewById(R.id.imageViewinfo);
            holder.imghinh          = (ImageView) view.findViewById(R.id.imageViewhinh);
            view.setTag(holder);

        }else {
            holder = (ViewHolder) view.getTag();
        }
        final DiaDiem diaDiem = diaDiemList.get(i);
        holder.txtTenDiaDiem.setText(diaDiem.getTendd());
        holder.txtDiaChi.setText(diaDiem.getDiachi());
        String a = diaDiem.getGiohoatdong();

        Picasso.with(context).load(diaDiem.getImage()).into(holder.imghinh);
        final int idus = Integer.parseInt(context.id_user());
        final int ided = diaDiem.getId_us();

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateDiaDiemActivity.class);
                intent.putExtra("dataDiadiem",diaDiem);
                if (idus==ided) {
                    context.startActivity(intent);
                }else if (idus!=ided){
                    Toast.makeText(context, "Không phải địa điểm của bạn", Toast.LENGTH_SHORT).show();
                }

            }
        });
        holder.imginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(context,InFoDiaDiemActivity.class);
                intent2.putExtra("dataDiadiem",diaDiem);
                context.startActivity(intent2);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateDiaDiemActivity.class);
                intent.putExtra("dataDiadiem",diaDiem);
                if (idus==ided) {
                    XacNhanXoa(diaDiem.getTendd(),diaDiem.getId_dd());
                }else if (idus!=ided){
                    Toast.makeText(context, "Không phải địa điểm của bạn", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
    private void XacNhanXoa(String ten, final int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có muốn xóa địa điểm "+ten+" không?");
        final DiaDiem diaDiem = diaDiemList.get(0);
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                    context.DeleteDiadiem(id);

            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
}

