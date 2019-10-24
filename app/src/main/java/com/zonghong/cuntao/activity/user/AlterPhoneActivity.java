package com.zonghong.cuntao.activity.user;

import android.content.DialogInterface;
import android.view.View;

import com.example.http.HttpClient;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.MStatusBarUtils;
import com.waw.hr.mutils.ObserableUtils;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.loginregister.ForgetActivity;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityAlterPhoneBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AlterPhoneActivity extends BaseActivity<ActivityAlterPhoneBinding> {


    private Observer countDownObserver;

    private Observable observable;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_alter_phone;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("更换手机号");
        MStatusBarUtils.setActivityLightMode(this);
        QMUIStatusBarHelper.translucent(this);
        loadingDialog = DialogUtils.getLoadingDialog(this, "", false);
    }

    @Override
    public void initData() {
        binding.tvTitle.setText("更换手机号以后，下次登录可使用新手机号登录。当前手机号为：" +
                UserService.getInstance().getPhone() + "\n" +
                "                        ");
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
        binding.tvSubmit.setOnClickListener((v) -> {
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


        loadingDialog.show();

        params.put("up_mobile", binding.etPhone.getText().toString());
        params.put(MKey.CODE, StringUtils.isEmpty(binding.etCode.getText()) ? "" : binding.etCode.getText());
        HttpClient.Builder.getServer().changeMobile(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                dismissLoadingDialog();
                UserService.getInstance().setPhone(binding.etPhone.getText().toString());
                tipDialog = DialogUtils.getSuclDialog(AlterPhoneActivity.this, baseBean.getMsg(), true);
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
                tipDialog = DialogUtils.getFailDialog(AlterPhoneActivity.this, baseBean.getMsg(), true);
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


                observable = ObserableUtils.countdown(60);

                observable.subscribe(countDownObserver);
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                binding.tvSendcode.setEnabled(true);
                tipDialog = DialogUtils.getFailDialog(AlterPhoneActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

}
