package bwie.com.yikezhongtwo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwie.com.yikezhongtwo.R;

/**
 * Created by 10419 on 2018/4/11.
 */

public class ShipinHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.shipin_img)
    ImageView shipinImg;
    public ShipinHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
