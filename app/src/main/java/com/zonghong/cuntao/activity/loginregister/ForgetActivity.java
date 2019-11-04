package com.zonghong.cuntao.activity.loginregister;

import android.content.DialogInterface;

import com.example.http.HttpClient;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.MStatusBarUtils;
import com.waw.hr.mutils.ObserableUtils;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityForgetBinding;
import com.zonghong.cuntao.http.HttpObserver;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ForgetActivity extends BaseActivity<ActivityForgetBinding> {

    private String code;

    private Observer countDownObserver;

    private Observable observable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget;
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
    public  void initData() {
        countDownObserver = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                if (integer <= 0) {
                    binding.tvSendcode.setEnabled(true);
                    binding.tvSendcode.setText("获取验证码");
                } else {
                    binding.tvSendcode.setText(integer + "");
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    public void initListener() {
        binding.tvSendcode.setOnClickListener((v) -> {

            sendCode();
        });
        binding.btnSubmit.setOnClickListener((v) -> {
            register();
        });
    }

    private void register() {


        if (StringUtils.isEmpty(binding.etPhone.getText())) {
            tipDialog = DialogUtils.getFailDialog(this, "请输入手机号码", true);
            tipDialog.show();
            return;
        }
        if (StringUtils.isEmpty(binding.etCode.getText())) {
            tipDialog = DialogUtils.getFailDialog(this, "请输入验证码", true);
            tipDialog.show();
            return;
        }
        if (StringUtils.isEmpty(binding.etPwd.getText())) {
            tipDialog = DialogUtils.getFailDialog(this, "请输入密码", true);
            tipDialog.show();
            return;
        }
//        if (!StringUtils.isEmpty(code) && !code.equals(binding.etCode.getText().toString().trim())) {
//            tipDialog = DialogUtils.getFailDialog(this, "验证码不正确", true);
//            tipDialog.show();
//            return;
//        }

        loadingDialog.show();

        params.put(MKey.MOBILE, binding.etPhone.getText());
        params.put(MKey.CODE, StringUtils.isEmpty(binding.etCode.getText()) ? "" : binding.etCode.getText());
        params.put(MKey.PASSWORD, binding.etPwd.getText());
        HttpClient.Builder.getServer().changePwd(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                dismissLoadingDialog();
                tipDialog = DialogUtils.getSuclDialog(ForgetActivity.this, baseBean.getMsg(), true);
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
                dismissLoadingDialog();
                tipDialog = DialogUtils.getFailDialog(ForgetActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });


    }

    private void sendCode() {
        if (StringUtils.isEmpty(binding.etPhone.getText())) {
            tipDialog = DialogUtils.getFailDialog(this, "请输入手机号码", true);
            tipDialog.show();
            return;
        }
        binding.tvSendcode.setEnabled(false);
        params.put(MKey.MOBILE, binding.etPhone.getText());
        HttpClient.Builder.getServer().sendVerify(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {

                code =String.valueOf(baseBean.getData());

                observable = ObserableUtils.countdown(60);

                observable.subscribe(countDownObserver);
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                binding.tvSendcode.setEnabled(true);
                tipDialog = DialogUtils.getFailDialog(ForgetActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

}
