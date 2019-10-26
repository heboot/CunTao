package com.zonghong.cuntao.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.http.HttpClient;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.RankListBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.adapter.RankListAdapter;
import com.zonghong.cuntao.base.BaseFragment;
import com.zonghong.cuntao.databinding.FragmentImagePreviewBinding;
import com.zonghong.cuntao.databinding.FragmentRankListBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.ImageUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImagePreviewFragment extends BaseFragment<FragmentImagePreviewBinding> {


    private String url;

    public static ImagePreviewFragment newInstance(String  url) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MKey.DATA, url);
        ImagePreviewFragment myOrderFragment = new ImagePreviewFragment();
        myOrderFragment.setArguments(bundle);
        return myOrderFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image_preview;
    }

    @Override
    public void initUI() {
    }

    @Override
    public void initData() {
        url = (String) getArguments().get(MKey.DATA);
        ImageUtils.showImage(url,binding.photoView);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

    }


}
