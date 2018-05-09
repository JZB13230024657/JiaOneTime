package bwie.com.yikezhongtwo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwie.com.yikezhongtwo.R;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by 10419 on 2018/4/10.
 */

public class Remen_Tiaomu_Holder extends RecyclerView.ViewHolder{
    @BindView(R.id.tuijian_touxiang)
    ImageView tuijianTouxiang;
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;
    @BindView(R.id.tuijian_name)
    TextView tuijianName;
    @BindView(R.id.tuijian_time)
    TextView tuijianTime;
    @BindView(R.id.tuijian_biaoti)
    TextView tuijianBiaoti;
    @BindView(R.id.tuijain_pinglun)
    TextView tuijainPinglun;
    public Remen_Tiaomu_Holder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
