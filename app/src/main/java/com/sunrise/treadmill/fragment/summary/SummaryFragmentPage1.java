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

public class SummaryFragmentPage1 extends BaseFragment {

    @BindView(R.id.summary_fragment1_time_value)
    TextView timeValue;
    @BindView(R.id.summary_fragment1_distance_value)
    TextView distanceValue;
    @BindView(R.id.summary_fragment1_calories_value)
    TextView caloriesValue;

    @BindView(R.id.summary_fragment1_avg_heart_rate_value)
    TextView heartRateAvgValue;
    @BindView(R.id.summary_fragment1_avg_speed_value)
    TextView speedValue;

    private WorkOut workOutInfo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_summary_page_1;
    }

    @Override
    public void recycleObject() {
        timeValue = null;
        distanceValue = null;
        caloriesValue = null;
        heartRateAvgValue = null;
        speedValue = null;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<>();
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment1_hint));

        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment1_time));
        txtList.add(timeValue);
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment1_time_unit));

        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment1_distance));
        txtList.add(distanceValue);
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment1_distance_unit));


        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment1_calories));
        txtList.add(caloriesValue);
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment1_calories_value));

        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment1_avg_heart_rate));
        txtList.add(heartRateAvgValue);
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment1_avg_heart_rate_unit));

        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment1_avg_speed));
        txtList.add(speedValue);
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment1_avg_speed_unit));

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft());
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial());
        }

        if (GlobalSetting.UnitType.equals(Constant.UNIT_TYPE_METRIC)) {
            txtList.get(6).setText(R.string.unit_km);
            txtList.get(15).setText(R.string.unit_km_h);
        } else {
            txtList.get(6).setText(R.string.unit_mile);
            txtList.get(15).setText(R.string.unit_mile_h);
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
