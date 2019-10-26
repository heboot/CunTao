package com.zonghong.cuntao.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.waw.hr.mutils.ToastUtils;
import com.zonghong.cuntao.MAPP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
            ToastUtils.show(MAPP.mapp, "处理图片失败，请稍后重试");
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

    /**
     * 保存图片到图库
     *
     * @param bmp
     */
    public static void saveImageToGallery(File oldFile) {

        String fileName =Environment.getExternalStoragePublicDirectory("cuntao").toString() + "/"+System.currentTimeMillis() + ".jpg";

        File file = new File(fileName);
        if (!file.exists()) {

        }
        try {
            InputStream inStream = new FileInputStream(oldFile);  //读入原文件
            FileOutputStream fos = new FileOutputStream(file);
            int byteread = 0;
            byte[] buffer = new byte[1444];
//               int  length;
            while ((byteread = inStream.read(buffer)) != -1) {
//                   bytesum  +=  byteread;  //字节数  文件大小
//                   System.out.println(bytesum);
                fos.write(buffer, 0, byteread);
            }
            inStream.close();
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //发送广播通知系统图库刷新数据
        Uri uri = Uri.fromFile(file);
        MAPP.mapp.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));


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
