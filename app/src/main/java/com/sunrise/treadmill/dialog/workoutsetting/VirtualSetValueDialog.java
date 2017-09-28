package com.sunrise.treadmill.dialog.workoutsetting;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workoutsetting.VirtualRealityActivity;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.interfaces.OnKeyBoardReturn;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.MyKeyBoardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/27.
 */

public class VirtualSetValueDialog extends BaseDialogFragment implements OnKeyBoardReturn {
    public static final String TAG = "VirtualSetValueDialog";

    @BindView(R.id.dialog_workout_vr_img)
    ImageView vrImage;

    @BindView(R.id.dialog_workout_vr_edit_time)
    TextView editValue;
    @BindView(R.id.dialog_workout_vr_keyboard)
    MyKeyBoardView keyBoardView;

    @BindView(R.id.workout_mode_start_4)
    ImageView startBtn;

    @BindView(R.id.workout_mode_back_4)
    ImageView backBtn;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext(), R.style.Dialog_No_BG);
        return dialog;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_workout_setting_vr;
    }

    @Override
    protected void init() {
        keyBoardView.setKeyBoardReturn(this);
        keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);
        Bundle bundle = getArguments();
        int vrNum = bundle.getInt(VirtualRealityActivity.SelectVRNum, VirtualRealityActivity.selectNothing);
        changeVRImage(vrNum);
        ((TextView) keyBoardView.findViewById(R.id.key_board_edit_value)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editValue.setText(editable.toString());
            }
        });
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();
        txtList.add((TextView) getParentView().findViewById(R.id.workout_edit_start_hint_4));
        txtList.add(editValue);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(getContext()));
        }
    }


    @Override
    public void onKeyBoardEnter(String result) {
        editValue.setText(result);
    }

    @Override
    public void onKeyBoardClose() {
        startBtn.setEnabled(true);
        backBtn.setEnabled(true);
        vrImage.setVisibility(View.VISIBLE);
        keyBoardView.setVisibility(View.GONE);
        TextUtils.changeTextColor(editValue, getResources().getColor(R.color.workout_mode_white));
        TextUtils.changeTextBackground(editValue, R.mipmap.btn_virtual_time_1);
    }

    @OnClick(R.id.dialog_workout_vr_edit_time)
    public void changeEditValue() {
        startBtn.setEnabled(false);
        backBtn.setEnabled(false);
        vrImage.setVisibility(View.GONE);
        keyBoardView.setVisibility(View.VISIBLE);

        TextUtils.changeTextColor(editValue, getResources().getColor(R.color.workout_mode_select));
        TextUtils.changeTextBackground(editValue, R.mipmap.btn_virtual_time_2);
    }

    @OnClick(R.id.workout_mode_back_4)
    public void back() {
        dismiss();
        VirtualRealityActivity activity = (VirtualRealityActivity) getActivity();
        activity.setOptionBodyVisibility(View.VISIBLE);
    }

    private void changeVRImage(int vrNum) {
        switch (vrNum) {
            default:
                break;
            case VirtualRealityActivity.tgValue_1:
                ImageUtils.changeImageView(vrImage, R.mipmap.img_program_virtual_01_4);
                break;
            case VirtualRealityActivity.tgValue_2:
                ImageUtils.changeImageView(vrImage, R.mipmap.img_program_virtual_02_4);
                break;
            case VirtualRealityActivity.tgValue_3:
                ImageUtils.changeImageView(vrImage, R.mipmap.img_program_virtual_03_4);
                break;
            case VirtualRealityActivity.tgValue_4:
                ImageUtils.changeImageView(vrImage, R.mipmap.img_program_virtual_04_4);
                break;
            case VirtualRealityActivity.tgValue_5:
                ImageUtils.changeImageView(vrImage, R.mipmap.img_program_virtual_05_4);
                break;
            case VirtualRealityActivity.tgValue_6:
                ImageUtils.changeImageView(vrImage, R.mipmap.img_program_virtual_06_4);
                break;
            case VirtualRealityActivity.tgValue_7:
                ImageUtils.changeImageView(vrImage, R.mipmap.img_program_virtual_07_4);
                break;
            case VirtualRealityActivity.tgValue_8:
                ImageUtils.changeImageView(vrImage, R.mipmap.img_program_virtual_08_4);
                break;
            case VirtualRealityActivity.tgValue_9:
                ImageUtils.changeImageView(vrImage, R.mipmap.img_program_virtual_09_4);
                break;
            case VirtualRealityActivity.tgValue_10:
                ImageUtils.changeImageView(vrImage, R.mipmap.img_program_virtual_10_4);
                break;
        }
    }

}
