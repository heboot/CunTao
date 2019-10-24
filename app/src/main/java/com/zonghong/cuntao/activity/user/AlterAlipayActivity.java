package com.zonghong.cuntao.activity.user;

import android.view.View;

import com.example.http.HttpClient;
import com.waw.hr.mutils.base.BaseBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityAlterAlipayBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.IntentUtils;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AlterAlipayActivity extends BaseActivity<ActivityAlterAlipayBinding> {

    private String name, account;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_alter_alipay;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("支付宝绑定");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        binding.tvSubmit.setOnClickListener(view -> {
            IntentUtils.intent2BindAlipayActivity(name, account);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        aliInfo();
    }

    /**
     * 返回数据 为什么是数组？ 太骚了 我也无法解释
     */
    private void aliInfo() {
        HttpClient.Builder.getServer().alipayInfo(UserService.getInstance().getToken()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<Map>>() {
            @Override
            public void onSuccess(BaseBean<List<Map>> baseBean) {
                String ali = String.valueOf(baseBean.getData().get(0).get("alipay"));
                name = String.valueOf(baseBean.getData().get(0).get("user_name"));
                account = ali;
                if (!ali.equals("110.0")) {
                    binding.tvAlipay.setText("你的支付宝账号: " + ali);
                }
            }

            @Override
            public void onError(BaseBean<List<Map>> baseBean) {

            }
        });
    }
}
