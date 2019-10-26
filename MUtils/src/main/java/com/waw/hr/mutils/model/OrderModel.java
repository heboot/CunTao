package com.waw.hr.mutils.model;

import java.io.Serializable;

public class OrderModel implements Serializable {
    /**
     * image : https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/870/580/90/0/%252FY0091008%252FY0091008_C009173999_E01_ZHC_CN.jpg
     * title : Dior迪奥 烈艳蓝金唇膏 热卖色号
     * goods_spec : #860 3.5克
     * price : 250.01
     * pay_sum : 99
     * commodity_number : 1
     * time : 2019-10-22 11:13:37
     * order_id : 1
     *
     *
     * {
     *     "address": "河南省焦作市山阳区世纪路2001号河南理工大学新校区",
     *     "consignee": "张君雅",
     *     "mobile": "15012345678",
     *     "title": "Dior迪奥 烈艳蓝金唇膏 热卖色号",
     *     "url": "https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/870/580/90/0/%252FY0091008%252FY0091008_C009173999_E01_ZHC_CN.jpg",
     *     "price": 250.01,
     *     "order_number": "19030870232820",
     *     "pay_sum": 99,
     *     "carriage": 10,
     *     "end_time": "2019-10-25 18:01:23",
     *     "commodity_number": 1
     *   }
     */

    private String address;
    private String consignee;
    private String mobile;
    private String url;
    private String carriage;
    private String end_time;
    private String order_number;


    private String image;
    private String title;
    private String goods_spec;
    private double price;
    private double pay_sum;
    private int commodity_number;
    private String time;
    private String order_id;

    public String getImage() {
        return image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGoods_spec() {
        return goods_spec;
    }

    public void setGoods_spec(String goods_spec) {
        this.goods_spec = goods_spec;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPay_sum() {
        return pay_sum;
    }

    public void setPay_sum(double pay_sum) {
        this.pay_sum = pay_sum;
    }

    public int getCommodity_number() {
        return commodity_number;
    }

    public void setCommodity_number(int commodity_number) {
        this.commodity_number = commodity_number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
