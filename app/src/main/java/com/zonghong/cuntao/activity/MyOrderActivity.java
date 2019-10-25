package com.zonghong.cuntao.activity;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.common.OrderType;
import com.zonghong.cuntao.databinding.ActivityMyOrderBinding;
import com.zonghong.cuntao.fragment.MyOrderFragment;

import java.util.ArrayList;

public class MyOrderActivity extends BaseActivity<ActivityMyOrderBinding> {

    private ArrayList<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

        fragmentList.add(MyOrderFragment.newInstance(2));
        fragmentList.add(MyOrderFragment.newInstance(3));
        fragmentList.add(MyOrderFragment.newInstance(4));
        String[] titles = {"待发货", "待收货", "已完成"};
        binding.tab.setViewPager(binding.vpContainer, titles, this, fragmentList);
    }

    @Override
    public void initListener() {

    }
}
