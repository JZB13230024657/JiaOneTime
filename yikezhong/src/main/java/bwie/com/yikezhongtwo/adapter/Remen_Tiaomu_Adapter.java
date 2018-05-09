package bwie.com.yikezhongtwo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.bean.RemenTiaomuBean;
import bwie.com.yikezhongtwo.view.activity.GlideCircleTransform;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by 10419 on 2018/4/10.
 */

public class Remen_Tiaomu_Adapter extends RecyclerView.Adapter<Remen_Tiaomu_Holder> {

    private List<RemenTiaomuBean.DataBean> data;
    private Context context;

    public Remen_Tiaomu_Adapter(List<RemenTiaomuBean.DataBean> data, Context context) {
        this.context = context;
        this.data = data;
    }

    @Override
    public Remen_Tiaomu_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.tuijian_item, null);
        Remen_Tiaomu_Holder holder = new Remen_Tiaomu_Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Remen_Tiaomu_Holder holder, int position) {
        holder.tuijianName.setText(data.get(position).getCommentNum() + "");
        holder.tuijianTime.setText(data.get(position).getCreateTime() + "");
        holder.tuijianBiaoti.setText(data.get(position).getWorkDesc() + "");
        holder.tuijainPinglun.setText(data.get(position).getUser().getNickname() + "");
        holder.videoplayer.setUp(data.get(position).getVideoUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
        Glide.with(context).load(data.get(position).getCover()).into(holder.videoplayer.thumbImageView);
        Glide.with(context).load(data.get(position).getUser().getIcon()).transform(new GlideCircleTransform(context)).into(holder.tuijianTouxiang);

    }

    @Override
    public int getItemCount() {
        if (data!=null){
            return data.size();
        }else {
            return 0;
        }
    }
}
