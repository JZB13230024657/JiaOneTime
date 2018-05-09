package bwie.com.yikezhongtwo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.adapter.OnItemListener;
import bwie.com.yikezhongtwo.adapter.ShipinAdapter;
import bwie.com.yikezhongtwo.bean.ShipinBean;
import bwie.com.yikezhongtwo.presenter.ShipinPre;
import bwie.com.yikezhongtwo.presenter.Shipin_Presenter;
import bwie.com.yikezhongtwo.utils.Constant;
import bwie.com.yikezhongtwo.view.activity.ShipinXiangqing;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/11.
 */

public class ShipinRemen extends Fragment implements ShipinPre {
    @BindView(R.id.shipin_remen_recycle)
    RecyclerView shipinRemenRecycle;
    Unbinder unbinder;
    @BindView(R.id.shipinremenrefresh)
    SmartRefreshLayout shipinremenrefresh;
    private int page = 1;
    private Map<String, String> map;
    private Shipin_Presenter shipin_presenter;
    private List<ShipinBean.DataBean> data;
    private List<ShipinBean.DataBean> list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipin_remen, container, false);
        unbinder = ButterKnife.bind(this, view);
        shipinRemenRecycle.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        shipinRemenRecycle.setNestedScrollingEnabled(false);
        shipin_presenter = new Shipin_Presenter(this);
        map = new HashMap<>();
       // map.put("token", "3D6C0E4D376A8A6332525591200C13D4");
        shipin_presenter.getShipinData(Constant.REMEN_SHIPIN_URL, map);
        shipinremenrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                page = 1;
                map.put("page", page + "");
                shipin_presenter.getShipinData(Constant.REMEN_SHIPIN_URL, map);
                refreshlayout.finishRefresh(2000/*,false*/);
            }
        });
        shipinremenrefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                map.put("page", page + "");
                shipin_presenter.getShipinData(Constant.REMEN_SHIPIN_URL, map);
                refreshlayout.finishLoadmore(2000/*,false*/);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getShipinSuccess(ResponseBody responseBody) {
        try {
            final String shipin = responseBody.string();

            Log.e("qqqq", shipin);
            final ShipinBean shipinBean = new Gson().fromJson(shipin, ShipinBean.class);
            data = shipinBean.getData();
            list.addAll(data);
            ShipinAdapter shipinAdapter = new ShipinAdapter(list, getActivity());
            shipinRemenRecycle.setAdapter(shipinAdapter);
            shipinAdapter.setOnItemListener(new OnItemListener() {
                @Override
                public void onItemClickListener(int position) {
                    String s = shipinBean.getData().get(position).getVideoUrl().toString();
                    Intent intent = new Intent(getActivity(), ShipinXiangqing.class);
                    intent.putExtra("followId",shipinBean.getData().get(position).getUid());
                    intent.putExtra("video", shipinBean.getData().get(position).getVideoUrl());
                    intent.putExtra("showtext", shipinBean.getData().get(position).getWorkDesc());
                    intent.putExtra("cover", shipinBean.getData().get(position).getCover());
                    intent.putExtra("icon",shipinBean.getData().get(position).getUser().getIcon());
                    startActivity(intent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getShipinError(Throwable throwable) {

    }
}
