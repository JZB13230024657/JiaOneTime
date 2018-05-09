package bwie.com.yikezhongtwo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.adapter.Remen_Tiaomu_Adapter;
import bwie.com.yikezhongtwo.adapter.Shoucang_Liebiao_Adapter;
import bwie.com.yikezhongtwo.bean.RemenTiaomuBean;
import bwie.com.yikezhongtwo.bean.ShoucangLiebiaoBean;
import bwie.com.yikezhongtwo.presenter.DengluPre;
import bwie.com.yikezhongtwo.presenter.GuanzhuPresenter;
import bwie.com.yikezhongtwo.utils.CommonUtils;
import bwie.com.yikezhongtwo.utils.Constant;
import okhttp3.ResponseBody;

public class WodeShoucangActivity extends AppCompatActivity implements DengluPre{

    @BindView(R.id.wodeshoucang_jiantou)
    ImageView wodeshoucangJiantou;
    @BindView(R.id.wodeshoucang_fanhui)
    TextView wodeshoucangFanhui;
    @BindView(R.id.wodeshoucang_shanchu)
    TextView wodeshoucangShanchu;
    @BindView(R.id.wodeshoucang_recycle)
    RecyclerView wodeshoucangRecycle;
    private GuanzhuPresenter guanzhuPresenter;
    private ShoucangLiebiaoBean shoucangLiebiaoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode_shoucang);
        ButterKnife.bind(this);
        wodeshoucangRecycle.setLayoutManager(new LinearLayoutManager(WodeShoucangActivity.this, LinearLayoutManager.VERTICAL, false));
        Map<String,String> map=new HashMap<>();
        guanzhuPresenter = new GuanzhuPresenter(this);
        guanzhuPresenter.getData(Constant.WODE_SHOUCANG_LIEBIAO_URL,map);
    }

    @OnClick({R.id.wodeshoucang_jiantou, R.id.wodeshoucang_fanhui, R.id.wodeshoucang_shanchu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wodeshoucang_jiantou:
                finish();
                break;
            case R.id.wodeshoucang_fanhui:
                finish();
                break;
            case R.id.wodeshoucang_shanchu:
                break;
        }
    }

    @Override
    public void getDengluSuccess(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            Log.e("responseBody", string);
            shoucangLiebiaoBean = new Gson().fromJson(string, ShoucangLiebiaoBean.class);
            List<ShoucangLiebiaoBean.DataBean> data = shoucangLiebiaoBean.getData();
            Shoucang_Liebiao_Adapter shoucang_liebiao_adapter = new Shoucang_Liebiao_Adapter(data, WodeShoucangActivity.this);
            wodeshoucangRecycle.setAdapter(shoucang_liebiao_adapter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDengluError(Throwable throwable) {

    }
}
