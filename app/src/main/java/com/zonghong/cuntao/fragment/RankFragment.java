package com.zonghong.cuntao.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.http.HttpClient;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.ToastUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.ArticleIndexBean;
import com.waw.hr.mutils.bean.RankListBean;
import com.zonghong.cuntao.MAPP;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.adapter.CommunityAdapter;
import com.zonghong.cuntao.adapter.MyOrderListAdapter;
import com.zonghong.cuntao.adapter.RankListAdapter;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.common.OrderType;
import com.zonghong.cuntao.databinding.FragmentMyOrderBinding;
import com.zonghong.cuntao.databinding.FragmentRankListBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RankFragment extends BaseFragment<FragmentRankListBinding> {

    private RankListAdapter rankListAdapter;

    private int type;

    public static RankFragment newInstance(int  data_type) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MKey.TYPE, data_type);
        RankFragment myOrderFragment = new RankFragment();
        myOrderFragment.setArguments(bundle);
        return myOrderFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rank_list;
    }

    @Override
    public void initUI() {
        binding.rvList.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        type = (int) getArguments().get(MKey.TYPE);
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
        params.put("page", page);
        HttpClient.Builder.getServer().commission(UserService.getInstance().getToken(),type).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<RankListBean>>() {
            @Override
            public void onSuccess(BaseBean<List<RankListBean>> baseBean) {

                if (rankListAdapter == null) {
                    rankListAdapter = new RankListAdapter(baseBean.getData() );
                    binding.rvList.setAdapter(rankListAdapter);

                } else {
                    if (page == 1) {
                        rankListAdapter.setNewData(baseBean.getData());
                    } else {
                        rankListAdapter.addData(baseBean.getData());
                    }
                    rankListAdapter.loadMoreComplete();
                }
            }

            @Override
            public void onError(BaseBean<List<RankListBean>> baseBean) {

            }
        });
    }
}
