package com.zonghong.cuntao.adapter;

import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.ArticleModel;
import com.zonghong.cuntao.MAPP;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.databinding.ItemCommunityBinding;
import com.zonghong.cuntao.databinding.ItemCommunityImageBinding;
import com.zonghong.cuntao.utils.ImageUtils;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;


public class CommunityAdapter extends BaseQuickAdapter<ArticleModel, BaseViewHolder> {


    public CommunityAdapter(@Nullable List<ArticleModel> data) {
        super(R.layout.item_community, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ArticleModel item) {
        ItemCommunityBinding binding = DataBindingUtil.bind(helper.itemView);
        ImageUtils.showImage(item.getU_avatar(),binding.ivAvatar);
        binding.tvName.setText(item.getU_name());
        binding.tvTime.setText(item.getA_time());
        binding.tvContent.setText(item.getA_content());
        binding.tvBrower.setText("浏览量 "+item.getA_browse()+"");

//        for(String s : item.getA_image()){
//            ItemCommunityImageBinding binding1 = DataBindingUtil.inflate(LayoutInflater.from(MAPP.mapp),R.layout.item_community_image,null,false);
//            ImageUtils.showImage(s,binding1.ivImg);
//
//        }

        binding.rvList.setLayoutManager(new GridLayoutManager(MAPP.mapp,3));
        binding.rvList.setAdapter(new CommunityImageAdapter(item.getA_image()));


    }
}
