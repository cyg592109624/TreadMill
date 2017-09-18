package com.sunrise.treadmill.activity.factory;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseActivity;
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

    @BindView(R.id.bottom_logo_tab_home)
    ImageButton backHome;

    @Override
    public int getLayoutId() {
        return R.layout.activity_factories;
    }

    @Override
    protected void setTextStyle() {
        TextView title = (TextView) findViewById(R.id.factories_title);
        TextView hint = (TextView) findViewById(R.id.factories_hint);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(title, TextUtils.MicrosoftBold(this));
            TextUtils.setTextTypeFace(hint, TextUtils.Microsoft(this));
        } else {
            TextUtils.setTextTypeFace(title, TextUtils.ArialBold(this));
            TextUtils.setTextTypeFace(hint, TextUtils.Arial(this));
        }
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
