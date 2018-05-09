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
import bwie.com.yikezhongtwo.bean.XiugaiBean;
import bwie.com.yikezhongtwo.presenter.DengluPre;
import bwie.com.yikezhongtwo.presenter.DengluPresenter;
import bwie.com.yikezhongtwo.utils.CommonUtils;
import bwie.com.yikezhongtwo.utils.Constant;
import okhttp3.ResponseBody;

public class XiugaiMimaActivity extends AppCompatActivity implements DengluPre{

    @BindView(R.id.xiugaimima_jiantou)
    ImageView xiugaimimaJiantou;
    @BindView(R.id.xiugai_mima_yiyou_zhanghao)
    TextView xiugaiMimaYiyouZhanghao;
    @BindView(R.id.xinmima)
    EditText xinmima;
    @BindView(R.id.xinmimatwo)
    EditText xinmimatwo;
    @BindView(R.id.xiugai_mima_wancheng)
    Button xiugaiMimaWancheng;
    @BindView(R.id.xiugaimima_youkedenglu)
    TextView xiugaimimaYoukedenglu;
    private DengluPresenter dengluPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugai_mima);
        ButterKnife.bind(this);
        dengluPresenter = new DengluPresenter(this);
    }

    @OnClick({R.id.xiugaimima_jiantou, R.id.xiugai_mima_yiyou_zhanghao, R.id.xiugai_mima_wancheng, R.id.xiugaimima_youkedenglu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xiugaimima_jiantou:
                finish();
                break;
            case R.id.xiugai_mima_yiyou_zhanghao:
                finish();
                break;
            case R.id.xiugai_mima_wancheng:
                String oldmima = CommonUtils.getString("mima");
                String xinmima = this.xinmima.getText().toString();
                String newmima = this.xinmimatwo.getText().toString();
                if (newmima.equals(xinmima)){
                    Map<String,String>map=new HashMap<>();
                    map.put("oldPassword",oldmima);
                    map.put("newPassword",newmima);
                    dengluPresenter.getDengluData(Constant.XIUGAI_MIMA_URL,map);
                }else {
                    Toast.makeText(XiugaiMimaActivity.this,"密码输入不一致",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.xiugaimima_youkedenglu:
                Intent intent=new Intent(XiugaiMimaActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getDengluSuccess(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            XiugaiBean xiugaiBean = new Gson().fromJson(string, XiugaiBean.class);
            String code = xiugaiBean.getCode();
            if (code.equals("0")){
                Toast.makeText(XiugaiMimaActivity.this,xiugaiBean.getMsg(),Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(XiugaiMimaActivity.this,xiugaiBean.getMsg(),Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDengluError(Throwable throwable) {

    }
}
