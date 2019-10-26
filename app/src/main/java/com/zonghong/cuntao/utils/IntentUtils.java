package com.zonghong.cuntao.utils;

import android.content.Intent;

import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.bean.CashLogModel;
import com.waw.hr.mutils.bean.CurlgetBean;
import com.zonghong.cuntao.MAPP;
import com.zonghong.cuntao.activity.RankActivity;
import com.zonghong.cuntao.activity.common.ImagePreviewActivity;
import com.zonghong.cuntao.activity.order.OrderDetailActivity;
import com.zonghong.cuntao.activity.user.CashActivity;
import com.zonghong.cuntao.activity.TransitionResultActivity;
import com.zonghong.cuntao.activity.common.TextActivity;
import com.zonghong.cuntao.activity.user.BindAlipayActivity;
import com.zonghong.cuntao.activity.user.CashStatusActivity;
import com.zonghong.cuntao.activity.user.MyBankActivity;

import java.io.Serializable;
import java.util.List;

public class IntentUtils {

    private static Intent intent;

    public static void doIntent(Class cls) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), cls);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2BindAlipayActivity(String name, String account) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), BindAlipayActivity.class);
        intent.putExtra(MKey.NAME, name);
        intent.putExtra(MKey.ACCOUNT, account);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2TransiionResultActivity(CurlgetBean curlgetBean) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), TransitionResultActivity.class);
        intent.putExtra(MKey.DATA, curlgetBean);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2TextActivity(String title, String content) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), TextActivity.class);
        intent.putExtra(MKey.TITLE, title);
        intent.putExtra(MKey.SEARCH_CONTENT, content);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2CashActivity(String money) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), CashActivity.class);
        intent.putExtra(MKey.MONEY, money);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2MyBankActivity(boolean choose) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), MyBankActivity.class);
        intent.putExtra(MKey.TYPE, choose);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2CashStatusActivity(CashLogModel money) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), CashStatusActivity.class);
        intent.putExtra(MKey.DATA, money);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2RankActivity(int type) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), RankActivity.class);
        intent.putExtra(MKey.TYPE, type);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2OrderDetailActivity(String orderId) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), OrderDetailActivity.class);
        intent.putExtra(MKey.DATA, orderId);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2ImagePreviewActivity(List<String> images, int position) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), ImagePreviewActivity.class);
        intent.putExtra(MKey.DATA, (Serializable) images);
        intent.putExtra("position", position);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }
}
