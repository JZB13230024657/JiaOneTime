package bwie.com.yikezhongtwo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import java.util.List;

import bwie.com.yikezhongtwo.R;

public class ImgGridViewAdapter extends BaseAdapter {
    private List<Bitmap> list;
    private Context context;

    public ImgGridViewAdapter(List<Bitmap> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.gridviewadapter,null);
        ImageView img = view.findViewById(R.id.shangchuan_img);
        Bitmap bitmap = list.get(position);
        img.setImageBitmap(bitmap);
        return view;
    }

    public void setList(List<Bitmap> list) {
        this.list = list;
    }
}
