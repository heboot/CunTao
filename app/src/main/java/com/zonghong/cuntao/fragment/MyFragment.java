package com.zonghong.cuntao.fragment;

import android.os.Bundle;

import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.databinding.FragmentMyBinding;

public class MyFragment extends BaseFragment<FragmentMyBinding> {

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
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
