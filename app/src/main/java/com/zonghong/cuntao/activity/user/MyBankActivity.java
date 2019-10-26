package com.zonghong.cuntao.activity.user;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.http.HttpClient;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.BankListBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.adapter.MyBankAdapter;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityMyBankBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.IntentUtils;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyBankActivity extends BaseActivity<ActivityMyBankBinding> {

    private MyBankAdapter myBankAdapter;

    private QMUIDialog qmuiDialog;

    private boolean choose = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_bank;
    }

    @Override
    public void initUI() {
        loadingDialog = DialogUtils.getLoadingDialog(this, "", false);
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("我的银行卡");
        binding.rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        loadingDialog = DialogUtils.getLoadingDialog(this, "", false);
    }

    @Override
    public void initData() {
        if(getIntent() !=null && getIntent().getExtras() != null){
            choose = (boolean) getIntent().getExtras().get(MKey.TYPE);
        }
        bankList();
    }

    @Override
    public void initListener() {
        binding.tvSubmit.setOnClickListener((v) -> {
            IntentUtils.doIntent(NewBankActivity.class);
        });
    }

    public void showConfirmDialog(BankListBean item) {
        if (qmuiDialog == null) {
            qmuiDialog = new QMUIDialog.MessageDialogBuilder(this)
                    .setMessage("确定要解绑银行卡吗")
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
                            kRelease(item.getID());
                        }
                    })
                    .create();
        }
        qmuiDialog.show();
    }

    private void kRelease(int id) {
        loadingDialog.show();
        params = new HashMap<>();
        params.put("id", id);
        HttpClient.Builder.getServer().accountDel(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getSuclDialog(MyBankActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
                bankList();
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getFailDialog(MyBankActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (myBankAdapter != null) {
            bankList();
        }
    }

    private void bankList() {
        loadingDialog.show();
        params = new HashMap<>();
        HttpClient.Builder.getServer().accountList(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<BankListBean>>() {
            @Override
            public void onSuccess(BaseBean<List<BankListBean>> baseBean) {
                loadingDialog.dismiss();
                if (myBankAdapter == null) {
                    if (baseBean.getData() != null) {
                        myBankAdapter = new MyBankAdapter(baseBean.getData(), new WeakReference<>(MyBankActivity.this),choose);
                        binding.rvList.setAdapter(myBankAdapter);
                    }
                } else {
                    myBankAdapter.getData().clear();
                    myBankAdapter.setNewData(baseBean.getData());
                    myBankAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(BaseBean<List<BankListBean>> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getFailDialog(MyBankActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

}
