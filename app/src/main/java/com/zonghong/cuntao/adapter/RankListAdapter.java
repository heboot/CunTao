package com.zonghong.cuntao.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.RankListBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.databinding.ItemRankBinding;
import com.zonghong.cuntao.utils.NumberUtils;

import java.util.List;


public class RankListAdapter extends BaseQuickAdapter<RankListBean, BaseViewHolder> {


    public RankListAdapter(@Nullable List<RankListBean> data) {
        super(R.layout.item_rank, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, RankListBean item) {
        ItemRankBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.tvMoney.setText(NumberUtils.formatDouble(item.getMoney()));
        binding.tvName.setText(item.getUser_mobile());

        switch (helper.getLayoutPosition()) {
            case 0:
                binding.vRankTop.setBackgroundResource(R.mipmap.icon_rank_1);
                break;

            case 1:
                binding.vRankTop.setBackgroundResource(R.mipmap.icon_rank_2);
                break;
            case 2:
                binding.vRankTop.setBackgroundResource(R.mipmap.icon_rank_3);
                break;
            default:
                binding.tvNum.setVisibility(View.VISIBLE);
                binding.vRankTop.setVisibility(View.GONE);
                break;
        }



    }
}
