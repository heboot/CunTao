package com.zonghong.cuntao.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.zxing.oned.ITFReader;
import com.waw.hr.mutils.model.OrderModel;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.databinding.ItemMyOrderBinding;
import com.zonghong.cuntao.fragment.MyOrderFragment;
import com.zonghong.cuntao.utils.ImageUtils;
import com.zonghong.cuntao.utils.IntentUtils;
import com.zonghong.cuntao.utils.NumberUtils;

import java.lang.ref.WeakReference;
import java.util.List;

public class MyOrderListAdapter extends BaseQuickAdapter<OrderModel, BaseViewHolder> {

    private int status;

    private WeakReference<MyOrderFragment> weakReference;

    public MyOrderListAdapter(@Nullable List<OrderModel> data, int status, WeakReference<MyOrderFragment> weakReference) {
        super(R.layout.item_my_order, data);
        this.status = status;
        this.weakReference = weakReference;
    }


    @Override
    protected void convert(BaseViewHolder helper, OrderModel item) {
        ItemMyOrderBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.tvDate.setText(item.getTime());
        if (status == 2) {
            binding.tvStatus.setText("待发货  |");
            binding.tvOption.setVisibility(View.GONE);
        } else if (status == 3) {
            binding.tvStatus.setText("待收货  |");
        } else if (status == 4) {
            binding.tvStatus.setText("已完成  |");
            binding.tvOption.setVisibility(View.GONE);
        }

        ImageUtils.showImage(item.getImage(), binding.ivImg);

        binding.tvInfo.setText(item.getTitle());

        binding.tvPrice.setText(NumberUtils.formatDouble(item.getPrice()));

        binding.tvNum.setText("x" + item.getCommodity_number() + "");

        binding.tvTotalMoney.setText("¥" + NumberUtils.formatDouble(item.getPay_sum()));

        binding.tvTotalMoneyTitle.setText("共" + item.getCommodity_number() + "件  应付总额：");

        binding.ivDel.setOnClickListener(view -> {
            weakReference.get().showDelDialog(item);
        });

        binding.tvOption.setOnClickListener(view -> {
            weakReference.get().showConfirmDialog(item);
        });

        binding.getRoot().setOnClickListener(view->{
            IntentUtils.intent2OrderDetailActivity(item.getOrder_id());
        });

    }
}