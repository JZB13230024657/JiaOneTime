package bwie.com.yikezhongtwo.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.utils.ShareUtil;
import bwie.com.yikezhongtwo.view.DivergeViewSecond;
import cn.jzvd.JZVideoPlayerStandard;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

public class ShipinXiangqing extends AppCompatActivity {

    @BindView(R.id.xingqingvideo)
    JZVideoPlayerStandard xingqingvideo;
    @BindView(R.id.xingqingshow)
    TextView xingqingshow;
    @BindView(R.id.shipin_xiangqing_fanhui)
    ImageView shipinXiangqingFanhui;
    @BindView(R.id.shipin_xiangqing_zan)
    ImageView shipinXiangqingZan;
    @BindView(R.id.shipin_xiangqing_cai)
    ImageView shipinXiangqingCai;
    @BindView(R.id.shipin_xiangqing_fenxiang)
    ImageView shipinXiangqingFenxiang;
    @BindView(R.id.shipin_xiangqing_touxiang)
    ImageView shipinXiangqingTouxiang;
    @BindView(R.id.tu)
    ImageView tu;
    @BindView(R.id.danmukongzhi)
    Switch danmukongzhi;
    private boolean showDanmaku;

    private DanmakuView danmakuView;

    private DanmakuContext danmakuContext;

    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };
    private DivergeViewSecond mDivergeView;
    private List<Bitmap> mList = new ArrayList<Bitmap>();
    private int mIndex = 0;
    private String followId;
    private String icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipin_xiangqing);
        ButterKnife.bind(this);

        mList = new ArrayList<>();
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.a2, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.a2, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.a2, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.a2, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.a2, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.a2, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.a2, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.a2, null)).getBitmap());
        //mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.a10, null)).getBitmap());
        Intent intent = getIntent();
        followId = intent.getStringExtra("followId");
        String video = intent.getStringExtra("video");
        icon = intent.getStringExtra("icon");
        String showtext = intent.getStringExtra("showtext");
        String cover = intent.getStringExtra("cover");
        Glide.with(ShipinXiangqing.this).load(icon).transform(new GlideCircleTransform(ShipinXiangqing.this)).into(shipinXiangqingTouxiang);
        xingqingvideo.setUp(video, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
        xingqingshow.setText(showtext);
        Glide.with(ShipinXiangqing.this).load(cover).into(xingqingvideo.thumbImageView);
        danmakuView = (DanmakuView) findViewById(R.id.danmaku_view);
        danmakuView.enableDanmakuDrawingCache(true);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                danmakuView.pause();
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = DanmakuContext.create();
        danmakuView.prepare(parser, danmakuContext);

        final LinearLayout operationLayout = (LinearLayout) findViewById(R.id.operation_layout);
        final Button send = (Button) findViewById(R.id.send);
        final EditText editText = (EditText) findViewById(R.id.edit_text);
        danmakuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (operationLayout.getVisibility() == View.GONE) {
                    operationLayout.setVisibility(View.VISIBLE);
                } else {
                    operationLayout.setVisibility(View.GONE);
                }
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    addDanmaku(content, true);
                    editText.setText("");
                }
            }
        });
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == View.SYSTEM_UI_FLAG_VISIBLE) {
                    onWindowFocusChanged(true);
                }
            }
        });
        mDivergeView = findViewById(R.id.divergeView);
        mDivergeView.post(new Runnable() {
            @Override
            public void run() {
                mDivergeView.setEndPoint(new PointF(mDivergeView.getMeasuredWidth() / 2, 0));
                mDivergeView.setDivergeViewProvider(new Provider());
            }
        });
        danmukongzhi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    danmakuView.resume();
                    danmakuView.setVisibility(View.VISIBLE);
                }else {
                    danmakuView.pause();
                    danmakuView.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 向弹幕View中添加一条弹幕
     *
     * @param content    弹幕的具体内容
     * @param withBorder 弹幕是否有边框
     */
    private void addDanmaku(String content, boolean withBorder) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = sp2px(20);
        danmaku.textColor = Color.WHITE;
        danmaku.setTime(danmakuView.getCurrentTime());
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        danmakuView.addDanmaku(danmaku);
    }

    /**
     * 随机生成一些弹幕内容以供测试
     */
    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (showDanmaku) {
                    int time = new Random().nextInt(300);
                    String content = "" + time + time;
                    addDanmaku(content, false);
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (danmakuView != null && danmakuView.isPrepared()) {
            danmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (danmakuView != null && danmakuView.isPrepared() && danmakuView.isPaused()) {
            danmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showDanmaku = false;
        if (danmakuView != null) {
            danmakuView.release();
            danmakuView = null;
        }
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }


    @OnClick({R.id.shipin_xiangqing_fanhui, R.id.shipin_xiangqing_zan, R.id.shipin_xiangqing_cai, R.id.shipin_xiangqing_fenxiang, R.id.shipin_xiangqing_touxiang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shipin_xiangqing_fanhui:
                finish();
                break;
            case R.id.shipin_xiangqing_zan:
                shipinXiangqingZan.setImageResource(R.drawable.a4);
                if (mIndex == 8) {
                    mIndex = 0;
                }
                mDivergeView.startDiverges(mIndex);
                mIndex++;
                break;
            case R.id.shipin_xiangqing_cai:
                break;
            case R.id.shipin_xiangqing_fenxiang:
                ShareUtil.shareWeb(this, "http://www.baidu.com"
                        , "这就是标题", "这就是描述"
                        , "http://img1.imgtn.bdimg.com/it/u=2764371306,3467823016&fm=214&gp=0.jpg"
                        , R.mipmap.ic_launcher, SHARE_MEDIA.QQ);
                break;
            case R.id.shipin_xiangqing_touxiang:
                Intent intent = new Intent(ShipinXiangqing.this, TarenXiangqing.class);
                intent.putExtra("uid",followId);
                intent.putExtra("icon",icon);
                startActivity(intent);
                break;
        }
    }

    class Provider implements DivergeViewSecond.DivergeViewProvider {

        @Override
        public Bitmap getBitmap(Object obj) {
            return mList == null ? null : mList.get((int) obj);
        }
    }
}
