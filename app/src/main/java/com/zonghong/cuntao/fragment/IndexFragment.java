package com.zonghong.cuntao.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.common.OrderType;
import com.zonghong.cuntao.databinding.FragmentIndexBinding;

import java.util.ArrayList;

public class IndexFragment extends BaseFragment<FragmentIndexBinding> {


    private ArrayList<Fragment> fragmentList = new ArrayList<>();



    public static IndexFragment newInstance() {
        Bundle args = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    public void initUI() {
        binding.includeToolbar.tvTitle.setText("首页");
        binding.includeToolbar.vBack.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        fragmentList.add(RankFragment.newInstance(OrderType.DAIFAHUO));
        fragmentList.add(RankFragment.newInstance(OrderType.DAISHOUHUO));
        fragmentList.add(RankFragment.newInstance(OrderType.FINISH));
        String[] titles = {"日榜", "周榜", "月榜"};
        binding.includeRankList.tab.setViewPager(binding.includeRankList.vpContainer, titles, _mActivity, fragmentList);
    }

    @Override
    public void initListener() {

    }
}
