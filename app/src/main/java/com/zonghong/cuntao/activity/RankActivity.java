package com.zonghong.cuntao.activity;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.waw.hr.mutils.MKey;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.common.OrderType;
import com.zonghong.cuntao.databinding.ActivityRankBinding;
import com.zonghong.cuntao.fragment.RankFragment;

import java.util.ArrayList;

public class RankActivity extends BaseActivity<ActivityRankBinding> {

    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    private int type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rank;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("排行榜");
    }

    @Override
    public void initData() {
        type = (int) getIntent().getExtras().get(MKey.TYPE);
        fragmentList.add(RankFragment.newInstance(1));
        fragmentList.add(RankFragment.newInstance(2));
        fragmentList.add(RankFragment.newInstance(3));
        String[] titles = {"日榜", "周榜", "月榜"};
        binding.includeRankList.tab.setViewPager(binding.includeRankList.vpContainer, titles, this, fragmentList);
        binding.includeRankList.tab.setCurrentTab(type - 1);
    }

    @Override
    public void initListener() {

    }
}
