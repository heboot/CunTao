package com.zonghong.cuntao.activity.order;

import android.view.View;

import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.model.OrderModel;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityOrderDetailBinding;

public class OrderDetailActivity extends BaseActivity<ActivityOrderDetailBinding> {

    private OrderModel orderModel;

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
        orderModel = (OrderModel) getIntent().getExtras().get(MKey.DATA);


        binding.tvName.setText(orderModel.getTime());



    }

    @Override
    public void initListener() {

    }
}
