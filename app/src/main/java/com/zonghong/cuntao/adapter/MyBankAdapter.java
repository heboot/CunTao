package com.zonghong.cuntao.adapter;


import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.BankListBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.user.MyBankActivity;
import com.zonghong.cuntao.databinding.ItemBankBinding;

import java.lang.ref.WeakReference;
import java.util.List;

public class MyBankAdapter extends BaseQuickAdapter<BankListBean, BaseViewHolder> {


    private WeakReference<MyBankActivity> weakReference;

    public MyBankAdapter(@Nullable List<BankListBean> data, WeakReference<MyBankActivity> weakReference) {
        super(R.layout.item_bank, data);
        this.weakReference = weakReference;
    }


    @Override
    protected void convert(BaseViewHolder helper, BankListBean item) {
        ItemBankBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.tvName.setText(item.getAccount_name() + "(" + item.getPay_account() + ")");
        binding.tvOption.setOnClickListener((v) -> {
            weakReference.get().showConfirmDialog(item);
        });

    }
}
