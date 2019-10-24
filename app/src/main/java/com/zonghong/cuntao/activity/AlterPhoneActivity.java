package com.zonghong.cuntao.activity;

import android.view.View;

import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityAlterPhoneBinding;

public class AlterPhoneActivity extends BaseActivity<ActivityAlterPhoneBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_alter_phone;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("更换手机号");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
