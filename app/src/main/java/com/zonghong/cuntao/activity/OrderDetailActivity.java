package com.zonghong.cuntao.activity;

import android.view.View;

import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityOrderDetailBinding;

public class OrderDetailActivity extends BaseActivity<ActivityOrderDetailBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("订单详情");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
