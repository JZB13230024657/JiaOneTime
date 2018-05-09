package bwie.com.yikezhongtwo.fragment;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.adapter.ShipinAdapter;
import bwie.com.yikezhongtwo.bean.ShipinBean;
import bwie.com.yikezhongtwo.presenter.ShipinPre;
import bwie.com.yikezhongtwo.presenter.Shipin_Presenter;
import bwie.com.yikezhongtwo.utils.Constant;
import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/11.
 */

public class ShipinFujin extends Fragment implements ShipinPre {
    @BindView(R.id.shipin_fujin_recycle)
    RecyclerView shipinFujinRecycle;
    Unbinder unbinder;
    private List<ShipinBean.DataBean>list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipin_fujin, container, false);
        unbinder = ButterKnife.bind(this, view);
        shipinFujinRecycle.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        Shipin_Presenter shipin_presenter = new Shipin_Presenter(this);
        Map<String,String> map=new HashMap<>();
       // map.put("token", "3D6C0E4D376A8A6332525591200C13D4");
        shipin_presenter.getShipinData(Constant.SHIPIN_REMEN_URL,map);
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
            String shipin = responseBody.string();

            Log.e("qqqq",shipin);
            ShipinBean shipinBean = new Gson().fromJson(shipin, ShipinBean.class);
            List<ShipinBean.DataBean> data = shipinBean.getData();
            list.addAll(data);
            ShipinAdapter shipinAdapter = new ShipinAdapter(list, getActivity());
            shipinFujinRecycle.setAdapter(shipinAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getShipinError(Throwable throwable) {

    }
}
