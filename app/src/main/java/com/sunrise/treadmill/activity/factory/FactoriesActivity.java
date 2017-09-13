package com.sunrise.treadmill.activity.factory;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseActivity;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/13.
 */

public class FactoriesActivity extends BaseActivity {

    @BindViews({R.id.factory_title, R.id.factory_hint})
    List<TextView> txtList;

    @BindView(R.id.bottom_logo_tab_home)
    ImageView backHome;

    @Override
    public int getLayoutId() {
        return R.layout.activity_factories;
    }

    @Override
    protected void setTextStyle() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList.get(0), TextUtils.MicrosoftBold(this));
            TextUtils.setTextTypeFace(txtList.get(1), TextUtils.Microsoft(this));
        } else {
            TextUtils.setTextTypeFace(txtList.get(0), TextUtils.ArialBold(this));
            TextUtils.setTextTypeFace(txtList.get(1), TextUtils.Arial(this));
        }
    }

    @OnClick({R.id.factory_1, R.id.factory_2})
    public void onFactorySelect(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            default:
                intent = null;
                break;
            case R.id.factory_1:
                intent.setClass(FactoriesActivity.this, Factory1Activity.class);

                break;
            case R.id.factory_2:
                intent.setClass(FactoriesActivity.this, Factory2Activity.class);
                break;
        }
        if (intent != null) {
            finishActivity();
            startActivity(intent);
        }
    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        ImageUtils.changeImageView(backHome, R.mipmap.btn_home_2);
        finishActivity();
    }
}
