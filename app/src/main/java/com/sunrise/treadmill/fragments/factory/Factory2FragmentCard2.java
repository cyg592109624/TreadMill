package com.sunrise.treadmill.fragments.factory;

import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.dialog.factory.Factory2FragmentCard2Dialog;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class Factory2FragmentCard2 extends BaseFragment {
    @BindView(R.id.factory2_card2_1_reset)
    ImageView reSet;

    @BindView(R.id.factory2_card2_1_total_time_value)
    TextView totalTime;

    @BindView(R.id.factory2_card2_1_total_distance_value)
    TextView totalDistance;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_factory2_card_2;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_1_total_time));
        txtList.add(totalTime);

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_1_total_distance));
        txtList.add(totalDistance);

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_2_ver_sdk));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_2_ver_sdk_value));

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_2_ver_firmware));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_2_ver_firmware_value));

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_2_ver_soft));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_2_ver_soft_value));

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(getContext()));
        }
    }

    @OnClick(R.id.factory2_card2_1_reset)
    public void reSetValue() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Factory2FragmentCard2Dialog dialog = new Factory2FragmentCard2Dialog();
        dialog.show(fragmentManager, Factory2FragmentCard2Dialog.TAG);
    }
}
