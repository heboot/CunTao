package com.zonghong.cuntao.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.http.HttpClient;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.LogUtil;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.CurlgetBean;
import com.waw.hr.mutils.bean.IndexBean;
import com.waw.hr.mutils.bean.UserInfoBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.article.ArticleAddActivity;
import com.zonghong.cuntao.adapter.NotiAdapter;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.databinding.FragmentIndexBinding;
import com.zonghong.cuntao.databinding.FragmentTransitionBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.ImageUtils;
import com.zonghong.cuntao.utils.IntentUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TransitionFragment extends BaseFragment<FragmentTransitionBinding> {

    public static TransitionFragment newInstance() {
        Bundle args = new Bundle();
        TransitionFragment fragment = new TransitionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_transition;
    }

    @Override
    public void initUI() {
        binding.includeToolbar.tvTitle.setText("一键转换");
        binding.includeToolbar.vBack.setVisibility(View.GONE);
        loadingDialog  = DialogUtils.getLoadingDialog(_mActivity,"",false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        binding.tvCuntao.setOnClickListener(view -> {
            transition(1);
        });
        binding.tvTbk.setOnClickListener(view -> {
            transition(2);
        });
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        getInfo();

    }

    private void transition(int type) {
        if (StringUtils.isEmpty(binding.etContent.getText())) {
            tipDialog = DialogUtils.getFailDialog(_mActivity, "请输入淘口令/商品链接", true);
            tipDialog.show();
            return;
        }

        loadingDialog.show();

        params = new HashMap<>();
        params.put("type",type);
        params.put("code",binding.etContent.getText().toString());
        HttpClient.Builder.getServer().curlget(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<CurlgetBean>() {
            @Override
            public void onSuccess(BaseBean<CurlgetBean> baseBean) {
                dismissLoadingDialog();
                IntentUtils.intent2TransiionResultActivity(baseBean.getData());
            }

            @Override
            public void onError(BaseBean<CurlgetBean> baseBean) {
                dismissLoadingDialog();
                tipDialog = DialogUtils.getFailDialog(_mActivity, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

    private void getInfo() {
        params = new HashMap<>();
        params.put("page", 2);
        HttpClient.Builder.getServer().getExplain(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Map>() {
            @Override
            public void onSuccess(BaseBean<Map> baseBean) {
                binding.tvTebie.setText(String.valueOf(baseBean.getData().get("explain")));
            }

            @Override
            public void onError(BaseBean<Map> baseBean) {
                tipDialog = DialogUtils.getFailDialog(_mActivity, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }


}
