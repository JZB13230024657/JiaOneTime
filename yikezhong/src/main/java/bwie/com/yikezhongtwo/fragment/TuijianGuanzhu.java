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
import bwie.com.yikezhongtwo.adapter.Remen_Tiaomu_Adapter;
import bwie.com.yikezhongtwo.bean.RemenTiaomuBean;
import bwie.com.yikezhongtwo.presenter.RV_Presenter;
import bwie.com.yikezhongtwo.presenter.Remen_RV_Presenter;
import bwie.com.yikezhongtwo.utils.Constant;
import okhttp3.ResponseBody;


/**
 * Created by 10419 on 2018/4/9.
 */

public class TuijianGuanzhu extends Fragment implements RV_Presenter {
    @BindView(R.id.tuijian_guanzhu_recycle)
    RecyclerView tuijianGuanzhuRecycle;
    Unbinder unbinder;
    @BindView(R.id.tuijianguanzhurefresh)
    SmartRefreshLayout tuijianguanzhurefresh;
    private Remen_RV_Presenter remen_rv_presenter;
    private RemenTiaomuBean remenTiaomuBean;
    private List<RemenTiaomuBean.DataBean> data;
    private List<RemenTiaomuBean.DataBean> list=new ArrayList<>();
    public int page=1;
    private Map<String, String> tiaomumap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View guanzhu = inflater.inflate(R.layout.tuijianguanzhi, container, false);

        unbinder = ButterKnife.bind(this, guanzhu);
        remen_rv_presenter = new Remen_RV_Presenter(this);
        tiaomumap = new HashMap<>();
        //tiaomumap.put("token", "3D6C0E4D376A8A6332525591200C13D4");
        remen_rv_presenter.getTiaomuUrl(Constant.REMEN_SHIPIN_URL, tiaomumap);
        tuijianGuanzhuRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tuijianGuanzhuRecycle.setNestedScrollingEnabled(false);
        tuijianguanzhurefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                page=1;
                tiaomumap.put("page",page+"");
                remen_rv_presenter.getTiaomuUrl(Constant.REMEN_SHIPIN_URL, tiaomumap);
                refreshlayout.finishRefresh(2000/*,false*/);
            }
        });
        tuijianguanzhurefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                tiaomumap.put("page",page+"");
                remen_rv_presenter.getTiaomuUrl(Constant.REMEN_SHIPIN_URL, tiaomumap);
                refreshlayout.finishLoadmore(2000/*,false*/);
            }
        });
        return guanzhu;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void TiaomuDataOnSuccess(ResponseBody responseBody) {
        try {
            String tiaomu = responseBody.string();
            remenTiaomuBean = new Gson().fromJson(tiaomu, RemenTiaomuBean.class);
            data = remenTiaomuBean.getData();
            list.addAll(data);
            Remen_Tiaomu_Adapter remen_tiaomu_adapter = new Remen_Tiaomu_Adapter(list, getContext());

            tuijianGuanzhuRecycle.setAdapter(remen_tiaomu_adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void TiaomuError(Throwable throwable) {

    }
}
