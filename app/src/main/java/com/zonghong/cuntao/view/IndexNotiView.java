package com.zonghong.cuntao.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.waw.hr.mutils.LogUtil;
import com.waw.hr.mutils.bean.IndexBean;
import com.zonghong.cuntao.MAPP;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.databinding.ItemNotiBinding;
import com.zonghong.cuntao.databinding.LayoutIndexNotiBinding;
import com.zonghong.cuntao.utils.NumberUtils;

import java.util.List;

public class IndexNotiView extends LinearLayout {

    private ItemNotiBinding itemNotiBinding1, itemNotiBinding2;

    private Handler handler;
    private boolean isShow = false;
    private int startY1, endY1, startY2, endY2;
    private Runnable runnable;
    private List<IndexBean.NoticeBean> list;
    private int position = 0;
    private int offsetY = MAPP.mapp.getResources().getDimensionPixelOffset(R.dimen.y15);
    private boolean hasPostRunnable = false;


    public IndexNotiView(Context context) {
        super(context);
        init();
    }

    public IndexNotiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndexNotiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public IndexNotiView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutIndexNotiBinding binding = DataBindingUtil.inflate(LayoutInflater.from(MAPP.mapp), R.layout.layout_index_noti, this, true);
        itemNotiBinding1 = binding.includeNoti1;
        itemNotiBinding2 = binding.includeNoti2;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                isShow = !isShow;
                if (position == list.size() - 1) {
                    position = 0;
                }

                if (isShow) {
                    itemNotiBinding1.tvNotiContent.setText(list.get(position++).getText() +"is1");
                    itemNotiBinding1.tvNotiMoney.setText(NumberUtils.formatDouble(list.get(position++).getMoney()));
                    itemNotiBinding2.tvNotiContent.setText(list.get(position).getText()+"is2");
                    itemNotiBinding2.tvNotiMoney.setText(NumberUtils.formatDouble(list.get(position).getMoney()));
                } else {
                    itemNotiBinding2.tvNotiContent.setText(list.get(position++).getText()+"is2");
                    itemNotiBinding2.tvNotiMoney.setText(NumberUtils.formatDouble(list.get(position++).getMoney()));
                    itemNotiBinding1.tvNotiContent.setText(list.get(position).getText()+"is1");
                    itemNotiBinding1.tvNotiMoney.setText(NumberUtils.formatDouble(list.get(position).getMoney()));
                }

                startY1 = isShow ? 0 : offsetY;
                endY1 = isShow ? -offsetY : 0;
                ObjectAnimator.ofFloat(itemNotiBinding1.getRoot(), "translationY", startY1, endY1).setDuration(300).start();

                startY2 = isShow ? offsetY : 0;
                endY2 = isShow ? 0 : -offsetY ;
                ObjectAnimator.ofFloat(itemNotiBinding2.getRoot(), "translationY", startY2, endY2).setDuration(300).start();
                LogUtil.e("滚动测试",isShow + "sy2="+ startY2 + "endy2=" + endY2);
                handler.postDelayed(runnable, 3000);
            }
        };
    }


    public List<IndexBean.NoticeBean> getList() {
        return list;
    }

    public void setList(List<IndexBean.NoticeBean> list) {
        this.list = list;

        //处理最后一条数据切换到第一条数据 太快的问题
        if (list.size() > 1) {
            list.add(list.get(0));
        }
    }

    public void startScroll() {
        itemNotiBinding1.tvNotiContent.setText(list.get(0).getText());
        itemNotiBinding1.tvNotiMoney.setText(NumberUtils.formatDouble(list.get(0).getMoney()));
        if (list.size() > 1) {
            if (!hasPostRunnable) {
                hasPostRunnable = true;
                //处理第一次进入 第一条数据切换第二条 太快的问题
                handler.postDelayed(runnable, 3000);
            }
        } else {
            //只有一条数据不进行滚动
            hasPostRunnable = false;
//            mBannerTV1.setText(list.get(0));
        }
    }

    public void stopScroll() {
        handler.removeCallbacks(runnable);
        hasPostRunnable = false;
    }

}
