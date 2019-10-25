package com.waw.hr.mutils.bean;

import java.util.List;

public class IndexBean {
    /**
     * sideShow : [{"image":"https://zonghongkeji.cn/VillageTao/public/uploads/20191023/92b5d6f8d2cd8870dea77f777595bcee.png"},{"image":"https://zonghongkeji.cn/VillageTao/public/uploads/20191014/4f4c665636cce6eb156dfcc5a7ac973f.jpg"},{"image":"https://zonghongkeji.cn/VillageTao/public/uploads/20191022/fbb43a233de46b3dcd48d523e5b12b21.png"},{"image":"https://zonghongkeji.cn/VillageTao/public/uploads/20191022/fbb43a233de46b3dcd48d523e5b12b21.png"}]
     * notice : [{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1},{"text":"测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号","money":1}]
     * commission : [{"money":13,"user_mobile":"137****1073"},{"money":14,"user_mobile":"123****8910"},{"money":15,"user_mobile":"123****8000"},{"money":16,"user_mobile":"137****4511"}]
     * explain :  1.绑定好友服务站二维码(请在底部查看二维码)，注意:所有做单的账号都要绑定，否则会丢单。\r\n\r\n2.使用一个正确的村淘地址作为收货地址，收货人和手机号随意设计费那就好高科技十多年刚开始。\r\n\r\n3.找到下单宝贝，复制宝贝链接，进入本系统选择转链接，然后使用转链接二维码进入手机淘宝左上角\r\n\r\n4.部分商品预估佣金显示为0，可以正常下单，具体佣金第二天查询，备注:受商家调整村淘佣金比例影响，预估佣金仅供参考，具体以实际结果为准。111\r\n
     */

    private String explain;
    private List<SideShowBean> sideShow;
    private List<NoticeBean> notice;
    private List<CommissionBean> commission;

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public List<SideShowBean> getSideShow() {
        return sideShow;
    }

    public void setSideShow(List<SideShowBean> sideShow) {
        this.sideShow = sideShow;
    }

    public List<NoticeBean> getNotice() {
        return notice;
    }

    public void setNotice(List<NoticeBean> notice) {
        this.notice = notice;
    }

    public List<CommissionBean> getCommission() {
        return commission;
    }

    public void setCommission(List<CommissionBean> commission) {
        this.commission = commission;
    }

    public static class SideShowBean {
        /**
         * image : https://zonghongkeji.cn/VillageTao/public/uploads/20191023/92b5d6f8d2cd8870dea77f777595bcee.png
         */

        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public static class NoticeBean {
        /**
         * text : 测**程分享了Dior迪奥 烈艳蓝金唇膏 热卖色号
         * money : 1
         */

        private String text;
        private double money;

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }


    }

    public static class CommissionBean {
        /**
         * money : 13
         * user_mobile : 137****1073
         */

        private double money;
        private String user_mobile;

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }
    }
}
