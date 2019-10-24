package com.zonghong.cuntao.activity.user;

import android.content.DialogInterface;
import android.view.View;

import com.example.http.HttpClient;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityBindBankBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewBankActivity extends BaseActivity<ActivityBindBankBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_bank;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("添加银行卡");
    }

    @Override
    public void initData() {
        loadingDialog = DialogUtils.getLoadingDialog(this, "", false);
    }

    @Override
    public void initListener() {
        binding.tvSubmit.setOnClickListener((v) -> {
            addBank();
        });
    }

    private void addBank() {
        if (StringUtils.isEmpty(binding.etBname.getText())) {
            tipDialog = DialogUtils.getFailDialog(this, "请输入开户行名称", true);
            tipDialog.show();
            return;
        }


        if (StringUtils.isEmpty(binding.etName.getText())) {
            tipDialog = DialogUtils.getFailDialog(this, "请输入持卡人名字", true);
            tipDialog.show();
            return;
        }

        if (StringUtils.isEmpty(binding.etAlipay.getText())) {
            tipDialog = DialogUtils.getFailDialog(this, "请输入卡号", true);
            tipDialog.show();
            return;
        }


        loadingDialog.show();
        params.put("user_name", binding.etName.getText().toString());
        params.put("account_name", binding.etBname.getText().toString());
        params.put("pay_account", binding.etAlipay.getText().toString());
        HttpClient.Builder.getServer().accountAdd(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getSuclDialog(NewBankActivity.this, baseBean.getMsg(), true);
                tipDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        finish();
                    }
                });
                tipDialog.show();
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getFailDialog(NewBankActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

}
