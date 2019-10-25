package com.zonghong.cuntao.activity.user;

import android.view.View;

import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.bean.CashLogModel;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityCashStatusBinding;

public class CashStatusActivity extends BaseActivity<ActivityCashStatusBinding> {

    private CashLogModel cashLogModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cash_status;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("提现详情");
    }

    @Override
    public void initData() {
        //提现状态; 0正在审核; 1提现成功;2提现失败
        cashLogModel = (CashLogModel) getIntent().getExtras().get(MKey.DATA);
        if (cashLogModel.getWithdrawalStatus() == 0) {
            binding.ivStatus.setBackgroundResource(R.mipmap.icon_cash_ing);
        } else if (cashLogModel.getWithdrawalStatus() == 1) {
            binding.ivStatus.setBackgroundResource(R.mipmap.icon_cash_suc);
        } else if (cashLogModel.getWithdrawalStatus() == 2) {
            binding.ivStatus.setBackgroundResource(R.mipmap.icon_cash_fail);
        }

        binding.tvCashChannel.setText(cashLogModel.getInfo());
        binding.tvCashMoney.setText(cashLogModel.getMoney() + "");
    }

    @Override
    public void initListener() {
        binding.tvSubmit.setOnClickListener(view->{
            finish();
        });
    }
}
