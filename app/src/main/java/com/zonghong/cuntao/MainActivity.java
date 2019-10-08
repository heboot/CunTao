package com.zonghong.cuntao;

import android.view.View;

import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityMainBinding;
import com.zonghong.cuntao.fragment.CommunityFragment;
import com.zonghong.cuntao.fragment.IndexFragment;
import com.zonghong.cuntao.fragment.MyFragment;
import com.zonghong.cuntao.fragment.OrderFragment;
import com.zonghong.cuntao.fragment.TransitionFragment;

import me.yokeyword.fragmentation.ISupportFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private IndexFragment indexFragment = IndexFragment.newInstance();

    private TransitionFragment transitionFragment = TransitionFragment.newInstance();

    private CommunityFragment communityFragment = CommunityFragment.newInstance();

    private OrderFragment orderFragment = OrderFragment.newInstance();

    private MyFragment myFragment = MyFragment.newInstance();

    private ISupportFragment currentFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.GONE);
        mDelegate.loadMultipleRootFragment(binding.flytContainer.getId(), 0, indexFragment, transitionFragment, communityFragment, orderFragment,myFragment);
        currentFragment = indexFragment;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        binding.llytIndex.setOnClickListener(view -> {
            mDelegate.showHideFragment(indexFragment, currentFragment);
            currentFragment = indexFragment;
            binding.ivIndex.setSelected(true);
            binding.ivTransition.setSelected(false);
            binding.ivCommunity.setSelected(false);
            binding.ivOrdr.setSelected(false);
            binding.ivMy.setSelected(false);
        });
        binding.llytTransition.setOnClickListener(view -> {
            mDelegate.showHideFragment(transitionFragment, currentFragment);
            currentFragment = transitionFragment;
            binding.ivIndex.setSelected(false);
            binding.ivTransition.setSelected(true);
            binding.ivCommunity.setSelected(false);
            binding.ivOrdr.setSelected(false);
            binding.ivMy.setSelected(false);
        });
        binding.llytCommunity.setOnClickListener(view -> {
            mDelegate.showHideFragment(communityFragment, currentFragment);
            currentFragment = communityFragment;
            binding.ivIndex.setSelected(false);
            binding.ivTransition.setSelected(false);
            binding.ivCommunity.setSelected(true);
            binding.ivOrdr.setSelected(false);
            binding.ivMy.setSelected(false);
        });
        binding.llytOrder.setOnClickListener(view -> {
            mDelegate.showHideFragment(orderFragment, currentFragment);
            currentFragment = orderFragment;
            binding.ivIndex.setSelected(false);
            binding.ivTransition.setSelected(false);
            binding.ivCommunity.setSelected(false);
            binding.ivOrdr.setSelected(true);
            binding.ivMy.setSelected(false);
        });
        binding.llytMy.setOnClickListener(view -> {
            mDelegate.showHideFragment(myFragment, currentFragment);
            currentFragment = myFragment;
            binding.ivIndex.setSelected(false);
            binding.ivTransition.setSelected(false);
            binding.ivCommunity.setSelected(false);
            binding.ivOrdr.setSelected(false);
            binding.ivMy.setSelected(true);
        });
    }
}
