package com.zonghong.cuntao.activity.article;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.waw.hr.mutils.DialogUtils;
import com.zonghong.cuntao.R;
import com.zonghong.cuntao.activity.SettingActivity;
import com.zonghong.cuntao.adapter.PublishContentImageAdapter;
import com.zonghong.cuntao.base.BaseActivity;
import com.zonghong.cuntao.databinding.ActivityPublishContentBinding;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ArticleAddActivity extends BaseActivity<ActivityPublishContentBinding> {

    private PublishContentImageAdapter publishContentImageAdapter;

    private List<String> images = new ArrayList<>();

    private QMUIBottomSheet chooseAvatarSheet;

    private final int REQUEST_CAMERA = 40001, REQUEST_PHOTO = 40002;

    private int selectNum = 9;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_content;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.tvTitle.setText("发表");
        binding.rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void initData() {
        images.add("localadd");
        publishContentImageAdapter = new PublishContentImageAdapter(images,new WeakReference<>(this));
    }

    public void addImage() {
        getSelectNum();
        chooseAvatarSheet = DialogUtils.getAvatarBottomSheet(ArticleAddActivity.this, new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
            @Override
            public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                if (position == 0) {
                    PictureSelector.create(ArticleAddActivity.this)
                            .openCamera(PictureMimeType.ofImage())
                            .maxSelectNum(selectNum).compress(true)
                            .forResult(REQUEST_CAMERA);
                    chooseAvatarSheet.dismiss();
                } else {
                    PictureSelector.create(ArticleAddActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(selectNum)
                            .compress(true)
                            .forResult(REQUEST_PHOTO);
                    chooseAvatarSheet.dismiss();
                }
            }
        });
        chooseAvatarSheet.show();
    }

    @Override
    public void initListener() {

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
            String url = selectList.get(0).getCompressPath();

            if (images.size() == 9) {
                images.set(8, url);
            } else {
                images.add(0, url);
            }

        }
    }

    public void delImage(String item){
        images.remove(item);
        if(images.indexOf("localadd") < 0){
            images.add(images.size(),"localadd");
        }
        publishContentImageAdapter.notifyDataSetChanged();
    }


    public void getSelectNum() {
        int total = 9;
        for (String s : publishContentImageAdapter.getData()) {
            if (!s.equals("localadd")) {
                total = total - 1;
            }
        }
        selectNum = total;
    }
}
