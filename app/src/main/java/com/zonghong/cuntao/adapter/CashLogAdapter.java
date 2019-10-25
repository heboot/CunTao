package com.zonghong.cuntao.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.ArticleModel;
import com.waw.hr.mutils.bean.CashLogModel;
import com.zonghong.cuntao.MAPP;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.databinding.ItemCashBinding;
import com.zonghong.cuntao.databinding.ItemCommunityBinding;
import com.zonghong.cuntao.utils.ImageUtils;
import com.zonghong.cuntao.utils.IntentUtils;
import com.zonghong.cuntao.utils.NumberUtils;

import java.util.List;


public class CashLogAdapter extends BaseQuickAdapter<CashLogModel, BaseViewHolder> {


    public CashLogAdapter(@Nullable List<CashLogModel> data) {
        super(R.layout.item_cash, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CashLogModel item) {
        ItemCashBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.tvName.setText(item.getInfo());
        binding.tvTime.setText(item.getUpdate_time());
        binding.tvMoney.setText(NumberUtils.formatDouble(item.getMoney()));
        binding.tvStatus.setText(item.getStatus());
        binding.getRoot().setOnClickListener(view->{
            IntentUtils.intent2CashStatusActivity(item);
        });



    }
}
