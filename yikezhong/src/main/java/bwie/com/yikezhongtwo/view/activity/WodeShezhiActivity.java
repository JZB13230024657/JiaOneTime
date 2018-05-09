package bwie.com.yikezhongtwo.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.utils.CommonUtils;

public class WodeShezhiActivity extends AppCompatActivity {

    @BindView(R.id.shezhi_jiantou)
    ImageView shezhiJiantou;
    @BindView(R.id.shezhi_fanhui)
    TextView shezhiFanhui;
    @BindView(R.id.jianchagengxin)
    LinearLayout jianchagengxin;
    @BindView(R.id.yijianfankui)
    LinearLayout yijianfankui;
    @BindView(R.id.guanyuyikezhong)
    LinearLayout guanyuyikezhong;
    @BindView(R.id.qingchuhuancun)
    LinearLayout qingchuhuancun;
    @BindView(R.id.tuichudenglu)
    Button tuichudenglu;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode_shezhi);
        ButterKnife.bind(this);
        preferences = getSharedPreferences("user", WodeShezhiActivity.MODE_PRIVATE);
    }

    @OnClick({R.id.shezhi_jiantou, R.id.shezhi_fanhui, R.id.jianchagengxin, R.id.yijianfankui, R.id.guanyuyikezhong, R.id.qingchuhuancun, R.id.tuichudenglu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shezhi_jiantou:
                finish();
                break;
            case R.id.shezhi_fanhui:
                finish();
                break;
            case R.id.jianchagengxin:
                break;
            case R.id.yijianfankui:
                break;
            case R.id.guanyuyikezhong:
                break;
            case R.id.qingchuhuancun:
                break;
            case R.id.tuichudenglu:
                CommonUtils.clearSp("name");
                CommonUtils.clearSp("uid");
                CommonUtils.clearSp("token");
                CommonUtils.clearSp("icon");
                CommonUtils.clearSp("flag");
                finish();


                Intent intent=new Intent(WodeShezhiActivity.this,DengLuYeMian.class);
                startActivity(intent);
                break;
        }
    }
}
