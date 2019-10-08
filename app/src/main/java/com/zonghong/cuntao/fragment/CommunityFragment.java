package com.zonghong.cuntao.fragment;

import android.os.Bundle;
import android.view.View;

import com.zonghong.cuntao.R;
import com.zonghong.cuntao.adapter.CommunityAdapter;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.databinding.FragmentCommunityBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CommunityFragment extends BaseFragment<FragmentCommunityBinding> {

    private CommunityAdapter communityAdapter;

    public static CommunityFragment newInstance() {
        Bundle args = new Bundle();
        CommunityFragment fragment = new CommunityFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    public void initUI() {
        binding.includeToolbar.tvTitle.setText("社区");
        binding.includeToolbar.vBack.setVisibility(View.GONE);
        binding.rvList.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        List<Object> datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        communityAdapter = new CommunityAdapter(datas);
        binding.rvList.setAdapter(communityAdapter);
    }

    @Override
    public void initListener() {

    }
}
