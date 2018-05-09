package bwie.com.yikezhongtwo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import java.util.List;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.bean.GuanzhuLiebiaoBean;

/**
 * Created by 10419 on 2018/4/17.
 */

public class WodeGuanzhuAdapter extends RecyclerView.Adapter<WodeGuanzhuHolder> {
    private List<GuanzhuLiebiaoBean.DataBean> data;
    private Context context;

    public WodeGuanzhuAdapter(List<GuanzhuLiebiaoBean.DataBean> data, Context context) {
        this.context = context;
        this.data = data;
    }

    @Override
    public WodeGuanzhuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.wodeguanzhuitem, null);
        WodeGuanzhuHolder wodeGuanzhuHolder = new WodeGuanzhuHolder(view);
        return wodeGuanzhuHolder;
    }

    @Override
    public void onBindViewHolder(WodeGuanzhuHolder holder, int position) {
        holder.wodeguanzhuName.setText(data.get(position).getUsername());
        holder.wodeguanzhuQianming.setText(data.get(position).getAppsecret());
        holder.wodeguanzhuTime.setText(data.get(position).getCreatetime());
        //Glide.with(context).load(data.get(position))
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
