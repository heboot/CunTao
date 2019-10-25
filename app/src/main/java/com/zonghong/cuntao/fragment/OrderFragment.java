package com.zonghong.cuntao.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.http.HttpClient;
import com.waw.hr.mutils.base.BaseBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.databinding.FragmentOrderBinding;
import com.zonghong.cuntao.http.HttpObserver;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderFragment extends BaseFragment<FragmentOrderBinding> {

    public static OrderFragment newInstance() {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initUI() {
        binding.includeToolbar.vBack.setVisibility(View.GONE);
        binding.includeToolbar.tvTitle.setText("我的订单");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        getInfo();
    }

    private void getInfo() {
        params = new HashMap<>();
        params.put("page",4);
        HttpClient.Builder.getServer().getExplain(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Map>() {
            @Override
            public void onSuccess(BaseBean<Map> baseBean) {
                binding.tvTebie.setText(String.valueOf(baseBean.getData().get("explain")));
            }

            @Override
            public void onError(BaseBean<Map> baseBean) {

            }
        });
    }
}
