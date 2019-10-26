package com.zonghong.cuntao.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.http.HttpClient;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.UserInfoBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.RankActivity;
import com.zonghong.cuntao.activity.user.CashActivity;
import com.zonghong.cuntao.activity.order.MyOrderActivity;
import com.zonghong.cuntao.activity.SettingActivity;
import com.zonghong.cuntao.activity.user.MyAtricleActivity;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.databinding.FragmentMyBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.ImageUtils;
import com.zonghong.cuntao.utils.IntentUtils;
import com.zonghong.cuntao.utils.NumberUtils;
import com.zonghong.cuntao.utils.TextUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyFragment extends BaseFragment<FragmentMyBinding> {

    private View.OnClickListener orderClickListener;

    private View.OnClickListener rankClickListener;

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {
        userInfo();
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        userInfo();
    }

    @Override
    public void initListener() {
        orderClickListener = (view -> {
            IntentUtils.doIntent(MyOrderActivity.class);
        });
        rankClickListener = (view -> {
            int rankType = 1;
            switch (view.getId()) {
                case R.id.tv_rank_day:
                    rankType = 1;
                    break;
                case R.id.tv_rank_week:
                    rankType = 2;
                    break;
                case R.id.tv_rank_month:
                    rankType = 3;
                    break;
            }
            IntentUtils.intent2RankActivity(rankType);
        });
        binding.tvRankDay.setOnClickListener(rankClickListener);
        binding.tvRankMonth.setOnClickListener(rankClickListener);
        binding.tvRankWeek.setOnClickListener(rankClickListener);
        binding.llytDaifahuo.setOnClickListener(orderClickListener);
        binding.llytDaishouhuo.setOnClickListener(orderClickListener);
        binding.llytYiwancheng.setOnClickListener(orderClickListener);
        binding.vSetting.setOnClickListener(view -> {
            IntentUtils.doIntent(SettingActivity.class);
        });
        binding.v1.setOnClickListener(view->{
            IntentUtils.doIntent(SettingActivity.class);
        });
        binding.tvMyWenzhang.setOnClickListener(view -> {
            IntentUtils.doIntent(MyAtricleActivity.class);
        });
        binding.tvCash.setOnClickListener(view -> {
            IntentUtils.doIntent(CashActivity.class);
        });
        binding.tvCopy.setOnClickListener(view -> {
            TextUtils.copyText(UserService.getInstance().getUserInfoBean().getInvitation_code() + "");
        });
        binding.vKf.setOnClickListener(view -> {
            if (!isQQClientAvailable(_mActivity)) {
                tipDialog = DialogUtils.getFailDialog(_mActivity, "手机上没有安装QQ", true);
                tipDialog.show();
                return;
            }
            final String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=2049723461&version=1";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
        });
//        binding.mrv.setOnPullListener(new QMUIPullRefreshLayout.OnPullListener() {
//            @Override
//            public void onMoveTarget(int offset) {
//
//            }
//
//            @Override
//            public void onMoveRefreshView(int offset) {
//
//            }
//
//            @Override
//            public void onRefresh() {
//                userInfo();
//            }
//        });

    }

    public boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }


    private void userInfo() {
        HttpClient.Builder.getServer().profileCenter(UserService.getInstance().getToken()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<UserInfoBean>() {
            @Override
            public void onSuccess(BaseBean<UserInfoBean> baseBean) {
//                binding.mrv.finishRefresh();
                ImageUtils.showAvatar(baseBean.getData().getImage(), binding.ivAvatar);
                binding.tvName.setText(baseBean.getData().getNick_name());
                UserService.getInstance().setUserInfoBean(baseBean.getData());

                binding.tvCurrentBrokerage.setText(NumberUtils.formatDouble(baseBean.getData().getCurrentMonth()));
                binding.tvUpBrokerage.setText(NumberUtils.formatDouble(baseBean.getData().getPreviousMonth()));

                binding.tvBalance.setText(NumberUtils.formatDouble(baseBean.getData().getMoney()));
                binding.tvYqcode.setText("邀请码: " + baseBean.getData().getInvitation_code() + "");
            }

            @Override
            public void onError(BaseBean<UserInfoBean> baseBean) {

            }
        });
    }
}
