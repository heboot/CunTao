package com.zonghong.cuntao.activity;

import android.graphics.Bitmap;
import android.view.View;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.bean.CurlgetBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityTransitionResultBinding;
import com.zonghong.cuntao.utils.ImageUtils;
import com.zonghong.cuntao.view.ShareDialog;

public class TransitionResultActivity extends BaseActivity<ActivityTransitionResultBinding> {

    private CurlgetBean curlgetBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transition_result;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.tvTitle.setText("一键转换");
    }

    @Override
    public void initData() {
        curlgetBean = (CurlgetBean) getIntent().getExtras().get(MKey.DATA);
        ImageUtils.showImage(curlgetBean.getPicture(), binding.ivImg);
        if (StringUtils.isEmpty(curlgetBean.getMax_commission())) {
            binding.tvYongjinbili.setVisibility(View.GONE);
        } else {
            binding.tvYongjinbili.setText("佣金比例: " + curlgetBean.getMax_commission() + "%");
        }

        if (StringUtils.isEmpty(curlgetBean.getMax_commission())) {
            binding.tvYuguyongjin.setVisibility(View.GONE);
        } else {
            binding.tvYuguyongjin.setText("预估佣金: ¥" + curlgetBean.getCompute_commission());
        }

        if (StringUtils.isEmpty(curlgetBean.getMax_commission()) && StringUtils.isEmpty(curlgetBean.getMax_commission())) {
            binding.v2.setVisibility(View.GONE);
        }


        binding.tvMoney.setText(curlgetBean.getPrice());
        binding.tvShop.setText(curlgetBean.getNick());
        binding.tvTitle.setText(curlgetBean.getTitle());
        Bitmap mBitmap = CodeUtils.createImage(curlgetBean.getUrl(), getResources().getDimensionPixelOffset(R.dimen.x82), getResources().getDimensionPixelOffset(R.dimen.y82), null);
        binding.ivQrcode.setImageBitmap(mBitmap);
    }

    @Override
    public void initListener() {
        binding.tvShare.setOnClickListener(view -> {
            String path = ImageUtils.saveBitmap(ImageUtils.getViewBp(binding.clytContainer));
            ShareDialog shareDialog = ShareDialog.newInstance(curlgetBean.getUrl(), path);
            shareDialog.show(getSupportFragmentManager(), "");
        });
    }
}
