package com.zonghong.cuntao.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.ArticleModel;
import com.waw.hr.mutils.bean.IndexBean;
import com.zonghong.cuntao.MAPP;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.databinding.ItemCommunityBinding;
import com.zonghong.cuntao.databinding.ItemNotiBinding;
import com.zonghong.cuntao.utils.ImageUtils;
import com.zonghong.cuntao.utils.NumberUtils;

import java.util.List;


public class NotiAdapter extends BaseQuickAdapter<IndexBean.NoticeBean, BaseViewHolder> {


    public NotiAdapter(@Nullable List<IndexBean.NoticeBean> data) {
        super(R.layout.item_noti, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, IndexBean.NoticeBean item) {
        ItemNotiBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.tvNotiContent.setText(item.getText());
        binding.tvNotiMoney.setText(NumberUtils.formatDouble(item.getMoney()));

    }
}
