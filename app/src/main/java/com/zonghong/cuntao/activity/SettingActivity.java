package com.zonghong.cuntao.activity;

import android.view.View;

import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivitySettingBinding;
import com.zonghong.cuntao.utils.IntentUtils;

public class SettingActivity extends BaseActivity<ActivitySettingBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("设置");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        binding.tvPhoneTitle.setOnClickListener(view->{
            IntentUtils.doIntent(AlterPhoneActivity.class);
        });
    }
}
