package bwie.com.yikezhongtwo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.bean.DuanziBean;
import bwie.com.yikezhongtwo.view.activity.GlideCircleTransform;

/**
 * Created by 10419 on 2018/4/11.
 */

public class DuanziAdapter extends RecyclerView.Adapter<Duanzi_Holder> {


    private List<DuanziBean.DataBean> list;
    private Context context;

    public DuanziAdapter(List<DuanziBean.DataBean> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Duanzi_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.duanzi_item, null);
        Duanzi_Holder duanzi_holder = new Duanzi_Holder(view);
        return duanzi_holder;
    }

    @Override
    public void onBindViewHolder(Duanzi_Holder holder, int position) {
        holder.duanziName.setText(list.get(position).getUser().getNickname() + "");
        holder.duanziShow.setText(list.get(position).getContent());
        holder.duanziTime.setText(list.get(position).getCreateTime());
        Glide.with(context).load(list.get(position).getUser().getIcon()).transform(new GlideCircleTransform(context)).into(holder.duanziTouxiang);
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }

    }
}
