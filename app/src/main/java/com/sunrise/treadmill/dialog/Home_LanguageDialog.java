package com.sunrise.treadmill.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.interfaces.HomeLanguageDialogReturn;
import com.sunrise.treadmill.utils.Constant;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.SPUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.List;

import butterknife.BindViews;
import butterknife.OnClick;
import butterknife.ButterKnife;

/**
 * Created by ChuHui on 2017/9/6.
 * 校正对话框
 */

public class Home_LanguageDialog extends BaseDialogFragment {

    public static final String Home_Language_Dialog = "Home_LanguageDialog";
    View inflaterView;
    private HomeLanguageDialogReturn dialogFragmentReturn;
    @BindViews({R.id.home_dialog_language_img_us, R.id.home_dialog_language_img_zh, R.id.home_dialog_language_img_de, R.id.home_dialog_language_img_tr, R.id.home_dialog_language_img_ir, R.id.home_dialog_language_img_es, R.id.home_dialog_language_img_pt, R.id.home_dialog_language_img_ru})
    List<ImageView> imageViews;

    @BindViews({R.id.home_dialog_language_txt_us, R.id.home_dialog_language_txt_zh, R.id.home_dialog_language_txt_de, R.id.home_dialog_language_txt_tr, R.id.home_dialog_language_txt_ir, R.id.home_dialog_language_txt_es, R.id.home_dialog_language_txt_pt, R.id.home_dialog_language_txt_ru})
    List<TextView> textViews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflaterView = inflater.inflate(R.layout.home_dialog_language, container);
        ButterKnife.bind(this, inflaterView);
        dialogFragmentReturn = (HomeLanguageDialogReturn) getActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public View inflateView() {
        return inflaterView;
    }

    @Override
    protected void setTextStyle() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(textViews, TextUtils.Microsoft(getContext()));
        } else {
            TextUtils.setTextTypeFace(textViews, TextUtils.Arial(getContext()));
        }
        TextUtils.changeTextColor(textViews, getResources().getColor(R.color.language_btn_on));
    }


    @OnClick({R.id.home_dialog_language_img_us, R.id.home_dialog_language_img_zh, R.id.home_dialog_language_img_de, R.id.home_dialog_language_img_tr, R.id.home_dialog_language_img_ir, R.id.home_dialog_language_img_es, R.id.home_dialog_language_img_pt, R.id.home_dialog_language_img_ru})
    void languageBtnClick(View btn) {
        boolean isChange = false;
        switch (btn.getId()) {
            default:
                dismiss();
                break;
            case R.id.home_dialog_language_img_us:
                if (!GlobalSetting.AppLanguage.equals(LanguageUtils.en_US)) {
                    isChange = true;
                    GlobalSetting.AppLanguage = LanguageUtils.en_US;
                    LanguageUtils.updateLanguage(LanguageUtils.Locale_US, getResources());
                }
                break;
            case R.id.home_dialog_language_img_zh:
                if (!GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
                    isChange = true;
                    GlobalSetting.AppLanguage = LanguageUtils.zh_CN;
                    LanguageUtils.updateLanguage(LanguageUtils.Locale_CN, getResources());
                }
                break;
            case R.id.home_dialog_language_img_de:
                if (!GlobalSetting.AppLanguage.equals(LanguageUtils.de_DE)) {
                    isChange = true;
                    GlobalSetting.AppLanguage = LanguageUtils.de_DE;
                    LanguageUtils.updateLanguage(LanguageUtils.Locale_DE, getResources());
                }
                break;
            case R.id.home_dialog_language_img_tr:
                if (!GlobalSetting.AppLanguage.equals(LanguageUtils.tr_TR)) {
                    isChange = true;
                    GlobalSetting.AppLanguage = LanguageUtils.tr_TR;
                    LanguageUtils.updateLanguage(LanguageUtils.Locale_TR, getResources());
                }
                break;
            case R.id.home_dialog_language_img_ir:
                if (!GlobalSetting.AppLanguage.equals(LanguageUtils.ir_IR)) {
                    isChange = true;
                    GlobalSetting.AppLanguage = LanguageUtils.ir_IR;
                    LanguageUtils.updateLanguage(LanguageUtils.Locale_IR, getResources());
                }
                break;
            case R.id.home_dialog_language_img_es:
                if (!GlobalSetting.AppLanguage.equals(LanguageUtils.es_ES)) {
                    isChange = true;
                    GlobalSetting.AppLanguage = LanguageUtils.es_ES;
                    LanguageUtils.updateLanguage(LanguageUtils.Locale_ESP, getResources());
                }
                break;
            case R.id.home_dialog_language_img_pt:
                if (!GlobalSetting.AppLanguage.equals(LanguageUtils.pt_PT)) {
                    isChange = true;
                    GlobalSetting.AppLanguage = LanguageUtils.pt_PT;
                    LanguageUtils.updateLanguage(LanguageUtils.Locale_PT, getResources());
                }
                break;
            case R.id.home_dialog_language_img_ru:
                if (!GlobalSetting.AppLanguage.equals(LanguageUtils.ru_RU)) {
                    isChange = true;
                    GlobalSetting.AppLanguage = LanguageUtils.ru_RU;
                    LanguageUtils.updateLanguage(LanguageUtils.Locale_RU, getResources());
                }
                break;
        }
        if (isChange) {
            SPUtils.put(getContext(), Constant.appLanguage, GlobalSetting.AppLanguage);
            dialogFragmentReturn.onLanguageReturn(isChange);
        } else {
            dialogFragmentReturn.onLanguageReturn(false);
        }
        dismiss();

    }

}
