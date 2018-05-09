package bwie.com.yikezhongtwo.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.yikezhongtwo.R;
import bwie.com.yikezhongtwo.fragment.BendiZuopin;
import bwie.com.yikezhongtwo.fragment.YishangChuan;

public class WodeZuopinActivity extends AppCompatActivity {

    @BindView(R.id.wodezuopin_jiantou)
    ImageView wodezuopinJiantou;
    @BindView(R.id.wodezuopin_fanhui)
    TextView wodezuopinFanhui;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private String[] url = {"本地视频","已上传"};
    private FragmentManager fragmentManager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode_zuopin);
        ButterKnife.bind(this);
        for(int i = 0;i<url.length;i++){
            tablayout.addTab(tablayout.newTab().setText(url[i]));
        }
        fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        BendiZuopin bendiZuopin = new BendiZuopin();
        YishangChuan yishangChuan = new YishangChuan();
        fragments.add(bendiZuopin);
        fragments.add(yishangChuan);
        viewpager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return url[position];
            }
        });
    }

    @OnClick({R.id.wodezuopin_jiantou, R.id.wodezuopin_fanhui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wodezuopin_jiantou:
                finish();
                break;
            case R.id.wodezuopin_fanhui:
                finish();
                break;
        }
    }
}
