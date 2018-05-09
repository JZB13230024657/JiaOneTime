package bwie.com.yikezhongtwo.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.bean.DengluBean;
import bwie.com.yikezhongtwo.presenter.DengluPre;
import bwie.com.yikezhongtwo.presenter.DengluPresenter;
import bwie.com.yikezhongtwo.utils.CommonUtils;
import bwie.com.yikezhongtwo.utils.Constant;
import okhttp3.ResponseBody;

public class TarenXiangqing extends AppCompatActivity implements DengluPre{

    @BindView(R.id.jiaguanzhu)
    CheckBox jiaguanzhu;
    @BindView(R.id.tarenxinxi_fanhui)
    ImageView tarenxinxiFanhui;
    @BindView(R.id.tarenxinxi_name)
    TextView tarenxinxiName;
    @BindView(R.id.tarenxinxi_fenxiang)
    ImageView tarenxinxiFenxiang;
    @BindView(R.id.tarenxinxi_xinxi)
    ImageView tarenxinxiXinxi;
    @BindView(R.id.tarenxinxi_touxiang)
    ImageView tarenxinxiTouxiang;
    @BindView(R.id.tarenxinxi_dianzan)
    ImageView tarenxinxiDianzan;
    @BindView(R.id.recycler_kongjian)
    RecyclerView recyclerKongjian;
    private DengluPresenter dengluPresenter;
    private String followid;
    private String icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taren_xiangqing);
        ButterKnife.bind(this);
        dengluPresenter = new DengluPresenter(this);
        Intent intent=getIntent();
        followid = intent.getStringExtra("uid");
        icon = intent.getStringExtra("icon");
        Glide.with(TarenXiangqing.this).load(icon).transform(new GlideCircleTransform(TarenXiangqing.this)).into(tarenxinxiTouxiang);
    }

    @OnClick({R.id.jiaguanzhu, R.id.tarenxinxi_fanhui, R.id.tarenxinxi_name, R.id.tarenxinxi_fenxiang, R.id.tarenxinxi_xinxi, R.id.tarenxinxi_touxiang, R.id.tarenxinxi_dianzan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jiaguanzhu:
            if (jiaguanzhu.isChecked()){
                jiaguanzhu.setTextColor(Color.WHITE);
                jiaguanzhu.setText("已关注");
                Map<String,String>map=new HashMap<>();
                map.put("followId",followid);
                Toast.makeText(TarenXiangqing.this,"id="+followid,Toast.LENGTH_SHORT).show();
                dengluPresenter.getDengluData(Constant.GUANZHU_URL,map);
            }else {
                jiaguanzhu.setTextColor(Color.BLUE);
                jiaguanzhu.setText("+关注");
            }
                break;
            case R.id.tarenxinxi_fanhui:
                finish();
                break;
            case R.id.tarenxinxi_name:
                break;
            case R.id.tarenxinxi_fenxiang:
                break;
            case R.id.tarenxinxi_xinxi:
                break;
            case R.id.tarenxinxi_touxiang:
                break;
            case R.id.tarenxinxi_dianzan:
                break;
        }
    }

    @Override
    public void getDengluSuccess(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            DengluBean dengluBean = new Gson().fromJson(string, DengluBean.class);
            String code = dengluBean.getCode();
            if(code.equals("1")){
                Toast.makeText(TarenXiangqing.this,dengluBean.getMsg(),Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(TarenXiangqing.this,dengluBean.getMsg(),Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDengluError(Throwable throwable) {

    }
}
