package com.waw.hr.mutils.bean;

import java.io.Serializable;

public class CashLogModel implements Serializable {
    /**
     * money : 31
     * update_time : 2019-10-03 17:10:57
     * status : 提现成功
     * info : 提现-到农业银行(6514)
     */

    private double money;
    private String update_time;
    private String status;
    private String info;
    //提现状态; 0正在审核; 1提现成功;2提现失败
    private int withdrawalStatus;

    public int getWithdrawalStatus() {
        return withdrawalStatus;
    }

    public void setWithdrawalStatus(int withdrawalStatus) {
        this.withdrawalStatus = withdrawalStatus;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
