package com.zonghong.cuntao.activity.loginregister;

import com.example.http.HttpClient;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.MStatusBarUtils;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.LoginBean;
import com.zonghong.cuntao.MainActivity;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityLoginBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.IntentUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initUI() {
        binding.qlytContainer.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x10)
                , getResources().getDimensionPixelOffset(R.dimen.y10), 0.30f);
        MStatusBarUtils.setActivityLightMode(this);
        QMUIStatusBarHelper.translucent(this);
        loadingDialog = DialogUtils.getLoadingDialog(this, "", false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        binding.btnLogin.setOnClickListener(view -> {
            login();
        });
        binding.tvRegister.setOnClickListener(view -> {
            IntentUtils.doIntent(RegisterActivity.class);
        });
        binding.tvForget.setOnClickListener(view -> {
            IntentUtils.doIntent(ForgetActivity.class);
        });
    }

    private void login() {
        if (StringUtils.isEmpty(binding.etPhone.getText())) {
            tipDialog = DialogUtils.getFailDialog(this, "请输入手机号码", true);
            tipDialog.show();
            return;
        }

        if (StringUtils.isEmpty(binding.etPwd.getText())) {
            tipDialog = DialogUtils.getFailDialog(this, "请输入密码", true);
            tipDialog.show();
            return;
        }

        loadingDialog.show();

        params.put(MKey.MOBILE, binding.etPhone.getText());
        params.put(MKey.PASSWORD, binding.etPwd.getText());

        HttpClient.Builder.getServer().login(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<LoginBean>() {
            @Override
            public void onSuccess(BaseBean<LoginBean> baseBean) {
                loadingDialog.dismiss();
                UserService.getInstance().setPhone(binding.etPhone.getText().toString());
                UserService.getInstance().setToken(baseBean.getData().getToken());
                UserService.getInstance().setRy_token(baseBean.getData().getRy_token());
                IntentUtils.doIntent(MainActivity.class);
//                RongUtils.connect(baseBean.getData().getRy_token());
            }

            @Override
            public void onError(BaseBean<LoginBean> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getFailDialog(LoginActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }
}
