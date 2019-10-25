package com.zonghong.cuntao.fragment;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.http.HttpClient;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout;
import com.waw.hr.mutils.ToastUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.ArticleIndexBean;
import com.waw.hr.mutils.bean.UserInfoBean;
import com.zonghong.cuntao.MAPP;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.PublishContentActivity;
import com.zonghong.cuntao.activity.article.ArticleAddActivity;
import com.zonghong.cuntao.adapter.CommunityAdapter;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.databinding.FragmentCommunityBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.ImageUtils;
import com.zonghong.cuntao.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CommunityFragment extends BaseFragment<FragmentCommunityBinding> {

    private CommunityAdapter communityAdapter;

    private ArticleIndexBean articleIndexBean;

    public static CommunityFragment newInstance() {
        Bundle args = new Bundle();
        CommunityFragment fragment = new CommunityFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    public void initUI() {
        binding.includeToolbar.tvTitle.setText("社区");
        binding.includeToolbar.vBack.setVisibility(View.GONE);
        binding.rvList.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        page = 1;
        userInfo();
    }

    @Override
    public void initListener() {
        binding.vPublish.setOnClickListener(view -> {
            if(articleIndexBean.getArticle_authoratize() == 1){
                IntentUtils.doIntent(ArticleAddActivity.class);
            }else{
                ToastUtils.show(MAPP.mapp,"你还不能发表文章");
            }

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

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        userInfo();
    }

    private void userInfo() {
        params.put("page", page);
        HttpClient.Builder.getServer().articleIndex(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<ArticleIndexBean>() {
            @Override
            public void onSuccess(BaseBean<ArticleIndexBean> baseBean) {
                articleIndexBean = baseBean.getData();
                total = baseBean.getData().getTotalPage();
                binding.mrv.finishRefresh();
                if (communityAdapter == null) {
                    communityAdapter = new CommunityAdapter(baseBean.getData().getList());
                    binding.rvList.setAdapter(communityAdapter);
                    communityAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            if (page >= total) {
                                ToastUtils.show(MAPP.mapp, "到底啦");
                                communityAdapter.loadMoreEnd();
                                return;
                            }
                            page = page + 1;
                            userInfo();
                        }
                    }, binding.rvList);
                } else {
                    if (page == 1) {
                        communityAdapter.setNewData(baseBean.getData().getList());
                    } else {
                        communityAdapter.addData(baseBean.getData().getList());
                    }
                    communityAdapter.loadMoreComplete();
                }
            }

            @Override
            public void onError(BaseBean<ArticleIndexBean> baseBean) {

            }
        });
    }
}
