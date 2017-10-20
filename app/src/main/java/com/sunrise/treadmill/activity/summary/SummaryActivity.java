package com.sunrise.treadmill.activity.summary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.home.HomeActivity;
import com.sunrise.treadmill.adapter.summary.SummaryViewPageAdapter;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.fragments.summary.SummaryFragmentPage1;
import com.sunrise.treadmill.fragments.summary.SummaryFragmentPage2;
import com.sunrise.treadmill.fragments.summary.SummaryFragmentPage3;
import com.sunrise.treadmill.utils.BitMapUtils;
import com.sunrise.treadmill.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/10/17.
 */

public class SummaryActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.summary_fragment_img)
    ImageView selectTg;
    @BindView(R.id.summary_viewPage)
    ViewPager viewPager;

    private Bitmap selectTgBitmap;
    private SummaryViewPageAdapter viewPageAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_summary;
    }

    @Override
    public void recycleObject() {
        selectTg = null;
        if (selectTgBitmap != null) {
            selectTgBitmap.recycle();
            selectTgBitmap = null;
        }
        viewPager.removeAllViews();
        viewPager = null;
        viewPageAdapter.recycle();
        viewPageAdapter = null;
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
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (selectTgBitmap != null) {
            selectTgBitmap.recycle();
        }
        switch (position) {
            case 0:
                selectTgBitmap = BitMapUtils.loadMipMapResource(activityContext.getResources(), R.mipmap.img_virtual_reality_page_1);
                ImageUtils.changeImageView(selectTg, selectTgBitmap);
                break;
            case 1:
                selectTgBitmap = BitMapUtils.loadMipMapResource(activityContext.getResources(), R.mipmap.img_virtual_reality_page_2);
                ImageUtils.changeImageView(selectTg, selectTgBitmap);
                break;
            case 2:
                selectTgBitmap = BitMapUtils.loadMipMapResource(activityContext.getResources(), R.mipmap.img_virtual_reality_page_3);
                ImageUtils.changeImageView(selectTg, selectTgBitmap);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    @OnClick(R.id.bottom_logo_tab_home)
    public void onHomeClick() {
        Intent intent = new Intent(activityContext, HomeActivity.class);
        finishActivity();
        startActivity(intent);
    }
}
