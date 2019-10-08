package com.zonghong.cuntao.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zonghong.cuntao.R;

import java.util.List;

import androidx.annotation.Nullable;


public class CommunityAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {


    public CommunityAdapter(@Nullable List<Object> data) {
        super(R.layout.item_community, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Object item) {
    }
}
