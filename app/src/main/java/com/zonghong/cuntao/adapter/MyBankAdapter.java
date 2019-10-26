package com.zonghong.cuntao.adapter;


import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.BankListBean;
import com.waw.hr.mutils.event.UserEvent;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.user.MyBankActivity;
import com.zonghong.cuntao.databinding.ItemBankBinding;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.List;

public class MyBankAdapter extends BaseQuickAdapter<BankListBean, BaseViewHolder> {


    private WeakReference<MyBankActivity> weakReference;

    private boolean choose;

    public MyBankAdapter(@Nullable List<BankListBean> data, WeakReference<MyBankActivity> weakReference, boolean choose) {
        super(R.layout.item_bank, data);
        this.weakReference = weakReference;
        this.choose = choose;
    }


    @Override
    protected void convert(BaseViewHolder helper, BankListBean item) {
        ItemBankBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.tvName.setText(item.getAccount_name() + "(" + item.getPay_account() + ")");
        if(choose){
            binding.tvOption.setVisibility(View.GONE);
            binding.getRoot().setOnClickListener(view->{
                EventBus.getDefault().post(new UserEvent.CHOOSE_BANK_EVENT(item));
            });
        }
        binding.tvOption.setOnClickListener((v) -> {
            weakReference.get().showConfirmDialog(item);
        });

    }
}
