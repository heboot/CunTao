package com.zonghong.cuntao.service;

import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.PreferencesUtils;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.bean.UserInfoBean;
import com.waw.hr.mutils.event.UserEvent;
import com.zonghong.cuntao.MAPP;
import com.zonghong.cuntao.activity.loginregister.LoginActivity;
import com.zonghong.cuntao.utils.IntentUtils;

import org.greenrobot.eventbus.EventBus;

public class UserService {

    private String token;

    private String ry_token;

    private UserInfoBean userInfoBean;

    private static UserService userService;

    private String phone;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }



    public void logout() {
        EventBus.getDefault().post(new UserEvent.LOGOUT_EVENT());
//        setUserId("");
        setRy_token("");
        setToken("");
        setUserInfoBean(null);
//        RongIM.getInstance().logout();
        IntentUtils.doIntent(LoginActivity.class);
    }

    public String getToken() {
        if (StringUtils.isEmpty(token)) {
            return PreferencesUtils.getString(MAPP.mapp, MKey.TOKEN);
        }
        return token;
    }

    public String getRy_token() {
        if (StringUtils.isEmpty(ry_token)) {
            return PreferencesUtils.getString(MAPP.mapp, MKey.RY_TOKEN);
        }
        return ry_token;
    }

    public void setRy_token(String ry_token) {
        this.ry_token = ry_token;
        PreferencesUtils.putString(MAPP.mapp, MKey.RY_TOKEN, ry_token);
    }

    public static UserService getUserService() {
        return userService;
    }

    public static void setUserService(UserService userService) {
        UserService.userService = userService;
    }

    public void setToken(String token) {
        this.token = token;
        PreferencesUtils.putString(MAPP.mapp, MKey.TOKEN, token);
    }

    public UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }   public String getPhone() {
        if (StringUtils.isEmpty(phone)) {
            return PreferencesUtils.getString(MAPP.mapp, "phone");
        }
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        PreferencesUtils.putString(MAPP.mapp, "phone", phone);
    }
}
