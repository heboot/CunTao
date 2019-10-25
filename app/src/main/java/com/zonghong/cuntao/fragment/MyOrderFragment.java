package com.zonghong.cuntao.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.http.HttpClient;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.RankListBean;
import com.waw.hr.mutils.model.OrderModel;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.adapter.MyOrderListAdapter;
import com.zonghong.cuntao.adapter.RankListAdapter;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.common.OrderType;
import com.zonghong.cuntao.databinding.FragmentMyOrderBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyOrderFragment extends BaseFragment<FragmentMyOrderBinding> {

    private MyOrderListAdapter myOrderListAdapter;

    private int status;

    public static MyOrderFragment newInstance(int orderStatus) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MKey.TYPE, orderStatus);
        MyOrderFragment myOrderFragment = new MyOrderFragment();
        myOrderFragment.setArguments(bundle);
        return myOrderFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_order;
    }

    @Override
    public void initUI() {

        binding.rvList.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        status = (int) getArguments().get(MKey.TYPE);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        userInfo();
    }

    private void userInfo() {
        params.put("status", status);
        HttpClient.Builder.getServer().orderList(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<OrderModel>>() {
            @Override
            public void onSuccess(BaseBean<List<OrderModel>> baseBean) {

                if (myOrderListAdapter == null) {
                    myOrderListAdapter = new MyOrderListAdapter(baseBean.getData());
                    binding.rvList.setAdapter(myOrderListAdapter);

                } else {
                    if (page == 1) {
                        myOrderListAdapter.setNewData(baseBean.getData());
                    } else {
                        myOrderListAdapter.addData(baseBean.getData());
                    }

                }
            }

            @Override
            public void onError(BaseBean<List<OrderModel>> baseBean) {

            }
        });
    }
}
