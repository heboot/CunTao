package com.waw.hr.mutils.bean;

public class UserInfoBean {
    /**
     * image : https://zonghongkeji.cn/VillageTao/public/uploads/20191012/69113dd7706e49957ce493942e523a05.gif
     * nick_name : 我是代码编写者
     * invitation_code : 4669905
     * money : 11111
     */

    private String image;
    private String nick_name;
    private int invitation_code;
    private int money;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getInvitation_code() {
        return invitation_code;
    }

    public void setInvitation_code(int invitation_code) {
        this.invitation_code = invitation_code;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
