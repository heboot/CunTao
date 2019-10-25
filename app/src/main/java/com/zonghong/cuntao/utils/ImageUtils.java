package com.zonghong.cuntao.utils;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.waw.hr.mutils.ToastUtils;
import com.zonghong.cuntao.MAPP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {

    public static void showAvatar(String url, ImageView image) {
        Glide.with(image.getContext()).load(url).into(image);
    }

    public static void showImage(int redId, ImageView image) {
        Glide.with(image.getContext()).load(redId).into(image);
    }

    public static void showImage(String redId, ImageView image) {
        Glide.with(image.getContext()).load(redId).into(image);
    }

    public static void showBiaoqing(String redId, ImageView image) {
        Glide.with(image.getContext()).load(redId).into(image);
    }

    public static void showGif(String redId, ImageView image) {
        Glide.with(image.getContext()).load(redId).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(image);

//        ((RequestBuilder)Glide.with(image.getContext()).asGif().diskCacheStrategy(DiskCacheStrategy.RESOURCE)).load(redId).listener(new RequestListener<GifDrawable>() {
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
//                return false;
//            }
//
//            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
//                return false;
//            }
//        }).into(image);
    }

    //保存图片
    public static String saveBitmap(Bitmap mBitmap) {
        if (mBitmap == null) {
            ToastUtils.show(MAPP.mapp,"处理图片失败，请稍后重试");
            return null;
        }
        FileOutputStream fos = null;
        String filePath = SDCardUtils.getRootPathPrivatePic() + System.currentTimeMillis() + ".jpg";
        try {
            File imagePath = new File(filePath);
            if (!imagePath.exists()) {
            }
            fos = new FileOutputStream(imagePath);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {

                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    public static Bitmap getViewBp(View v) {
        if (null == v) {
            return null;
        }
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        if (Build.VERSION.SDK_INT >= 11) {
            v.measure(View.MeasureSpec.makeMeasureSpec(v.getWidth(),
                    View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(
                    v.getHeight(), View.MeasureSpec.EXACTLY));
            v.layout((int) v.getX(), (int) v.getY(),
                    (int) v.getX() + v.getMeasuredWidth(),
                    (int) v.getY() + v.getMeasuredHeight());
        } else {
            v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        }
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache(), 0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());

        v.setDrawingCacheEnabled(false);
        v.destroyDrawingCache();
        return b;
    }
}
