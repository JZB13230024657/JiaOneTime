package bwie.com.yikezhongtwo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

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

public class DengLuYeMian extends AppCompatActivity {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.qitadenglu)
    TextView qitadenglu;
    private DengluPresenter dengluPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng_lu_ye_mian);
        ButterKnife.bind(this);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.fanhui, R.id.qitadenglu,R.id.qqdenglu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.qitadenglu:
                final Intent intent=new Intent(DengLuYeMian.this,ShouJiDengluActivity.class);
                startActivity(intent);
                break;
                case R.id.qqdenglu:
                    UMShareAPI.get(this).getPlatformInfo(DengLuYeMian.this
                            , SHARE_MEDIA.QQ, new UMAuthListener() {
                                private String uid;

                                /**
                                 * @desc 授权开始的回调
                                 * @param platform 平台名称
                                 */
                                @Override
                                public void onStart(SHARE_MEDIA platform) {
                                }
                                /**
                                 * @desc 授权成功的回调
                                 * @param platform 平台名称
                                 * @param action 行为序号，开发者用不上
                                 * @param data 用户资料返回
                                 */
                                @Override
                                public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                                    Toast.makeText(DengLuYeMian.this, "成功了"+("id:"+data.get("uid")+",昵称:"+data.get("name")+",性别："+data.get("gender") +",头像:"+data.get("iconurl")), Toast.LENGTH_LONG).show();
                                    CommonUtils.saveString("icon",data.get("iconurl"));
                                    CommonUtils.saveString("uid",data.get("uid"));
                                    //CommonUtils.saveString("sex",dengluBean.getData().getGender());
                                    CommonUtils.saveString("name",data.get("name"));
                                    CommonUtils.putBoolean("flag",true);

                                    finish();
                                }
                                /**
                                 * @desc 授权失败的回调
                                 * @param platform 平台名称
                                 * @param action 行为序号，开发者用不上
                                 * @param t 错误原因
                                 */
                                @Override
                                public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                                    Toast.makeText(DengLuYeMian.this, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
                                }
                                /**
                                 * @desc 授权取消的回调
                                 * @param platform 平台名称
                                 * @param action 行为序号，开发者用不上
                                 */
                                @Override
                                public void onCancel(SHARE_MEDIA platform, int action) {
                                    Toast.makeText(DengLuYeMian.this, "取消了", Toast.LENGTH_LONG).show();
                                }
                            });

                break;
        }
    }


}
