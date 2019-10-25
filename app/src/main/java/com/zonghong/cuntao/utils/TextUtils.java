package com.zonghong.cuntao.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.waw.hr.mutils.ToastUtils;
import com.zonghong.cuntao.MAPP;


public class TextUtils {

    public static void copyText(String text) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) MAPP.mapp.getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", text);
// 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
        ToastUtils.show(MAPP.mapp, "已复制到粘贴板");
    }

}
