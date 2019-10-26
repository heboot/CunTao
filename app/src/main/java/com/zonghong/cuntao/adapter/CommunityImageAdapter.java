package com.zonghong.cuntao.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.article.ArticleAddActivity;
import com.zonghong.cuntao.databinding.ItemCommunityImageBinding;
import com.zonghong.cuntao.databinding.ItemPublishImgBinding;
import com.zonghong.cuntao.utils.ImageUtils;
import com.zonghong.cuntao.utils.IntentUtils;

import java.lang.ref.WeakReference;
import java.util.List;

public class CommunityImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public CommunityImageAdapter(@Nullable List<String> data) {
        super(R.layout.item_community_image, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ItemCommunityImageBinding binding = DataBindingUtil.bind(helper.itemView);

        ImageUtils.showImage(item, binding.ivImg);

        binding.getRoot().setOnClickListener(view -> {
            IntentUtils.intent2ImagePreviewActivity(getData(),helper.getAdapterPosition());
        });
    }
}
