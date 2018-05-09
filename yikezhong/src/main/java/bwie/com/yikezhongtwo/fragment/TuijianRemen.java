package bwie.com.yikezhongtwo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.adapter.Remen_Tiaomu_Adapter;
import bwie.com.yikezhongtwo.bean.BannerBean;
import bwie.com.yikezhongtwo.bean.RemenTiaomuBean;
import bwie.com.yikezhongtwo.presenter.RV_Presenter;
import bwie.com.yikezhongtwo.presenter.Remen_Presenter;
import bwie.com.yikezhongtwo.presenter.Remen_RV_Presenter;
import bwie.com.yikezhongtwo.presenter.Tuijian_Remen_presenter;
import bwie.com.yikezhongtwo.utils.Constant;
import bwie.com.yikezhongtwo.utils.GlideImageLoader;
import okhttp3.ResponseBody;


/**
 * Created by 10419 on 2018/4/9.
 */

public class TuijianRemen extends Fragment implements Remen_Presenter, RV_Presenter {

    @BindView(R.id.banner)
    Banner banner;
    Unbinder unbinder;
    @BindView(R.id.tj_rm_rv)
    RecyclerView tjRmRv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Tuijian_Remen_presenter tuijian_remen_presenter;
    private List<String> list;
    private Remen_RV_Presenter remen_rv_presenter;
    private RemenTiaomuBean remenTiaomuBean;
    private List<RemenTiaomuBean.DataBean> data;
    private List<RemenTiaomuBean.DataBean> list1=new ArrayList<>();
    private Remen_Tiaomu_Adapter remen_tiaomu_adapter;
    public int page=1;
    private Map<String, String> tiaomumap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View remen = inflater.inflate(R.layout.tuijianremen, container, false);

        tuijian_remen_presenter = new Tuijian_Remen_presenter(this);
        Map<String, String> map = new HashMap<>();
        tuijian_remen_presenter.getBannerUrl(Constant.BANNER_URL, map);
        list = new ArrayList<>();
        unbinder = ButterKnife.bind(this, remen);
        remen_rv_presenter = new Remen_RV_Presenter(this);
        tiaomumap = new HashMap<>();
        //tiaomumap.put("token", "3D6C0E4D376A8A6332525591200C13D4");
        remen_rv_presenter.getTiaomuUrl(Constant.REMEN_SHIPIN_URL, tiaomumap);
        tjRmRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tjRmRv.setNestedScrollingEnabled(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list1.clear();
                page=1;
                tiaomumap.put("page",page+"");
                remen_rv_presenter.getTiaomuUrl(Constant.REMEN_SHIPIN_URL, tiaomumap);
                refreshlayout.finishRefresh(2000/*,false*/);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                tiaomumap.put("page",page+"");
                remen_rv_presenter.getTiaomuUrl(Constant.REMEN_SHIPIN_URL, tiaomumap);
                refreshlayout.finishLoadmore(2000/*,false*/);
            }
        });
        return remen;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void OnSuccess(ResponseBody responseBody) {
        try {
            String json = responseBody.string();
            BannerBean bannerBean = new Gson().fromJson(json, BannerBean.class);
            for (int i = 0; i < bannerBean.getData().size(); i++) {
                String icon = bannerBean.getData().get(i).getIcon();
                list.add(icon);
            }
            banner.setImageLoader(new GlideImageLoader());
            banner.setDelayTime(3000);
            banner.isAutoPlay(true);
            banner.setImages(list);
            banner.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Error(Throwable throwable) {

    }

    @Override
    public void TiaomuDataOnSuccess(ResponseBody responseBody) {
        try {
            String tiaomu = responseBody.string();
            remenTiaomuBean = new Gson().fromJson(tiaomu, RemenTiaomuBean.class);
            data = remenTiaomuBean.getData();
            list1.addAll(data);
            remen_tiaomu_adapter = new Remen_Tiaomu_Adapter(list1, getContext());
            tjRmRv.setAdapter(remen_tiaomu_adapter);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void TiaomuError(Throwable throwable) {

    }
}
