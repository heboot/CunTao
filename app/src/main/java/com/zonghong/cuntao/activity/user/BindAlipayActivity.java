package com.zonghong.cuntao.activity.user;

import android.content.DialogInterface;
import android.view.View;

import com.example.http.HttpClient;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.MStatusBarUtils;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.UserInfoBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.SettingActivity;
import com.zonghong.cuntao.activity.loginregister.LoginActivity;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityBindAlipayBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.ImageUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BindAlipayActivity extends BaseActivity<ActivityBindAlipayBinding> {

    private String name, account;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_alipay;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("支付宝绑定");
        MStatusBarUtils.setActivityLightMode(this);
        QMUIStatusBarHelper.translucent(this);
        loadingDialog = DialogUtils.getLoadingDialog(this, "", false);
    }

    @Override
    public void initData() {
        account = (String) getIntent().getExtras().get(MKey.ACCOUNT);
        name = (String) getIntent().getExtras().get(MKey.NAME);

        if(!StringUtils.isEmpty(account)){
            binding.etAlipay.setText(account);
        }
        if(!StringUtils.isEmpty(name)){
            binding.etName.setText(name);
        }
    }

    @Override
    public void initListener() {
        binding.tvSubmit.setOnClickListener(view -> {
            changeAlipay();
        });
    }

    private void changeAlipay() {

        if (StringUtils.isEmpty(binding.etName.getText())) {
            tipDialog = DialogUtils.getFailDialog(this, "请输入姓名", true);
            tipDialog.show();
            return;
        }

        if (StringUtils.isEmpty(binding.etAlipay.getText())) {
            tipDialog = DialogUtils.getFailDialog(this, "请输入支付宝账号", true);
            tipDialog.show();
            return;
        }

        loadingDialog.show();
        params = new HashMap<>();
        params.put("payAccount", binding.etAlipay.getText().toString());
        params.put("userName", binding.etName.getText().toString());
        HttpClient.Builder.getServer().changePayAccount(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                dismissLoadingDialog();
                tipDialog = DialogUtils.getSuclDialog(BindAlipayActivity.this, baseBean.getMsg(), true);
                tipDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                    }
                });
                tipDialog.show();
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                dismissLoadingDialog();
                tipDialog = DialogUtils.getFailDialog(BindAlipayActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }
}
