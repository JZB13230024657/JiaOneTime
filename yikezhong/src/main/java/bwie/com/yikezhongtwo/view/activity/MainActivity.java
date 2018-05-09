package bwie.com.yikezhongtwo.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.fragment.Duanzi;
import bwie.com.yikezhongtwo.fragment.Shipin;
import bwie.com.yikezhongtwo.fragment.TuiJian;
import bwie.com.yikezhongtwo.presenter.DengluPre;
import bwie.com.yikezhongtwo.presenter.DengluPresenter;
import bwie.com.yikezhongtwo.utils.CommonUtils;
import bwie.com.yikezhongtwo.utils.Constant;
import bwie.com.yikezhongtwo.utils.GlideCrcularPhoto;
import bwie.com.yikezhongtwo.view.CircleDrawable;
import bwie.com.yikezhongtwo.view.NoSlidingViewPaper;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.touxiang)
    ImageView touxiang;
    @BindView(R.id.biaoti)
    TextView biaoti;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.deawer_left)
    LinearLayout deawerLeft;
    @BindView(R.id.drawer_touxiang)
    ImageView drawerTouxiang;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.chuangzuo)
    ImageView chuangzuo;
    @BindView(R.id.vp_main_container)
    NoSlidingViewPaper vpMainContainer;
    @BindView(R.id.wodeguanzhu)
    LinearLayout wodeguanzhu;
    @BindView(R.id.wodeshoucang)
    LinearLayout wodeshoucang;
    @BindView(R.id.sousuohaoyou)
    LinearLayout sousuohaoyou;
    @BindView(R.id.xiaoxitongzhi)
    LinearLayout xiaoxitongzhi;
    @BindView(R.id.yejianmoshi)
    LinearLayout yejianmoshi;
    @BindView(R.id.wodezuopin)
    LinearLayout wodezuopin;
    @BindView(R.id.shezhi)
    LinearLayout shezhi;
    private NoSlidingViewPaper mViewPager;

    //底部菜单栏各个菜单项的点击事件处理
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            resetToDefaultIcon();//重置到默认不选中图片
            switch (item.getItemId()) {
                case R.id.navigation_home://主页
                    mViewPager.setCurrentItem(0);
                    item.setIcon(R.drawable.raw_1500085367);
                    biaoti.setText("推荐");
                    return true;
                case R.id.navigation_message://信息
                    item.setIcon(R.drawable.raw_1500085899);
                    biaoti.setText("段子");
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_publish://发布
                    item.setIcon(R.drawable.raw_1500086067);
                    biaoti.setText("视频");
                    mViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

        private void resetToDefaultIcon() {
            MenuItem home = navigation.getMenu().findItem(R.id.navigation_home);
            home.setIcon(R.drawable.raw_1500083878);
            MenuItem message = navigation.getMenu().findItem(R.id.navigation_message);
            message.setIcon(R.drawable.raw_1500085327);
            MenuItem publish = navigation.getMenu().findItem(R.id.navigation_publish);
            publish.setIcon(R.drawable.raw_1500083686);
        }

    };
    private BottomNavigationView navigation;
    private Intent intent6;

    protected static Uri tempUri;
    private String uid;
    private String icon;
    private boolean flag;
    private String name;
    // 默认是日间模式
    private int theme = R.style.AppTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否有主题存储
        if(savedInstanceState != null){
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.touxiang);
        CircleDrawable drawable = new CircleDrawable(getResources(), bitmap);
        touxiang.setImageDrawable(drawable);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.touxiang);
        CircleDrawable drawable1 = new CircleDrawable(getResources(), bitmap1);
        drawerTouxiang.setImageDrawable(drawable1);
         /*初始化显示内容*/
        mViewPager = (NoSlidingViewPaper) findViewById(R.id.vp_main_container);
        final ArrayList<Fragment> fgLists = new ArrayList<>(3);
        fgLists.add(new TuiJian());
        fgLists.add(new Duanzi());
        fgLists.add(new Shipin());
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fgLists.get(position);
            }

            @Override
            public int getCount() {
                return fgLists.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(2); //预加载剩下两页

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        /*给底部导航栏菜单项添加点击事件*/
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View content = drawer.getChildAt(0);
                View menu = drawerView;
                float scale = 1 - slideOffset;//1~0
                content.setTranslationX(menu.getMeasuredWidth() * (1 - scale));//0~width
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        intent6 = new Intent(MainActivity.this,DengLuYeMian.class);


    }

    @Override
    protected void onStart() {
        super.onStart();

        uid = CommonUtils.getString("uid");
        icon = CommonUtils.getString("icon");
        flag = CommonUtils.getBoolean("flag");
        name = CommonUtils.getString("name");


    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean   flag = CommonUtils.getBoolean("flag");
        if (flag) {
            String icon = CommonUtils.getString("icon");
              Log.d("zxc",icon);
            GlideCrcularPhoto.getCrularUrl(MainActivity.this, icon,drawerTouxiang );
            GlideCrcularPhoto.getCrularUrl(MainActivity.this, icon,touxiang );
            nickname.setText(name);
        } else {
            GlideCrcularPhoto.getCrular(MainActivity.this, R.mipmap.ic_launcher_round, drawerTouxiang);
            GlideCrcularPhoto.getCrular(MainActivity.this, R.mipmap.ic_launcher_round, touxiang);
        }


    }

    @OnClick({R.id.touxiang, R.id.chuangzuo, R.id.drawer_touxiang, R.id.wodeguanzhu, R.id.wodeshoucang, R.id.sousuohaoyou, R.id.xiaoxitongzhi, R.id.yejianmoshi, R.id.wodezuopin, R.id.shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.touxiang:
                drawer.openDrawer(deawerLeft);
                break;
            case R.id.chuangzuo:
                Intent intent1=new Intent(MainActivity.this,Chuangzuo_Activity.class);
                startActivity(intent1);
                break;
            case R.id.drawer_touxiang:
                if (flag==false){
                    startActivity(intent6);
                }else {
                    Intent intent5 = new Intent(MainActivity.this, GerenXinxiActivity.class);
                    intent5.putExtra("icon",icon);
                    startActivity(intent5);
                }
                break;
            case R.id.wodeguanzhu:
                if (flag==false){
                    startActivity(intent6);
                }else {
                    Intent intent3 = new Intent(MainActivity.this, WodeGuanzhuActivity.class);
                    startActivity(intent3);
                }
                break;
            case R.id.wodeshoucang:
                if (flag==false){
                    startActivity(intent6);
                }
                Intent intent4 = new Intent(MainActivity.this, WodeShoucangActivity.class);
                startActivity(intent4);
                break;
            case R.id.sousuohaoyou:
                if (flag==false){
                    startActivity(intent6);
                }else {
                    Intent intent5 = new Intent(MainActivity.this, SousuoHaoyouActivity.class);
                    startActivity(intent5);
                }
                break;
            case R.id.xiaoxitongzhi:
                if (flag==false){
                    startActivity(intent6);
                }
                break;
            case R.id.yejianmoshi:
                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                MainActivity.this.recreate();
                break;
            case R.id.wodezuopin:
                if (flag==false){
                    startActivity(intent6);
                }else {
                    Intent intent7 = new Intent(MainActivity.this, WodeZuopinActivity.class);
                    startActivity(intent7);
                }
                break;
            case R.id.shezhi:
                if (flag==false){
                    startActivity(intent6);
                }else {
                    Intent intent7 = new Intent(MainActivity.this, WodeShezhiActivity.class);
                    startActivity(intent7);
                }
                break;
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }
}
