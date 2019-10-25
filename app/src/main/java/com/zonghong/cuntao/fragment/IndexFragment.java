package com.zonghong.cuntao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.http.HttpClient;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout;
import com.waw.hr.mutils.LogUtil;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.IndexBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.adapter.NotiAdapter;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.common.OrderType;
import com.zonghong.cuntao.databinding.FragmentIndexBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.BannerImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class IndexFragment extends BaseFragment<FragmentIndexBinding> {


    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    private NotiAdapter notiAdapter;

    private Handler notiHander;

    private int notiIndex = 0;


    public static IndexFragment newInstance() {
        Bundle args = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    public void initUI() {
        binding.includeToolbar.tvTitle.setText("首页");
        binding.includeToolbar.vBack.setVisibility(View.GONE);
        binding.rvNotilist.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false) {
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
    }

    @Override
    public void initData() {
        binding.banner.setImageLoader(new BannerImageLoader());
        notiHander = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(notiIndex >= notiAdapter.getData().size()){
                    notiIndex = 0;
                }
                notiIndex = notiIndex+1;
                binding.rvNotilist.scrollToPosition(notiIndex  );
                notiHander.sendEmptyMessageDelayed(100,1500);
            }
        };
        fragmentList.add(RankFragment.newInstance(1));
        fragmentList.add(RankFragment.newInstance(2));
        fragmentList.add(RankFragment.newInstance(3));
        String[] titles = {"日榜", "周榜", "月榜"};
        binding.includeRankList.tab.setViewPager(binding.includeRankList.vpContainer, titles, _mActivity, fragmentList);
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
                getInfo();
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
        params.put("data_type",1);
        HttpClient.Builder.getServer().homea(UserService.getInstance().getToken(), "1").observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<IndexBean>() {
            @Override
            public void onSuccess(BaseBean<IndexBean> baseBean) {
                binding.mrv.finishRefresh();
                notiAdapter = new NotiAdapter(baseBean.getData().getNotice());
                binding.rvNotilist.setAdapter(notiAdapter);
                notiHander.sendEmptyMessageDelayed(100,1500);
                binding.banner.setImages(baseBean.getData().getSideShow());
                binding.banner.start();
                binding.tvTebie.setText(baseBean.getData().getExplain());


            }

            @Override
            public void onError(BaseBean<IndexBean> baseBean) {
                LogUtil.e(TAG,"cuole");
            }
        });
    }
}
