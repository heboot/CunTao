package com.zonghong.cuntao.activity.order;

import android.view.View;

import com.example.http.HttpClient;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.UserInfoBean;
import com.waw.hr.mutils.model.OrderModel;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityOrderDetailBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.ImageUtils;
import com.zonghong.cuntao.utils.NumberUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderDetailActivity extends BaseActivity<ActivityOrderDetailBinding> {

    private String orderId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("订单详情");
    }

    @Override
    public void initData() {
        orderId = (String) getIntent().getExtras().get(MKey.DATA);
        userInfo();
    }

    @Override
    public void initListener() {

    }


    private void userInfo() {
        params = new HashMap<>();
        params.put("order_id", orderId);
        HttpClient.Builder.getServer().orderDetail(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<OrderModel>() {
            @Override
            public void onSuccess(BaseBean<OrderModel> baseBean) {
                binding.tvName.setText(baseBean.getData().getConsignee());
                binding.tvPhone.setText(baseBean.getData().getMobile());
                binding.tvAddress.setText(baseBean.getData().getAddress());

                binding.includeGoods.tvNum.setText("x" + baseBean.getData().getCommodity_number() + "");
                ImageUtils.showImage(baseBean.getData().getUrl(), binding.includeGoods.ivImg);
                binding.includeGoods.tvPrice.setText(NumberUtils.formatDouble(baseBean.getData().getPrice()));
                binding.includeGoods.tvTitle.setText(baseBean.getData().getTitle());

                binding.tvFreightMoney.setText(baseBean.getData().getCarriage());
                binding.tvTotalMoney.setText(NumberUtils.formatDouble(baseBean.getData().getPay_sum()));

                binding.tvOrderNo.setText(baseBean.getData().getOrder_number());
                binding.tvPlaceTime.setText(baseBean.getData().getEnd_time());
            }

            @Override
            public void onError(BaseBean<OrderModel> baseBean) {

            }
        });
    }
}
