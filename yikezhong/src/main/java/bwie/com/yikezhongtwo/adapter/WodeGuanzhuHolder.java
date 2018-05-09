package bwie.com.yikezhongtwo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwie.com.yikezhongtwo.R;

/**
 * Created by 10419 on 2018/4/17.
 */

public class WodeGuanzhuHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.wodeguanzhu_touxiang)
    ImageView wodeguanzhuTouxiang;
    @BindView(R.id.wodeguanzhu_name)
    TextView wodeguanzhuName;
    @BindView(R.id.wodeguanzhu_qianming)
    TextView wodeguanzhuQianming;
    @BindView(R.id.wodeguanzhu_time)
    TextView wodeguanzhuTime;
    public WodeGuanzhuHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
