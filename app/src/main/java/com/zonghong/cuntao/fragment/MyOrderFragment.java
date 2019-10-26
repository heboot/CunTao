package com.zonghong.cuntao.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.http.HttpClient;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.BankListBean;
import com.waw.hr.mutils.bean.RankListBean;
import com.waw.hr.mutils.model.OrderModel;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.user.MyBankActivity;
import com.zonghong.cuntao.adapter.MyOrderListAdapter;
import com.zonghong.cuntao.adapter.RankListAdapter;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.common.OrderType;
import com.zonghong.cuntao.databinding.FragmentMyOrderBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyOrderFragment extends BaseFragment<FragmentMyOrderBinding> {

    private MyOrderListAdapter myOrderListAdapter;

    private int status;

    private QMUIDialog qmuiDialog, confirmDialog;


    public static MyOrderFragment newInstance(int orderStatus) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MKey.TYPE, orderStatus);
        MyOrderFragment myOrderFragment = new MyOrderFragment();
        myOrderFragment.setArguments(bundle);
        return myOrderFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_order;
    }

    @Override
    public void initUI() {

        binding.rvList.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
        loadingDialog = DialogUtils.getLoadingDialog(_mActivity, "", false);
    }

    @Override
    public void initData() {
        status = (int) getArguments().get(MKey.TYPE);
    }

    @Override
    public void initListener() {
        binding.mrv.setOnPullListener(new QMUIPullRefreshLayout.OnPullListener() {
            @Override
            public void onMoveTarget(int offset) {

            }

            @Override
            public void onMoveRefreshView(int offset) {

            }

            @Override
            public void onRefresh() {
                userInfo();
            }
        });
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        userInfo();
    }

    private void userInfo() {
        params.put("status", status);
        HttpClient.Builder.getServer().orderList(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<OrderModel>>() {
            @Override
            public void onSuccess(BaseBean<List<OrderModel>> baseBean) {
                binding.mrv.finishRefresh();
                if (myOrderListAdapter == null) {
                    myOrderListAdapter = new MyOrderListAdapter(baseBean.getData(), status, new WeakReference<>(MyOrderFragment.this));
                    binding.rvList.setAdapter(myOrderListAdapter);

                } else {
                    if (page == 1) {
                        myOrderListAdapter.setNewData(baseBean.getData());
                    } else {
                        myOrderListAdapter.addData(baseBean.getData());
                    }

                }
            }

            @Override
            public void onError(BaseBean<List<OrderModel>> baseBean) {

            }
        });
    }

    public void showConfirmDialog(OrderModel item) {

        confirmDialog = new QMUIDialog.MessageDialogBuilder(_mActivity)
                .setMessage("确定要确认订单吗")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        confirmDialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        confirmDialog.dismiss();
                        confirmOrder(item.getOrder_id());
                    }
                })
                .create();

        confirmDialog.show();
    }

    public void showDelDialog(OrderModel item) {

        qmuiDialog = new QMUIDialog.MessageDialogBuilder(_mActivity)
                .setMessage("确定要删除订单吗")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        qmuiDialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        qmuiDialog.dismiss();
                        delOrder(item.getOrder_id());
                    }
                })
                .create();

        qmuiDialog.show();
    }

    private void delOrder(String id) {
        loadingDialog.show();
        params = new HashMap<>();
        params.put("order_id", id);
        HttpClient.Builder.getServer().orderDel(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getSuclDialog(_mActivity, baseBean.getMsg(), true);
                tipDialog.show();
                userInfo();
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getFailDialog(_mActivity, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

    private void confirmOrder(String id) {
        loadingDialog.show();
        params = new HashMap<>();
        params.put("order_id", id);
        HttpClient.Builder.getServer().orderConfirm(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getSuclDialog(_mActivity, baseBean.getMsg(), true);
                tipDialog.show();
                userInfo();
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getFailDialog(_mActivity, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

}
