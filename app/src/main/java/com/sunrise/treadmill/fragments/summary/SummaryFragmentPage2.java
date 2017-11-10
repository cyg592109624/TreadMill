package com.sunrise.treadmill.fragments.summary;

import android.widget.TextView;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.bean.Level;
import com.sunrise.treadmill.bean.WorkOut;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.summary.LineChat;
import com.sunrise.treadmill.views.workout.LevelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class SummaryFragmentPage2 extends BaseFragment {


    @BindView(R.id.summary_fragment2_lineChat)
    LineChat lineChat;

    @BindView(R.id.summary_fragment2_avg_value)
    TextView avgValue;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_summary_page_2;
    }

    @Override
    public void recycleObject() {
        avgValue = null;
        lineChat.recycle();
        lineChat = null;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<>();
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment2_hint));
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment2_avg));
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment2_avg_unit));
        txtList.add(avgValue);

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(getContext()));
        }
        txtList.clear();
        txtList = null;
    }

    private long avgLevel = 0L;

    @Override
    protected void init() {
        WorkOut workOutInfo = getActivity().getIntent().getParcelableExtra(Constant.WORK_OUT_INFO);
        List<Level> levels = workOutInfo.getLevelList();
        List<Integer> list = new ArrayList<>();
        avgLevel = 0L;
        for (Level level : levels) {
            avgLevel = avgLevel + level.getLevel();
        }
        avgLevel = avgLevel / levels.size();
        //有可能超越30个数据 这里锁死用前面的30个
        for (int i = 0; i < LevelView.columnCount; i++) {
            list.add(levels.get(i).getLevel());
        }
        lineChat.setData(list);
    }

    private int loadTimes = -1;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            if (loadTimes == -1) {
                lineChat.reFlashView();
                avgValue.setText(avgLevel + "");
                loadTimes = 1;
            }
        }
    }
}
