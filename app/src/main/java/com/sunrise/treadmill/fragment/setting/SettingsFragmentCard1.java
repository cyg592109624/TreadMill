package com.sunrise.treadmill.fragment.setting;

import android.app.Service;
import android.media.AudioManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.ScreenUtils;
import com.sunrise.treadmill.utils.SoundsUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class SettingsFragmentCard1 extends BaseFragment implements SeekBar.OnSeekBarChangeListener {
    @BindView(R.id.settings_card1_seek_bar_bright)
    SeekBar bright;
    @BindView(R.id.settings_card1_seek_bar_sounds)
    SeekBar sounds;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_settings_card_1;
    }

    @Override
    public void recycleObject() {
        bright.setOnSeekBarChangeListener(null);
        bright = null;
        sounds.setOnSeekBarChangeListener(null);
        sounds = null;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<>();
        txtList.add((TextView) parentView.findViewById(R.id.settings_card1_txt1));
        txtList.add((TextView)parentView.findViewById(R.id.settings_card1_txt2));

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold());
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold());
        }
        txtList.clear();
        txtList=null;
    }

    @Override
    protected void init() {
        ScreenUtils.setContentResolver(getContext());
        ScreenUtils.setWindow(getActivity());

        SoundsUtils.setAudioManager((AudioManager) getActivity().getSystemService(Service.AUDIO_SERVICE));

        bright.setMax(ScreenUtils.MAX_BRIGHTNESS);
        sounds.setMax(SoundsUtils.getVoiceMusicMax());

        bright.setProgress(ScreenUtils.getScreenBrightness());
        sounds.setProgress(SoundsUtils.getVoiceMusic());

        bright.setOnSeekBarChangeListener(SettingsFragmentCard1.this);
        sounds.setOnSeekBarChangeListener(SettingsFragmentCard1.this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            default:
                break;
            case R.id.settings_card1_seek_bar_bright:
                ScreenUtils.setBrightness(i);
                break;
            case R.id.settings_card1_seek_bar_sounds:
                SoundsUtils.setVoiceMusic(i);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
