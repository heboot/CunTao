package com.zonghong.cuntao.activity.common;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.waw.hr.mutils.MKey;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.adapter.ImagePreviewAdapter;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityImagePreviewBinding;
import com.zonghong.cuntao.fragment.ImagePreviewFragment;

import java.util.ArrayList;
import java.util.List;

public class ImagePreviewActivity extends BaseActivity<ActivityImagePreviewBinding> {

    private List<String> images;

    private List<ImagePreviewFragment> imagePreviewFragments = new ArrayList<>();

    private ImagePreviewAdapter imagePreviewAdapter;

    private int position;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_preview;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("图片");
    }

    @Override
    public void initData() {
        images = (List<String>) getIntent().getExtras().get(MKey.DATA);
        position = (int) getIntent().getExtras().get("position");
        binding.includeToolbar.tvTitle.setText("图片" + (position+1) + "/" + images.size());
        for (String s : images) {
            imagePreviewFragments.add(ImagePreviewFragment.newInstance(s));
        }
        imagePreviewAdapter = new ImagePreviewAdapter(getSupportFragmentManager(), imagePreviewFragments);
        binding.vpContainer.setAdapter(imagePreviewAdapter);
        binding.vpContainer.setCurrentItem(position);
    }

    @Override
    public void initListener() {
        binding.vpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int p) {
                position = p;
                binding.includeToolbar.tvTitle.setText("图片" + (position+1) + "/" + images.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
