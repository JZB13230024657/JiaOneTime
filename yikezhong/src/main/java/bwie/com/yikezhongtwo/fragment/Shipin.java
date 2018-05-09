package bwie.com.yikezhongtwo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import bwie.com.yikezhongtwo.R;


/**
 * Created by 10419 on 2018/4/9.
 */

public class Shipin extends Fragment {
    @BindView(R.id.shipin_remen)
    RadioButton shipinRemen;
    @BindView(R.id.shipin_fujin)
    RadioButton shipinFujin;
    Unbinder unbinder;
    @BindView(R.id.shipin_child_frame)
    FrameLayout shipinChildFrame;
    private FragmentManager childFragmentManager;
    private ShipinRemen shipinRemen1;
    private ShipinFujin shipinFujin1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View shipin = inflater.inflate(R.layout.shipin, container, false);

        unbinder = ButterKnife.bind(this, shipin);

        shipinRemen1 = new ShipinRemen();
        shipinFujin1 = new ShipinFujin();

        childFragmentManager = getChildFragmentManager();
        FragmentTransaction add1 = childFragmentManager.beginTransaction().add(R.id.shipin_child_frame, shipinRemen1);
        FragmentTransaction add2 = childFragmentManager.beginTransaction().add(R.id.shipin_child_frame, shipinFujin1);
        add1.commit();
        add2.commit();
        FragmentTransaction hide = childFragmentManager.beginTransaction().show(shipinRemen1).hide(shipinFujin1);
        hide.commit();
        return shipin;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.shipin_remen, R.id.shipin_fujin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shipin_remen:
                FragmentTransaction hide1 = childFragmentManager.beginTransaction().show(shipinRemen1).hide(shipinFujin1);
                hide1.commit();
                shipinRemen.setTextColor(Color.BLUE);
                shipinFujin.setTextColor(Color.BLACK);
                break;
            case R.id.shipin_fujin:
                FragmentTransaction hide2 = childFragmentManager.beginTransaction().show(shipinFujin1).hide(shipinRemen1);
                hide2.commit();
                shipinRemen.setTextColor(Color.BLACK);
                shipinFujin.setTextColor(Color.BLUE);
                break;
        }
    }
}
