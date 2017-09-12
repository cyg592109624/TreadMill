package com.sunrise.treadmill.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ChuHui on 2017/9/11.
 */

public class TextUtils {
    private static AssetManager ass;

    private TextUtils() {
    }

    public static Typeface Arial(Context context) {
        if (ass == null) {
            ass = context.getAssets();
        }
        Typeface tf = Typeface.createFromAsset(ass, "fonts/srs_arial_0.ttf");
        return tf;
    }

    public static Typeface ArialBold(Context context) {
        if (ass == null) {
            ass = context.getAssets();
        }
        Typeface tf = Typeface.createFromAsset(ass, "fonts/srs_arialbd_0.ttf");
        return tf;
    }

    public static Typeface ArialBlack(Context context) {
        if (ass == null) {
            ass = context.getAssets();
        }
        Typeface tf = Typeface.createFromAsset(ass, "fonts/srs_ariblk_0.ttf");
        return tf;
    }

    public static Typeface Microsoft(Context context) {
        if (ass == null) {
            ass = context.getAssets();
        }
        Typeface tf = Typeface.createFromAsset(ass, "fonts/srs_msjh.ttf");
        return tf;
    }

    public static Typeface MicrosoftBold(Context context) {
        if (ass == null) {
            ass = context.getAssets();
        }
        Typeface tf = Typeface.createFromAsset(ass, "fonts/srs_msjhbd.ttf");
        return tf;
    }


    public static void setTextSize(TextView textView, float size) {
        textView.setTextSize(size);
    }

    public static void setTextSize(List<TextView> textViews, float size) {
        for (TextView t : textViews) {
            setTextSize(t, size);
        }
    }

    public static void setTextTypeFace(TextView textView, Typeface typeface) {
        textView.setTypeface(typeface);
    }

    public static void setTextTypeFace(List<TextView> textViews, Typeface typeface) {
        for (TextView t : textViews) {
            setTextTypeFace(t, typeface);
        }
    }

    public static void changeTextColor(List<TextView> textViews, int color) {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.de_DE)) {
            textViews.get(2).setTextColor(color);

        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.en_US)) {
            textViews.get(0).setTextColor(color);

        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.es_ES)) {
            textViews.get(5).setTextColor(color);

        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.ir_IR)) {
            textViews.get(4).setTextColor(color);

        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.pt_PT)) {
            textViews.get(6).setTextColor(color);

        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.ru_RU)) {
            textViews.get(7).setTextColor(color);

        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.tr_TR)) {
            textViews.get(3).setTextColor(color);

        } else if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            textViews.get(1).setTextColor(color);

        }
    }
}
