package com.zonghong.cuntao.activity.common;

import android.view.View;

import com.example.http.HttpClient;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.UserInfoBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityTextBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.ImageUtils;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TextActivity extends BaseActivity<ActivityTextBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_text;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("修改昵称");

    }

    @Override
    public void initData() {
//        String title = getIntent().getStringExtra(MKey.TITLE);
//        String content = getIntent().getStringExtra(MKey.SEARCH_CONTENT);
//        binding.tvContent.setText(content);
        binding.includeToolbar.tvTitle.setText("帮助");
        userInfo();
    }

    @Override
    public void initListener() {

    }

    private void userInfo() {
        HttpClient.Builder.getServer().getHelp().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Map>() {
            @Override
            public void onSuccess(BaseBean<Map> baseBean) {
                binding.tvContent.setText((String) baseBean.getData().get("help"));
            }

            @Override
            public void onError(BaseBean<Map> baseBean) {

            }
        });
    }

}
