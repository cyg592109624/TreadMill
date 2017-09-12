package com.sunrise.treadmill.utils;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;

/**
 * Created by ChuHui on 2017/9/11.
 */

public class ImageUtils {


    public  static void changeImageView(ImageView imageView,int imgResource){
        imageView.setImageResource(imgResource);
    }

    /**
     * 切换语言图标 必须运行在UI线程
     *
     * @param isClick 是否切换到点击状态
     */
    public static void changeLanguageIconState(ImageView languageIcon, boolean isClick) {

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.de_DE)) {
            if (isClick) {
                languageIcon.setImageResource(R.mipmap.btn_home_language_de_2);
                return;
            }
            languageIcon.setImageResource(R.mipmap.btn_home_language_de_1);
        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.en_US)) {
            if (isClick) {
                languageIcon.setImageResource(R.mipmap.btn_home_language_us_2);
                return;
            }
            languageIcon.setImageResource(R.mipmap.btn_home_language_us_1);
        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.es_ES)) {
            if (isClick) {
                languageIcon.setImageResource(R.mipmap.btn_home_language_esp_2);
                return;
            }
            languageIcon.setImageResource(R.mipmap.btn_home_language_esp_1);
        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.ir_IR)) {
            if (isClick) {
                languageIcon.setImageResource(R.mipmap.btn_home_language_ir_2);
                return;
            }
            languageIcon.setImageResource(R.mipmap.btn_home_language_ir_1);
        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.pt_PT)) {
            if (isClick) {
                languageIcon.setImageResource(R.mipmap.btn_home_language_pt_2);
                return;
            }
            languageIcon.setImageResource(R.mipmap.btn_home_language_pt_1);
        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.ru_RU)) {
            if (isClick) {
                languageIcon.setImageResource(R.mipmap.btn_home_language_rus_2);
                return;
            }
            languageIcon.setImageResource(R.mipmap.btn_home_language_rus_1);
        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.tr_TR)) {
            if (isClick) {
                languageIcon.setImageResource(R.mipmap.btn_home_language_tr_2);
                return;
            }
            languageIcon.setImageResource(R.mipmap.btn_home_language_tr_1);
        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            if (isClick) {
                languageIcon.setImageResource(R.mipmap.btn_home_language_cn_2);
                return;
            }
            languageIcon.setImageResource(R.mipmap.btn_home_language_cn_1);
        }
    }
}
