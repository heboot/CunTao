package com.zonghong.cuntao.fragment;

import android.os.Bundle;
import android.view.View;

import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.databinding.FragmentIndexBinding;
import com.zonghong.cuntao.databinding.FragmentTransitionBinding;

public class TransitionFragment extends BaseFragment<FragmentTransitionBinding> {

    public static TransitionFragment newInstance() {
        Bundle args = new Bundle();
        TransitionFragment fragment = new TransitionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_transition;
    }

    @Override
    public void initUI() {
        binding.includeToolbar.tvTitle.setText("一键转换");
        binding.includeToolbar.vBack.setVisibility(View.GONE);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
