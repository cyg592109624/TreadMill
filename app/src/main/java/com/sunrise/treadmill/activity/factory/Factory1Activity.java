package com.sunrise.treadmill.activity.factory;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseActivity;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/13.
 */

public class Factory1Activity extends BaseActivity {
    @BindView(R.id.bottom_logo_tab_home)
    ImageButton backHome;

    @Override
    public int getLayoutId() {
        return R.layout.activity_factory1;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();
        txtList.add((TextView) findViewById(R.id.factory_1_title));
        txtList.add((TextView) findViewById(R.id.factory_1_card_setting));

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(this));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(this));
        }
    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }
}
