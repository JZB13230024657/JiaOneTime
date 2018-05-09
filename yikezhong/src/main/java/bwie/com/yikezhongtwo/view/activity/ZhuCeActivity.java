package bwie.com.yikezhongtwo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.bean.ZhuceBean;
import bwie.com.yikezhongtwo.presenter.ZhuCePre;
import bwie.com.yikezhongtwo.presenter.ZhucePresenter;
import bwie.com.yikezhongtwo.utils.Constant;
import okhttp3.ResponseBody;

public class ZhuCeActivity extends AppCompatActivity implements ZhuCePre{

    @BindView(R.id.zhuce_fanhui)
    ImageView zhuceFanhui;
    @BindView(R.id.yiyou_zhanghao)
    TextView yiyouZhanghao;
    @BindView(R.id.zhuce_zhanghao)
    EditText zhuceZhanghao;
    @BindView(R.id.zhuce_mima)
    EditText zhuceMima;
    @BindView(R.id.zhuce)
    Button zhuce;
    @BindView(R.id.zhuce_youkedenglu)
    TextView zhuceYoukedenglu;
    private ZhucePresenter zhucePresenter;
    private String mima;
    private String zhanghao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        ButterKnife.bind(this);
        zhucePresenter = new ZhucePresenter(this);

    }

    @OnClick({R.id.zhuce_fanhui, R.id.yiyou_zhanghao, R.id.zhuce, R.id.zhuce_youkedenglu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuce_fanhui:
                finish();
                break;
            case R.id.yiyou_zhanghao:
                finish();
                break;
            case R.id.zhuce:
                mima = zhuceMima.getText().toString();
                zhanghao = zhuceZhanghao.getText().toString();
                Map<String,String>map=new HashMap<>();
                map.put("mobile",zhanghao);
                map.put("password",mima);
                zhucePresenter.getZhuceData(Constant.ZHUCE_URL,map);
                break;
            case R.id.zhuce_youkedenglu:
                Intent intent=new Intent(ZhuCeActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getZhuceSuccess(ResponseBody responseBody) {
        try {
            String zhuce = responseBody.string();
            ZhuceBean zhuceBean = new Gson().fromJson(zhuce, ZhuceBean.class);
            String code = zhuceBean.getCode();
            int i = Integer.parseInt(code);
            if (i==1){
                Toast.makeText(ZhuCeActivity.this,zhuceBean.getMsg(),Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(ZhuCeActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ZhuCeActivity.this,ShouJiDengluActivity.class);
                startActivity(intent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getZhuceError(Throwable throwable) {

    }
}
