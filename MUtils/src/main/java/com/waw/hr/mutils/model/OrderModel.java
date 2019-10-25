package com.waw.hr.mutils.model;

public class OrderModel {
    /**
     * image : https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/870/580/90/0/%252FY0091008%252FY0091008_C009173999_E01_ZHC_CN.jpg
     * title : Dior迪奥 烈艳蓝金唇膏 热卖色号
     * goods_spec : #860 3.5克
     * price : 250.01
     * pay_sum : 99
     * commodity_number : 1
     * time : 2019-10-22 11:13:37
     * order_id : 1
     */

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
