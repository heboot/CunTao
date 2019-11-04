package com.zonghong.cuntao.activity.common;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.waw.hr.mutils.MStatusBarUtils;
import com.waw.hr.mutils.ObserableUtils;
import com.waw.hr.mutils.StringUtils;
import com.zonghong.cuntao.MainActivity;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.loginregister.LoginActivity;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityLoadingBinding;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.IntentUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoadingActivity extends BaseActivity<ActivityLoadingBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {
        MStatusBarUtils.setActivityLightMode(this);
        QMUIStatusBarHelper.translucent(this);
        ObserableUtils.countdown(1).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                if (integer == 0) {
                    if (!StringUtils.isEmpty(UserService.getInstance().getToken())) {
                        IntentUtils.doIntent(MainActivity.class);
                    } else {
                        IntentUtils.doIntent(LoginActivity.class);

                    }
                    finish();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void initListener() {

    }
}
