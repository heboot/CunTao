package com.zonghong.cuntao.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.ToastUtils;
import com.zonghong.cuntao.MAPP;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.databinding.LayoutShareDialogBinding;
import com.zonghong.cuntao.utils.ImageUtils;
import com.zonghong.cuntao.utils.TextUtils;

import java.io.File;

public class ShareDialog extends DialogFragment {

    private LayoutShareDialogBinding binding;

    public static ShareDialog newInstance(String url, String picPath) {
        Bundle args = new Bundle();
        args.putString(MKey.ACCOUNT, url);
        args.putString(MKey.DATA, picPath);
        ShareDialog fragment = new ShareDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_share_dialog, container, false);
        binding = DataBindingUtil.bind(view);

        binding.qlytContainer.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x10)
                , getResources().getDimensionPixelOffset(R.dimen.y10), 0.30f);

        String url = (String) getArguments().get(MKey.ACCOUNT);
        String path = (String) getArguments().get(MKey.DATA);

        ImageUtils.showImage(path, binding.ivImg);

        binding.tvCopyLink.setOnClickListener(v -> {
            TextUtils.copyText(url);
        });

        binding.tvSavePic.setOnClickListener(v -> {
            ImageUtils.saveImageToGallery(new File(path));
            ToastUtils.show(MAPP.mapp, "已保存");
        });

        return binding.getRoot();
    }


}
