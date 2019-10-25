package com.zonghong.cuntao.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.article.ArticleAddActivity;
import com.zonghong.cuntao.databinding.ItemBankBinding;
import com.zonghong.cuntao.databinding.ItemPublishImgBinding;
import com.zonghong.cuntao.utils.ImageUtils;

import java.lang.ref.WeakReference;
import java.util.List;

public class PublishContentImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private WeakReference<ArticleAddActivity> weakReference;

    public PublishContentImageAdapter(@Nullable List<String> data,WeakReference<ArticleAddActivity> weakReference) {
        super(R.layout.item_publish_img, data);
        this.weakReference = weakReference;
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ItemPublishImgBinding binding = DataBindingUtil.bind(helper.itemView);
        if(item.equals("localadd")){
            binding.vDel.setVisibility(View.GONE);
            ImageUtils.showImage(R.mipmap.icon_add_photo,binding.ivImg);
        }else{
            binding.vDel.setVisibility(View.VISIBLE);
            ImageUtils.showImage(item,binding.ivImg);
        }
        binding.getRoot().setOnClickListener((v)->{
            if(item.equals("localadd")){
                weakReference.get().addImage();
            }
        });
        binding.vDel.setOnClickListener(view->{
            weakReference.get().delImage(item);
        });

    }
}
