package com.zonghong.cuntao.activity;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.http.HttpClient;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.UserInfoBean;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.common.AlterTextActivity;
import com.zonghong.cuntao.activity.common.TextActivity;
import com.zonghong.cuntao.activity.user.AlterAlipayActivity;
import com.zonghong.cuntao.activity.user.AlterPhoneActivity;
import com.zonghong.cuntao.activity.user.BindAlipayActivity;
import com.zonghong.cuntao.activity.user.MyBankActivity;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivitySettingBinding;
import com.zonghong.cuntao.http.HttpObserver;
import com.zonghong.cuntao.service.UserService;
import com.zonghong.cuntao.utils.ImageUtils;
import com.zonghong.cuntao.utils.IntentUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SettingActivity extends BaseActivity<ActivitySettingBinding> {

    private QMUIBottomSheet sexSheet;

    private QMUIBottomSheet chooseAvatarSheet;

    private final int REQUEST_CAMERA = 40001, REQUEST_PHOTO = 40002;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("设置");
    }

    @Override
    public void initData() {
        loadingDialog = DialogUtils.getLoadingDialog(this, "", false);
        showUserInfo();
    }

    private void showUserInfo(){
        binding.tvNick.setText(UserService.getInstance().getUserInfoBean().getNick_name());
        ImageUtils.showAvatar(UserService.getInstance().getUserInfoBean().getImage(),binding.ivAvatar);
        binding.tvPhone.setText(UserService.getInstance().getPhone());
    }

    @Override
    public void initListener() {
        binding.tvPhoneTitle.setOnClickListener(view->{
            IntentUtils.doIntent(AlterPhoneActivity.class);
        });
        binding.tvLogout.setOnClickListener(view->{
            UserService.getInstance().logout();
            finish();
        });
        binding.tvBankTitle.setOnClickListener(view->{
            IntentUtils.doIntent(MyBankActivity.class);
        });
        binding.tvAlipayTitle.setOnClickListener(view->{
            if(StringUtils.isEmpty(binding.tvAlipay.getText().toString())){
                IntentUtils.doIntent(BindAlipayActivity.class);
            }else{
                IntentUtils.doIntent(AlterAlipayActivity.class);
            }
        });
        binding.tvSexTitle.setOnClickListener((v) -> {
            if (sexSheet == null) {
                sexSheet = DialogUtils.getSexbottomSheet(this, new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        sexSheet.dismiss();
                        if (position == 0) {
                            chooseSex(2);
                        } else {
                            chooseSex(1);
                        }
                    }
                });
            }
            sexSheet.show();
        });
        binding.tvHelpTitle.setOnClickListener(view->{
            IntentUtils.doIntent(TextActivity.class);
        });
        binding.tvName.setOnClickListener((v) -> {
            IntentUtils.doIntent(AlterTextActivity.class);
        });

        binding.tvAvatar.setOnClickListener((v) -> {
            if (chooseAvatarSheet == null) {
                chooseAvatarSheet = DialogUtils.getAvatarBottomSheet(SettingActivity.this, new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        if (position == 0) {
                            PictureSelector.create(SettingActivity.this)
                                    .openCamera(PictureMimeType.ofImage())
                                    .enableCrop(true)
                                    .withAspectRatio(1, 1)
                                    .maxSelectNum(1).compress(true)
                                    .forResult(REQUEST_CAMERA);
                            chooseAvatarSheet.dismiss();
                        } else {
                            PictureSelector.create(SettingActivity.this)
                                    .openGallery(PictureMimeType.ofImage())
                                    .maxSelectNum(1).enableCrop(true)
                                    .compress(true).withAspectRatio(1, 1)
                                    .forResult(REQUEST_PHOTO);
                            chooseAvatarSheet.dismiss();
                        }
                    }
                });
            }
            chooseAvatarSheet.show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        userInfo();
        aliInfo();
    }

    /**
     * 返回数据 为什么是数组？ 太骚了 我也无法解释
     */
    private void aliInfo() {
        HttpClient.Builder.getServer().alipayInfo(UserService.getInstance().getToken()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<Map>>() {
            @Override
            public void onSuccess(BaseBean<List<Map>> baseBean) {
                String ali = String.valueOf(baseBean.getData().get(0).get("alipay"));
                if(!ali.equals("110.0")){
                    binding.tvAlipay.setText(ali);
                }
            }

            @Override
            public void onError(BaseBean<List<Map>> baseBean) {

            }
        });
    }

    private void userInfo() {
        HttpClient.Builder.getServer().profileCenter(UserService.getInstance().getToken()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<UserInfoBean>() {
            @Override
            public void onSuccess(BaseBean<UserInfoBean> baseBean) {
                ImageUtils.showAvatar(baseBean.getData().getImage(), binding.ivAvatar);
                binding.tvName.setText(baseBean.getData().getNick_name());
                UserService.getInstance().setUserInfoBean(baseBean.getData());
                showUserInfo();
            }

            @Override
            public void onError(BaseBean<UserInfoBean> baseBean) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 图片、视频、音频选择结果回调
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            // 例如 LocalMedia 里面返回三种path
            // 1.media.getPath(); 为原图path
            // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
            // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
            // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
            avatarPath = selectList.get(0).getCompressPath();

            uploadAvatar();
//                UploadUtils.uploadImage(selectList.get(0).getCompressPath(), UploadUtils.getIDCardPath(), upCompletionHandler);
        }
    }

    private String avatarPath;

    private void uploadAvatar() {

        loadingDialog.show();

        File file = new File(avatarPath);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);

        HttpClient.Builder.getServer().changeAvatar(UserService.getInstance().getToken(), body).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                dismissLoadingDialog();
                tipDialog = DialogUtils.getSuclDialog(SettingActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
                ImageUtils.showAvatar(String.valueOf(((Map)baseBean.getData()).get("avatar")), binding.ivAvatar);
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                dismissLoadingDialog();
                tipDialog = DialogUtils.getFailDialog(SettingActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }


    private void chooseSex(int sex) {
        loadingDialog.show();
        params.put("gender", sex);
        HttpClient.Builder.getServer().changeGender(UserService.getInstance().getToken(), params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<Object>() {
            @Override
            public void onSuccess(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
//                tipDialog = DialogUtils.getSuclDialog(InfoActivity.this, baseBean.getMsg(), true);
                if (sex == 1) {
                    binding.tvSex.setText("男");
                } else {
                    binding.tvSex.setText("女");
                }
//                tipDialog.show();
            }

            @Override
            public void onError(BaseBean<Object> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getFailDialog(SettingActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }
}
