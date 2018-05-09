package bwie.com.yikezhongtwo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwie.com.yikezhongtwo.R;

/**
 * Created by 10419 on 2018/4/9.
 */

public class TuiJian extends Fragment {


    @BindView(R.id.tuijian_tablayout)
    TabLayout tuijianTablayout;
    @BindView(R.id.tuijian_viewpager)
    ViewPager tuijianViewpager;
    Unbinder unbinder;
    private String[] url = {"热门","关注"};
    private FragmentManager fragmentManager;
    private List<Fragment> fragments;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View tuijian = inflater.inflate(R.layout.tuijian, container, false);
        unbinder = ButterKnife.bind(this, tuijian);
        for(int i = 0;i<url.length;i++){
            tuijianTablayout.addTab(tuijianTablayout.newTab().setText(url[i]));
        }
        tuijianTablayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fragmentManager = getActivity().getSupportFragmentManager();
        fragments = new ArrayList<>();
        TuijianRemen tuijianRemen = new TuijianRemen();
        TuijianGuanzhu tuijianGuanzhu = new TuijianGuanzhu();
        fragments.add(tuijianRemen);
        fragments.add(tuijianGuanzhu );
        //设置TabLayout的模式
        tuijianTablayout.setTabMode(TabLayout.MODE_FIXED);
        //让tablayout和Viewpager关联;
        tuijianTablayout.setupWithViewPager(tuijianViewpager);

        tuijianViewpager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
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
        return tuijian;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
