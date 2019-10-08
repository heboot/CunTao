package com.zonghong.cuntao.fragment;

import android.os.Bundle;

import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.databinding.FragmentOrderBinding;

public class OrderFragment extends BaseFragment<FragmentOrderBinding> {

    public static OrderFragment newInstance() {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
