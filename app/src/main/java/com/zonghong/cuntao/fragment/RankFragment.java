package com.zonghong.cuntao.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.waw.hr.mutils.MKey;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.adapter.MyOrderListAdapter;
import com.zonghong.cuntao.adapter.RankListAdapter;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.common.OrderType;
import com.zonghong.cuntao.databinding.FragmentMyOrderBinding;
import com.zonghong.cuntao.databinding.FragmentRankListBinding;

import java.util.ArrayList;
import java.util.List;

public class RankFragment extends BaseFragment<FragmentRankListBinding> {

    private RankListAdapter rankListAdapter;

    public static RankFragment newInstance(OrderType orderType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MKey.TYPE, orderType);
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
        List datas = new ArrayList<>();
        datas.add("1");
        datas.add("1");
        datas.add("1");
        datas.add("1");
        datas.add("1");
        datas.add("1");
        datas.add("1");
        datas.add("1");
        binding.rvList.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
        rankListAdapter = new RankListAdapter(datas);
        binding.rvList.setAdapter(rankListAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
