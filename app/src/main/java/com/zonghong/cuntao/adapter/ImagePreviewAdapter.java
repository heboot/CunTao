package com.zonghong.cuntao.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zonghong.cuntao.fragment.ImagePreviewFragment;

import java.util.List;

public class ImagePreviewAdapter extends FragmentPagerAdapter {

    private List<ImagePreviewFragment> fragmentList;

    public ImagePreviewAdapter(FragmentManager fm, List<ImagePreviewFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
