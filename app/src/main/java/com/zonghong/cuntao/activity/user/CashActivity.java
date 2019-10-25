package com.zonghong.cuntao.activity.user;

import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;

import com.example.http.HttpClient;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.BankListBean;
import com.waw.hr.mutils.bean.CashLogModel;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityCashBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.IntentUtils;
import com.zonghong.cuntao.utils.NumberUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CashActivity extends BaseActivity<ActivityCashBinding> {

    private String aliName, aliAccount, aliId;

    private BankListBean bankListBean;

    private int cashType = 1;//1 支付宝 2银行卡

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cash;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.tvTitle.setText("提现");
        binding.tvCashAlipay.setBackgroundColor(Color.parseColor("#383b44"));
        binding.tvCashAlipay.setTextColor(Color.parseColor("#ffffff"));
    }

    @Override
    public void initData() {
        binding.tvCashTip.setText("可提现金额:" + NumberUtils.formatDouble(UserService.getInstance().getUserInfoBean().getMoney()));
        aliInfo();
        bankList();
    }

    @Override
    public void initListener() {
        binding.tvCashAlipay.setOnClickListener(view -> {
            cashType = 1;
            checkAlipay();
        });
        binding.tvCashBank.setOnClickListener(view -> {
            cashType = 2;
            checkBank();
        });
        binding.tvSubmit.setOnClickListener(view -> {
            cash();
        });
        binding.cashAll.setOnClickListener(view -> {
            binding.etMoney.setText(NumberUtils.formatDouble(UserService.getInstance().getUserInfoBean().getMoney()));
        });



    }

    private void checkAlipay() {
        binding.tvCashAlipay.setBackgroundColor(Color.parseColor("#383b44"));
        binding.tvCashAlipay.setTextColor(Color.parseColor("#ffffff"));
        binding.tvCashBank.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.tvCashBank.setTextColor(Color.parseColor("#383b44"));
        if (StringUtils.isEmpty(aliName) && StringUtils.isEmpty(aliAccount)) {
            binding.vIcon.setBackgroundResource(R.mipmap.icon_add);
            binding.tvAccount.setVisibility(View.GONE);
            binding.tvTitle.setVisibility(View.GONE);
            binding.tvBind.setText("绑定支付宝");
            binding.tvBind.setVisibility(View.VISIBLE);
        } else {
            binding.vIcon.setBackgroundResource(R.mipmap.icon_alipay);
            binding.tvBind.setVisibility(View.GONE);
            binding.tvAccount.setVisibility(View.VISIBLE);
            binding.tvTitle.setVisibility(View.VISIBLE);
            binding.tvAccount.setText(aliAccount);
            binding.tvTitle.setText(aliName);
        }
    }

    private void checkBank() {
        binding.tvCashBank.setBackgroundColor(Color.parseColor("#383b44"));
        binding.tvCashBank.setTextColor(Color.parseColor("#ffffff"));
        binding.tvCashAlipay.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.tvCashAlipay.setTextColor(Color.parseColor("#383b44"));
        if (bankListBean == null) {
            binding.vIcon.setBackgroundResource(R.mipmap.icon_add);
            binding.tvAccount.setVisibility(View.GONE);
            binding.tvTitle.setVisibility(View.GONE);
            binding.tvBind.setText("绑定银行卡");
            binding.tvBind.setVisibility(View.VISIBLE);
            binding.tvBind.setOnClickListener(view -> {
                IntentUtils.doIntent(BindAlipayActivity.class);
            });
        } else {
            binding.vIcon.setBackgroundResource(R.mipmap.icon_bank);
            binding.tvBind.setVisibility(View.GONE);
            binding.tvAccount.setVisibility(View.GONE);
            binding.tvTitle.setVisibility(View.VISIBLE);
            binding.tvTitle.setText(bankListBean.getAccount_name() + "(" + bankListBean.getPay_account() + ")");
            binding.tvBind.setOnClickListener(view -> {
                IntentUtils.doIntent(MyBankActivity.class);
            });
        }
    }

    /**
     * 返回数据 为什么是数组？ 太骚了 我也无法解释
     */
    private void aliInfo() {
        HttpClient.Builder.getServer().alipayInfo(UserService.getInstance().getToken()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<Map>>() {
            @Override
            public void onSuccess(BaseBean<List<Map>> baseBean) {
                String ali = String.valueOf(baseBean.getData().get(0).get("alipay"));
                if (!ali.equals("110.0")) {
                    aliName = String.valueOf(baseBean.getData().get(0).get("user_name"));
                    aliAccount = ali;
                    aliId = String.valueOf(baseBean.getData().get(0).get("ID"));
                }

                checkAlipay();
            }

            @Override
            public void onError(BaseBean<List<Map>> baseBean) {

            }
        });
    }

    private void cash() {

        if (StringUtils.isEmpty(binding.etMoney.getText())) {
            tipDialog = DialogUtils.getFailDialog(this, "请输入提现金额", true);
            tipDialog.show();
            return;
        }

        if (cashType == 1 && StringUtils.isEmpty(aliId)) {
            tipDialog = DialogUtils.getFailDialog(this, "请先绑定支付宝", true);
            tipDialog.show();
            return;
        }


        if (cashType == 2 && bankListBean == null) {
            tipDialog = DialogUtils.getFailDialog(this, "请先绑定银行卡", true);
            tipDialog.show();
            return;
        }


        params = new HashMap<>();
        params.put("moeny", binding.etMoney.getText().toString());
        params.put("account", cashType == 1 ? aliId : bankListBean.getID());
        HttpClient.Builder.getServer().moneyWithdrawDeposit(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                tipDialog = DialogUtils.getSuclDialog(CashActivity.this, baseBean.getMsg(), true);
                CashLogModel cashLogModel = new CashLogModel();
                cashLogModel.setInfo(cashType == 1 ? "支付宝(" + aliName + ")" : bankListBean.getAccount_name() + "(" + bankListBean.getPay_account() + ")");
                cashLogModel.setMoney(Double.parseDouble(binding.etMoney.getText().toString()));
                tipDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        IntentUtils.intent2CashStatusActivity(cashLogModel);
                        finish();
                    }
                });
                tipDialog.show();
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                tipDialog = DialogUtils.getFailDialog(CashActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

    private void bankList() {

        params = new HashMap<>();
        HttpClient.Builder.getServer().accountList(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<BankListBean>>() {
            @Override
            public void onSuccess(BaseBean<List<BankListBean>> baseBean) {
                if (baseBean.getData() != null && baseBean.getData().size() > 0) {
                    bankListBean = baseBean.getData().get(0);
                }
            }

            @Override
            public void onError(BaseBean<List<BankListBean>> baseBean) {
//                loadingDialog.dismiss();
//                tipDialog = DialogUtils.getFailDialog(CashActivity.this, baseBean.getMsg(), true);
//                tipDialog.show();
            }
        });
    }
}
