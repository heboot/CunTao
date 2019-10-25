package com.zonghong.cuntao.adapter;


import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.ArticleModel;
import com.waw.hr.mutils.bean.BankListBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.user.MyAtricleActivity;
import com.zonghong.cuntao.activity.user.MyBankActivity;
import com.zonghong.cuntao.databinding.ItemBankBinding;
import com.zonghong.cuntao.databinding.ItemMyArticleBinding;
import com.zonghong.cuntao.utils.ImageUtils;

import java.lang.ref.WeakReference;
import java.util.List;

public class MyArticleAdapter extends BaseQuickAdapter<ArticleModel, BaseViewHolder> {


    private WeakReference<MyAtricleActivity> weakReference;

    public MyArticleAdapter(@Nullable List<ArticleModel> data,WeakReference<MyAtricleActivity> weakReference) {
        super(R.layout.item_my_article, data);
        this.weakReference = weakReference;
    }


    @Override
    protected void convert(BaseViewHolder helper, ArticleModel item) {
        ItemMyArticleBinding binding = DataBindingUtil.bind(helper.itemView);

        ImageUtils.showImage(item.getImage().get(0),binding.ivImg);

        binding.tvTitle.setText(item.getA_content());
        binding.tvBrower.setText("浏览量: " + item.getA_browse());
        binding.tvDel.setOnClickListener(view->{
            weakReference.get().showConfirmDialog(item.getA_id()+"");
        });

    }
}
