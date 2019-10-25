package com.zonghong.cuntao.activity.user;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.http.HttpClient;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.ToastUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.ArticleIndexBean;
import com.waw.hr.mutils.bean.CashLogModel;
import com.zonghong.cuntao.MAPP;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.adapter.CashLogAdapter;
import com.zonghong.cuntao.adapter.CommunityAdapter;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityCashLogBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.IntentUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CashLogActivity extends BaseActivity<ActivityCashLogBinding> {

    private CashLogAdapter cashLogAdapter;

    private int cashType = 1;//1 已提现  0 其他

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cash_log;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("提现记录");
        checkCashed();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        binding.tvCashed.setOnClickListener(view -> {
            cashType = 1;
            checkCashed();
            userInfo();
        });
        binding.tvCashIng.setOnClickListener(view -> {
            cashType = 0;
            checkCashing();
            userInfo();
        });
        binding.mrv.setOnPullListener(new QMUIPullRefreshLayout.OnPullListener() {
            @Override
            public void onMoveTarget(int offset) {

            }

            @Override
            public void onMoveRefreshView(int offset) {

            }

            @Override
            public void onRefresh() {
                page = 1;
                userInfo();
            }
        });
    }

    private void checkCashed() {
        binding.tvCashed.setBackgroundColor(Color.parseColor("#383b44"));
        binding.tvCashed.setTextColor(Color.parseColor("#ffffff"));
        binding.tvCashIng.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.tvCashIng.setTextColor(Color.parseColor("#383b44"));

    }

    private void checkCashing() {
        binding.tvCashIng.setBackgroundColor(Color.parseColor("#383b44"));
        binding.tvCashIng.setTextColor(Color.parseColor("#ffffff"));
        binding.tvCashed.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.tvCashed.setTextColor(Color.parseColor("#383b44"));

    }

    private void userInfo() {
        params.put("type", cashType);
        HttpClient.Builder.getServer().withdrawIndex(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<CashLogModel>>() {
            @Override
            public void onSuccess(BaseBean<List<CashLogModel>> baseBean) {
                if (cashLogAdapter == null) {
                    cashLogAdapter = new CashLogAdapter(baseBean.getData());
                    binding.rvList.setAdapter(cashLogAdapter);
                } else {
                    if (page == 1) {
                        cashLogAdapter.setNewData(baseBean.getData());
                    } else {
                        cashLogAdapter.addData(baseBean.getData());
                    }
                    cashLogAdapter.loadMoreComplete();
                }
            }

            @Override
            public void onError(BaseBean<List<CashLogModel>> baseBean) {

            }
        });
    }
}
