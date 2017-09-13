package com.sunrise.treadmill.activity.factory;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class Factory2Activity extends BaseActivity {

    @BindView(R.id.factory_2_view_bg)
    LinearLayout bgView;
    @BindViews({R.id.factory_2_card_setting, R.id.factory_2_card_info,R.id.factory_2_card_update,R.id.factory_2_card_logo,R.id.factory_2_title})
    List<TextView> txtList;

    @BindView(R.id.bottom_logo_tab_home)
    ImageView backHome;


    @Override
    public int getLayoutId() {
        return R.layout.activity_factory2;
    }

    @Override
    protected void setTextStyle() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(this));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(this));
        }
    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        ImageUtils.changeImageView(backHome, R.mipmap.btn_home_2);
        finishActivity();
    }

    @OnClick({R.id.factory_2_card_setting, R.id.factory_2_card_info,R.id.factory_2_card_update,R.id.factory_2_card_logo})
    public void onSelectCardClick(View view) {
        int bgResource = -1;
        int tgCard = -1;
        switch (view.getId()) {
            default:
                bgResource = -1;
                tgCard = -1;
                break;
            case R.id.factory_2_card_setting:
                bgResource = R.mipmap.img_factory_3_1;
                tgCard = 0;
                break;
            case R.id.factory_2_card_info:
                bgResource = R.mipmap.img_factory_3_2;
                tgCard = 1;
                break;
            case R.id.factory_2_card_update:
                bgResource = R.mipmap.img_factory_3_3;
                tgCard = 2;
                break;
            case R.id.factory_2_card_logo:
                bgResource = R.mipmap.img_factory_3_4;
                tgCard = 3;
                break;
        }
        if (bgResource != -1 && tgCard != -1) {
            bgView.setBackgroundResource(bgResource);
            for (int i = 0; i < txtList.size()-1; i++) {
                if (i == tgCard) {
                    TextUtils.changeTextColor(txtList.get(i), getColor(R.color.settings_tabs_on));
                    TextUtils.changeTextSize(txtList.get(i), 30f);
                } else {
                    TextUtils.changeTextColor(txtList.get(i), getColor(R.color.settings_tabs_off));
                    TextUtils.changeTextSize(txtList.get(i), 25f);
                }
            }
        }
    }

}
