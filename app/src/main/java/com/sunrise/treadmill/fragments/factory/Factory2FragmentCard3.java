package com.sunrise.treadmill.fragments.factory;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class Factory2FragmentCard3 extends BaseFragment {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_factory2_card_3;
    }

    @Override
    public void recycleObject() {
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();

        txtList.add((TextView) parentView.findViewById(R.id.factory2_card3_1_usb));
        txtList.add((TextView) parentView.findViewById(R.id.factory2_card3_2_updating));
        txtList.add((TextView) parentView.findViewById(R.id.factory2_card3_2_not_shut_down));
        txtList.add((TextView) parentView.findViewById(R.id.factory2_card3_3_download_failed));

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(getContext()));
        }
        txtList.clear();
        txtList = null;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.factory2_card3_1_btn, R.id.factory2_card3_3_btn})
    public void card3Click(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.factory2_card3_1_btn:
                break;
            case R.id.factory2_card3_3_btn:
                break;
        }
    }
}
