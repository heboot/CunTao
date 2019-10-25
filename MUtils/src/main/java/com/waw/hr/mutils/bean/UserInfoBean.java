package com.waw.hr.mutils.bean;

public class UserInfoBean {
    /**
     * image : https://zonghongkeji.cn/VillageTao/public/uploads/20191012/69113dd7706e49957ce493942e523a05.gif
     * nick_name : 我是代码编写者
     * invitation_code : 4669905
     * money : 11111
     *
     *  "currentMonth": 1.4,
     *     "previousMonth": 0.7,
     *     "gender": 1
     */

    private String image;
    private String nick_name;
    private int invitation_code;
    private int money;

    private double currentMonth;

    private double previousMonth;

    private int gender;

    public double getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(double currentMonth) {
        this.currentMonth = currentMonth;
    }

    public double getPreviousMonth() {
        return previousMonth;
    }

    public void setPreviousMonth(double previousMonth) {
        this.previousMonth = previousMonth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

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
