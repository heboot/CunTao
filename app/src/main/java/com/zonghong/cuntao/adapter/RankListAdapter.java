package com.zonghong.cuntao.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zonghong.cuntao.R;

import java.util.List;


public class RankListAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {


    public RankListAdapter(@Nullable List<Object> data) {
        super(R.layout.item_rank, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Object item) {
    }
}
