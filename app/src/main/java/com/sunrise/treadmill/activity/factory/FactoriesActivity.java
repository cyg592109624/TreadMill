package com.sunrise.treadmill.activity.factory;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseActivity;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/13.
 */

public class FactoriesActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_factories;
    }

    @Override
    public void recycleObject() {

    }

    @Override
    protected void setTextStyle() {
        TextView title = (TextView) findViewById(R.id.factories_title);
        TextView hint = (TextView) findViewById(R.id.factories_hint);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(title, TextUtils.MicrosoftBold());
            TextUtils.setTextTypeFace(hint, TextUtils.Microsoft());
        } else {
            TextUtils.setTextTypeFace(title, TextUtils.ArialBold());
            TextUtils.setTextTypeFace(hint, TextUtils.Arial());
        }
        title = null;
        hint = null;
    }

    @OnClick({R.id.factories_1, R.id.factories_2})
    public void onFactorySelect(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            default:
                intent = null;
                break;
            case R.id.factories_1:
                intent.setClass(FactoriesActivity.this, Factory1Activity.class);
                break;
            case R.id.factories_2:
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
        finishActivity();
    }
}
