package com.waw.hr.mutils.bean;

import java.io.Serializable;

public class CurlgetBean implements Serializable {
    /**
     * picture : https://img.alicdn.com/bao/uploaded/i3/1695298965/O1CN01R2npPg2G61HdMdDP6_!!0-item_pic.jpg
     * title : 裤子男士秋季休闲裤冬季修身加厚加绒长裤直筒裤男裤2019新款秋冬
     * nick : 公爵车旗舰店
     * price : 88
     * max_commission : 9.00
     * compute_commission : 7.92
     * url : https://detail.tmall.com/item.htm?id=577686764754
     */

    private String picture;
    private String title;
    private String nick;
    private String price;
    private String max_commission;
    private double compute_commission;
    private String url;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMax_commission() {
        return max_commission;
    }

    public void setMax_commission(String max_commission) {
        this.max_commission = max_commission;
    }

    public double getCompute_commission() {
        return compute_commission;
    }

    public void setCompute_commission(double compute_commission) {
        this.compute_commission = compute_commission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
