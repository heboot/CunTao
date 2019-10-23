package com.zonghong.cuntao.activity;

import android.view.View;

import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityPublishContentBinding;

public class PublishContentActivity extends BaseActivity<ActivityPublishContentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_content;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
