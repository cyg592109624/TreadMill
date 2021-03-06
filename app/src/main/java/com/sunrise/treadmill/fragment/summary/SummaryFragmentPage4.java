package com.sunrise.treadmill.fragment.summary;

import android.widget.TextView;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.bean.WorkOut;
import com.sunrise.treadmill.utils.DateUtil;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class SummaryFragmentPage4 extends BaseFragment {

    @BindView(R.id.summary_fragment4_time_value)
    TextView timeValue;
    @BindView(R.id.summary_fragment4_distance_value)
    TextView distanceValue;
    @BindView(R.id.summary_fragment4_calories_value)
    TextView caloriesValue;

    @BindView(R.id.summary_fragment4_avg_heart_rate_value)
    TextView heartRateAvgValue;

    @BindView(R.id.summary_fragment4_avg_vo2_value_des)
    TextView vo2ValueDescription;

    @BindView(R.id.summary_fragment4_avg_vo2_value)
    TextView vo2Value;

    private WorkOut workOutInfo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_summary_page_4;
    }

    @Override
    public void recycleObject() {
        timeValue = null;
        distanceValue = null;
        caloriesValue = null;
        heartRateAvgValue = null;
        vo2Value = null;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<>();
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment4_hint));

        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment4_time));
        txtList.add(timeValue);
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment4_time_unit));

        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment4_distance));
        txtList.add(distanceValue);
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment4_distance_unit));

        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment4_calories));
        txtList.add(caloriesValue);
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment4_calories_value));

        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment4_avg_heart_rate));
        txtList.add(heartRateAvgValue);
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment4_avg_heart_rate_unit));

        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment4_avg_vo2));
        txtList.add(vo2Value);
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment4_avg_vo2_value_des));

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft());
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial());
        }
        if (GlobalSetting.UnitType.equals(Constant.UNIT_TYPE_METRIC)) {
            txtList.get(6).setText(R.string.unit_km);
        } else {
            txtList.get(6).setText(R.string.unit_mile);
        }

        txtList.clear();
        txtList = null;
    }

    @Override
    protected void init() {
        workOutInfo = getActivity().getIntent().getParcelableExtra(Constant.WORK_OUT_INFO);
        if (workOutInfo != null) {
            timeValue.setText(DateUtil.getFormatMMSS(Long.valueOf(workOutInfo.getRunningTime())));
            distanceValue.setText(workOutInfo.getDistance());
            caloriesValue.setText(workOutInfo.getCalories());
        }
    }
}
