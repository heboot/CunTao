package com.zonghong.cuntao.utils;

import android.content.Intent;

import com.waw.hr.mutils.MKey;
import com.zonghong.cuntao.MAPP;
import com.zonghong.cuntao.activity.common.AlterTextActivity;
import com.zonghong.cuntao.activity.common.TextActivity;
import com.zonghong.cuntao.activity.user.AlterAlipayActivity;
import com.zonghong.cuntao.activity.user.BindAlipayActivity;

public class IntentUtils {

    private static Intent intent;

    public static void doIntent(Class cls) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), cls);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2BindAlipayActivity(String name,String account){
        intent = new Intent(MAPP.mapp.getCurrentActivity(), BindAlipayActivity.class);
        intent.putExtra(MKey.NAME,name);
        intent.putExtra(MKey.ACCOUNT,account);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2TextActivity(String title,String content){
        intent = new Intent(MAPP.mapp.getCurrentActivity(), TextActivity.class);
        intent.putExtra(MKey.TITLE,title);
        intent.putExtra(MKey.SEARCH_CONTENT,content);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }


}
