package com.zonghong.cuntao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.http.HttpClient;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.databinding.FragmentOrderBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderFragment extends BaseFragment<FragmentOrderBinding> {


    private int orderType = 1; //1 是村淘 2是淘宝客


    public static OrderFragment newInstance() {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initUI() {
        binding.includeToolbar.vBack.setVisibility(View.GONE);
        binding.includeToolbar.tvTitle.setText("我的订单");
        binding.qlytMoney.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x27)
                , getResources().getDimensionPixelOffset(R.dimen.y10), 0.30f);

        binding.qlytName.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x27)
                , getResources().getDimensionPixelOffset(R.dimen.y10), 0.30f);
        binding.qlytNo.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x27)
                , getResources().getDimensionPixelOffset(R.dimen.y10), 0.30f);
        loadingDialog = DialogUtils.getLoadingDialog(_mActivity, "", false);
        checkCTOrder();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        binding.tvSubmit.setOnClickListener(view -> {
            order();
        });
        binding.tvCtOrder.setOnClickListener(view -> {
            orderType = 1;
            checkCTOrder();
        });
        binding.tvTbOrder.setOnClickListener(view -> {
            orderType = 2;
            checkTBOrder();
        });
    }

    private void checkCTOrder() {
        binding.tvCtOrder.setBackgroundColor(Color.parseColor("#383b44"));
        binding.tvCtOrder.setTextColor(Color.parseColor("#ffffff"));
        binding.tvTbOrder.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.tvTbOrder.setTextColor(Color.parseColor("#383b44"));

    }

    private void checkTBOrder() {
        binding.tvTbOrder.setBackgroundColor(Color.parseColor("#383b44"));
        binding.tvTbOrder.setTextColor(Color.parseColor("#ffffff"));
        binding.tvCtOrder.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.tvCtOrder.setTextColor(Color.parseColor("#383b44"));

    }

    private void order() {


        if (StringUtils.isEmpty(binding.etNo.getText())) {
            tipDialog = DialogUtils.getFailDialog(_mActivity, "请输入订单编号", true);
            tipDialog.show();
            return;
        }

        if (StringUtils.isEmpty(binding.etName.getText())) {
            tipDialog = DialogUtils.getFailDialog(_mActivity, "请输入品名", true);
            tipDialog.show();
            return;
        }

        if (StringUtils.isEmpty(binding.etMoney.getText())) {
            tipDialog = DialogUtils.getFailDialog(_mActivity, "请输入金额", true);
            tipDialog.show();
            return;
        }

        params = new HashMap<>();
        params.put("order_number", binding.etNo.getText().toString());
        params.put("order_name", binding.etName.getText().toString());
        params.put("order_sum", binding.etMoney.getText().toString());
        params.put("type", orderType);
        HttpClient.Builder.getServer().orderIncrease(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                dismissLoadingDialog();
                tipDialog = DialogUtils.getSuclDialog(_mActivity, baseBean.getMsg(), true);
                tipDialog.show();
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                dismissLoadingDialog();
                tipDialog = DialogUtils.getFailDialog(_mActivity, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        getInfo();
    }

    private void getInfo() {
        params = new HashMap<>();
        params.put("page", 4);
        HttpClient.Builder.getServer().getExplain(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Map>() {
            @Override
            public void onSuccess(BaseBean<Map> baseBean) {
                binding.tvTebie.setText(String.valueOf(baseBean.getData().get("explain")));
            }

            @Override
            public void onError(BaseBean<Map> baseBean) {

            }
        });
    }
}
