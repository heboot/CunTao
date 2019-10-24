package com.zonghong.cuntao.fragment;

import android.os.Bundle;
import android.view.View;

import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.MyOrderActivity;
import com.zonghong.cuntao.activity.SettingActivity;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.databinding.FragmentMyBinding;
import com.zonghong.cuntao.utils.IntentUtils;

public class MyFragment extends BaseFragment<FragmentMyBinding> {

    private View.OnClickListener orderClickListener;

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
        orderClickListener = (view->{
            IntentUtils.doIntent(MyOrderActivity.class);
        });
        binding.llytDaifahuo.setOnClickListener(orderClickListener);
        binding.llytDaishouhuo.setOnClickListener(orderClickListener);
        binding.llytYiwancheng.setOnClickListener(orderClickListener);
        binding.vSetting.setOnClickListener(view->{
            IntentUtils.doIntent(SettingActivity.class);
        });
    }
}
