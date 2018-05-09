package bwie.com.yikezhongtwo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.yikezhongtwo.R;

public class Chuangzuo_Activity extends AppCompatActivity {

    @BindView(R.id.quxiao)
    TextView quxiao;
    @BindView(R.id.fashipin)
    ImageView fashipin;
    @BindView(R.id.faduanzi)
    ImageView faduanzi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuangzuo_);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.quxiao, R.id.fashipin, R.id.faduanzi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quxiao:
                finish();
                break;
            case R.id.fashipin:
                break;
            case R.id.faduanzi:
                Intent intent=new Intent(Chuangzuo_Activity.this,FaDuanziActivity.class);
                startActivity(intent);
                break;
        }
    }
}
