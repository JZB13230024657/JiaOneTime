package bwie.com.yikezhongtwo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import bwie.com.yikezhongtwo.adapter.DuanziAdapter;
import bwie.com.yikezhongtwo.bean.DuanziBean;
import bwie.com.yikezhongtwo.presenter.Duanzi_Pre;
import bwie.com.yikezhongtwo.presenter.Duanzi_Presenter;
import bwie.com.yikezhongtwo.utils.Constant;
import okhttp3.ResponseBody;


/**
 * Created by 10419 on 2018/4/9.
 */

public class Duanzi extends Fragment implements Duanzi_Pre {
    Unbinder unbinder;
    @BindView(R.id.duanzi_recycle)
    RecyclerView duanziRecycle;
    @BindView(R.id.duanzirefresh)
    SmartRefreshLayout duanzirefresh;
    private int page=1;
    private List<DuanziBean.DataBean> data;
    private List<DuanziBean.DataBean> list=new ArrayList<>();
    private Map<String, String> map;
    private Duanzi_Presenter duanzi_presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View duanzi = inflater.inflate(R.layout.duanzi, container, false);
        unbinder = ButterKnife.bind(this, duanzi);
        duanziRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        duanziRecycle.setNestedScrollingEnabled(false);
        duanzi_presenter = new Duanzi_Presenter(this);
        map = new HashMap<>();
       // map.put("token", "3D6C0E4D376A8A6332525591200C13D4");
        duanzi_presenter.getDuanziData(Constant.DUANZI_TIAOMY_URL, map);
        duanzirefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                page=1;
                map.put("page",page+"");
                duanzi_presenter.getDuanziData(Constant.REMEN_SHIPIN_URL, map);
                refreshlayout.finishRefresh(2000/*,false*/);
            }
        });
        duanzirefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                map.put("page",page+"");
                duanzi_presenter.getDuanziData(Constant.REMEN_SHIPIN_URL, map);
                refreshlayout.finishLoadmore(2000/*,false*/);
            }
        });
        return duanzi;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getDuanziSuccess(ResponseBody responseBody) {
        try {
            String duanziTiaomuData = responseBody.string();
            DuanziBean duanziBean = new Gson().fromJson(duanziTiaomuData, DuanziBean.class);
            data = duanziBean.getData();
            list.addAll(data);
            DuanziAdapter duanziAdapter = new DuanziAdapter(list, getActivity());
            duanziRecycle.setAdapter(duanziAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDuanziError(Throwable throwable) {

    }
}
