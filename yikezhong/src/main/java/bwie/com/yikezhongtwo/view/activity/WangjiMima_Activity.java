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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.yikezhongtwo.R;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class WangjiMima_Activity extends AppCompatActivity {

    @BindView(R.id.wangjimima_jiantou)
    ImageView wangjimimaJiantou;
    @BindView(R.id.yiyou_zhanghao)
    TextView yiyouZhanghao;
    @BindView(R.id.shouji_hao)
    EditText shoujiHao;
    @BindView(R.id.huqu_yanzhengma)
    TextView huquYanzhengma;
    @BindView(R.id.yanzheng_ma)
    EditText yanzhengMa;
    @BindView(R.id.tijiao_xiayibu)
    Button tijiaoXiayibu;
    @BindView(R.id.wangjimima_youkedenglu)
    TextView wangjimimaYoukedenglu;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wangji_mima_);
        ButterKnife.bind(this);

    }
    public void sendCode(String country, String phone) {
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理成功得到验证码的结果
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                } else{
                    // TODO 处理错误的结果
                }

            }
        });
        // 触发操作
        SMSSDK.getVerificationCode(country, phone);
    }

    // 提交验证码，其中的code表示验证码，如“1357”
    public void submitCode(String country, String phone, String code) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理验证成功的结果

                } else{
                    // TODO 处理错误的结果
                    Toast.makeText(WangjiMima_Activity.this,"验证码错误,验证失败",Toast.LENGTH_SHORT).show();
                }

            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }

    @OnClick({R.id.wangjimima_jiantou, R.id.yiyou_zhanghao, R.id.huqu_yanzhengma, R.id.tijiao_xiayibu, R.id.wangjimima_youkedenglu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wangjimima_jiantou:
                finish();
                break;
            case R.id.yiyou_zhanghao:
                Intent intent=new Intent(WangjiMima_Activity.this,ShouJiDengluActivity.class);
                startActivity(intent);
                break;
            case R.id.huqu_yanzhengma:
                String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
                Pattern p = Pattern.compile(regExp);
                phone = shoujiHao.getText().toString();
                Matcher m = p.matcher(phone);
                if (phone.equals("")){
                    Toast.makeText(WangjiMima_Activity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }else if(m.find()){
                    sendCode(86+"", phone);
                    Toast.makeText(WangjiMima_Activity.this,"验证码获取成功",Toast.LENGTH_SHORT).show();
                }  else{
                    Toast.makeText(WangjiMima_Activity.this,"手机号不正确",Toast.LENGTH_SHORT).show();
            }
                break;
            case R.id.tijiao_xiayibu:
                String code = yanzhengMa.getText().toString();
                if (code.equals("")){
                    Toast.makeText(WangjiMima_Activity.this,"验证码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    submitCode(86+"", phone,code);
                    Toast.makeText(WangjiMima_Activity.this,"验证成功",Toast.LENGTH_SHORT).show();
                    Intent intent2=new Intent(WangjiMima_Activity.this,XiugaiMimaActivity.class);
                    startActivity(intent2);
                }

                break;
            case R.id.wangjimima_youkedenglu:
                Intent intent1=new Intent(WangjiMima_Activity.this,MainActivity.class);
                startActivity(intent1);
                break;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
    }
}
