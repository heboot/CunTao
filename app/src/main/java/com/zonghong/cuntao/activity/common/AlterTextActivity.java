package com.zonghong.cuntao.activity.common;

import android.content.DialogInterface;
import android.view.View;

import com.example.http.HttpClient;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityAlterTextBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AlterTextActivity extends BaseActivity<ActivityAlterTextBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_alter_text;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("修改昵称");
        binding.etText.setText(UserService.getInstance().getUserInfoBean().getNick_name());
        binding.includeToolbar.tvRight.setText("完成");
        binding.includeToolbar.tvRight.setVisibility(View.VISIBLE);
        loadingDialog = DialogUtils.getLoadingDialog(this, "", false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        binding.ivClear.setOnClickListener((v) -> {
            binding.etText.setText("");
        });
        binding.includeToolbar.tvRight.setOnClickListener((v) -> {
//            if (alterTextType == AlterTextType.NICK_NAME) {
//                alterNickname();
//            } else if (alterTextType == AlterTextType.GROUP_MY_NAME) {
//                alterGroupNickname();
//            } else if (alterTextType == AlterTextType.FRIEND_NAME) {
//                alterFriendNickName();
//            }
            alterNickname();
        });
    }

    private void alterNickname() {

        if (StringUtils.isEmpty(binding.etText.getText())) {
            String tip = "";

            tip = "请输入昵称";
            tipDialog = DialogUtils.getFailDialog(this, tip, true);
            tipDialog.show();
            return;
        }


        loadingDialog.show();
        params.put("nick_name", binding.etText.getText().toString());
        HttpClient.Builder.getServer().changeName(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getSuclDialog(AlterTextActivity.this, baseBean.getMsg(), true);
                tipDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        finish();
                    }
                });
                tipDialog.show();
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getFailDialog(AlterTextActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }
}
