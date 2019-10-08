package com.zonghong.cuntao.fragment;

import android.os.Bundle;
import android.view.View;

import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.databinding.FragmentIndexBinding;

public class IndexFragment extends BaseFragment<FragmentIndexBinding> {

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

    }

    @Override
    public void initListener() {

    }
}
