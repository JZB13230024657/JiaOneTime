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
import bwie.com.yikezhongtwo.adapter.WodeGuanzhuAdapter;
import bwie.com.yikezhongtwo.bean.GuanzhuLiebiaoBean;
import bwie.com.yikezhongtwo.presenter.DengluPre;
import bwie.com.yikezhongtwo.presenter.GuanzhuPresenter;
import bwie.com.yikezhongtwo.utils.CommonUtils;
import bwie.com.yikezhongtwo.utils.Constant;
import okhttp3.ResponseBody;

public class WodeGuanzhuActivity extends AppCompatActivity implements DengluPre{

    @BindView(R.id.guanzhu_jiantou)
    ImageView guanzhuJiantou;
    @BindView(R.id.guanzhu_fanhui)
    TextView guanzhuFanhui;
    @BindView(R.id.remen_guanzhu)
    TextView remenGuanzhu;
    @BindView(R.id.guanzhu_recycle)
    RecyclerView guanzhuRecycle;
    private GuanzhuPresenter guanzhuPresenter;
    private List<GuanzhuLiebiaoBean.DataBean> data;
    private WodeGuanzhuAdapter wodeGuanzhuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode_guanzhu);
        ButterKnife.bind(this);
        guanzhuRecycle.setLayoutManager(new LinearLayoutManager(WodeGuanzhuActivity.this,LinearLayoutManager.VERTICAL,false));
        guanzhuPresenter = new GuanzhuPresenter(this);
        Map<String,String>map=new HashMap<>();
        guanzhuPresenter.getData(Constant.WODE_GUANZHU_LIEBIAO_URL,map);
    }

    @OnClick({R.id.guanzhu_jiantou, R.id.guanzhu_fanhui, R.id.remen_guanzhu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guanzhu_jiantou:
                finish();
                break;
            case R.id.guanzhu_fanhui:
                finish();
                break;
            case R.id.remen_guanzhu:
                break;
        }
    }

    @Override
    public void getDengluSuccess(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            Log.e("responseBody",string);
            GuanzhuLiebiaoBean guanzhuLiebiaoBean = new Gson().fromJson(string, GuanzhuLiebiaoBean.class);
            data = guanzhuLiebiaoBean.getData();
            wodeGuanzhuAdapter = new WodeGuanzhuAdapter(data, WodeGuanzhuActivity.this);
            guanzhuRecycle.setAdapter(wodeGuanzhuAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDengluError(Throwable throwable) {

    }
}
