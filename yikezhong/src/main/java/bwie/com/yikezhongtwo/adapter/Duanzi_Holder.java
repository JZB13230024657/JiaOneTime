package bwie.com.yikezhongtwo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwie.com.yikezhongtwo.R;

/**
 * Created by 10419 on 2018/4/11.
 */

public class Duanzi_Holder extends RecyclerView.ViewHolder{
    @BindView(R.id.duanzi_touxiang)
    ImageView duanziTouxiang;
    @BindView(R.id.duanzi_name)
    TextView duanziName;
    @BindView(R.id.duanzi_time)
    TextView duanziTime;
    @BindView(R.id.duanzi_show)
    TextView duanziShow;
    private final Unbinder bind;

    public Duanzi_Holder(View itemView) {
        super(itemView);
        bind = ButterKnife.bind(this, itemView);
    }
}
