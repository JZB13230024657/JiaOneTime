package bwie.com.yikezhongtwo.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import bwie.com.yikezhongtwo.bean.DengluBean;
import bwie.com.yikezhongtwo.presenter.DengluPre;
import bwie.com.yikezhongtwo.presenter.DengluPresenter;
import bwie.com.yikezhongtwo.utils.CommonUtils;
import bwie.com.yikezhongtwo.utils.Constant;
import okhttp3.ResponseBody;

public class ShouJiDengluActivity extends AppCompatActivity implements DengluPre{

    @BindView(R.id.denglu_fanhui)
    ImageView dengluFanhui;
    @BindView(R.id.quzhuce)
    TextView quzhuce;
    @BindView(R.id.denglu_zhanghao)
    EditText dengluZhanghao;
    @BindView(R.id.denglu_mima)
    EditText dengluMima;
    @BindView(R.id.denglu)
    Button denglu;
    @BindView(R.id.wangjimima)
    TextView wangjimima;
    @BindView(R.id.denglu_youke)
    TextView dengluYouke;
    private DengluPresenter dengluPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_ji_denglu);
        ButterKnife.bind(this);
        dengluPresenter = new DengluPresenter(this);
    }


    @OnClick({R.id.denglu_fanhui, R.id.quzhuce, R.id.denglu, R.id.wangjimima, R.id.denglu_youke})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.denglu_fanhui:
                finish();
                break;
            case R.id.quzhuce:
                Intent intent=new Intent(ShouJiDengluActivity.this,ZhuCeActivity.class);
                startActivity(intent);
                break;
            case R.id.denglu:
                String mima = dengluMima.getText().toString();
                String zhanghao = dengluZhanghao.getText().toString();
                Map<String,String>map=new HashMap<>();
                map.put("mobile",zhanghao);
                map.put("password",mima);
                dengluPresenter.getDengluData(Constant.DENGLU_URL,map);
                break;
            case R.id.wangjimima:
                Intent intent1=new Intent(ShouJiDengluActivity.this,WangjiMima_Activity.class);
                startActivity(intent1);
                break;
            case R.id.denglu_youke:
                Intent intent2=new Intent(ShouJiDengluActivity.this,MainActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void getDengluSuccess(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            DengluBean dengluBean = new Gson().fromJson(string, DengluBean.class);
            String code = dengluBean.getCode();
            CommonUtils.saveString("name",dengluBean.getData().getNickname());
            CommonUtils.saveString("uid",dengluBean.getData().getUid());
            CommonUtils.saveString("token",dengluBean.getData().getToken());
            CommonUtils.saveString("icon",dengluBean.getData().getIcon());
            CommonUtils.saveString("mima",dengluBean.getData().getPassword());
            CommonUtils.putBoolean("flag",true);


            int i = Integer.parseInt(code);
            if (i==1){
                Toast.makeText(ShouJiDengluActivity.this,dengluBean.getMsg(),Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(ShouJiDengluActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                Intent intent3=new Intent(ShouJiDengluActivity.this,MainActivity.class);
                startActivity(intent3);
                finish();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDengluError(Throwable throwable) {

    }
}
