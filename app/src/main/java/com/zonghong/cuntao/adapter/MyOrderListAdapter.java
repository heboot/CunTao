package com.zonghong.cuntao.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.model.OrderModel;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.databinding.ItemMyOrderBinding;
import com.zonghong.cuntao.utils.ImageUtils;
import com.zonghong.cuntao.utils.NumberUtils;

import java.util.List;

public class MyOrderListAdapter extends BaseQuickAdapter<OrderModel, BaseViewHolder> {

    private int status;

    public MyOrderListAdapter(@Nullable List<OrderModel> data, int status) {
        super(R.layout.item_my_order, data);
        this.status = status;
    }


    @Override
    protected void convert(BaseViewHolder helper, OrderModel item) {
        ItemMyOrderBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.tvDate.setText(item.getTime());
        if (status == 2) {
            binding.tvStatus.setText("待发货  |");
        } else if (status == 3) {
            binding.tvStatus.setText("待收货  |");
        } else if (status == 4) {
            binding.tvStatus.setText("已完成  |");
        }

        ImageUtils.showImage(item.getImage(),binding.ivImg);

        binding.tvInfo.setText(item.getTitle());

        binding.tvPrice.setText(NumberUtils.formatDouble(item.getPrice()));

        binding.tvNum.setText(item.getCommodity_number()+"");

        binding.tvTotalMoney.setText("¥" + NumberUtils.formatDouble(item.getPay_sum()));


    }
}