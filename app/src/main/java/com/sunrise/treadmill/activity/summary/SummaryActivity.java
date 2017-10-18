package com.sunrise.treadmill.activity.summary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.adapter.summary.SummaryViewPageAdapter;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.fragments.summary.SummaryFragmentPage1;
import com.sunrise.treadmill.fragments.summary.SummaryFragmentPage2;
import com.sunrise.treadmill.fragments.summary.SummaryFragmentPage3;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.utils.ThreadPoolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/10/17.
 */

public class SummaryActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.summary_fragment_img)
    ImageView selectTg;
    @BindView(R.id.summary_viewPage)
    ViewPager viewPager;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private SummaryViewPageAdapter viewPageAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_summary;
    }

    @Override
    protected void init() {
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new SummaryFragmentPage1());
        list.add(new SummaryFragmentPage2());
        list.add(new SummaryFragmentPage3());
        viewPageAdapter = new SummaryViewPageAdapter(fragmentManager, list);
        viewPager.setAdapter(viewPageAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(SummaryActivity.this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                ImageUtils.changeImageView(selectTg, R.mipmap.img_virtual_reality_page_1);
                break;
            case 1:
                ImageUtils.changeImageView(selectTg, R.mipmap.img_virtual_reality_page_2);
                break;
            case 2:
                ImageUtils.changeImageView(selectTg, R.mipmap.img_virtual_reality_page_3);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
