package bwie.com.yikezhongtwo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.bean.ShipinBean;

/**
 * Created by 10419 on 2018/4/11.
 */

public class ShipinAdapter extends RecyclerView.Adapter<ShipinHolder> {

    private List<ShipinBean.DataBean> data ;
    private Context context;
    private OnItemListener onItemListener;

    public ShipinAdapter(List<ShipinBean.DataBean> data, Context context) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ShipinHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shipin_item, null);
        ShipinHolder shipinHolder = new ShipinHolder(view);
        return shipinHolder;
    }

    @Override
    public void onBindViewHolder(ShipinHolder holder, final int position) {
        String[] split = data.get(position).getCover().split("\\|");
        Glide.with(context).load(split[0]).into(holder.shipinImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListener.onItemClickListener(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (data!=null){
            return data.size();
        }else {
            return 0;
        }
    }
    public void setOnItemListener(OnItemListener onItemListener){
        this.onItemListener=onItemListener;
    }
}
