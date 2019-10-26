package com.waw.hr.mutils.event;

import com.waw.hr.mutils.bean.BankListBean;

public class UserEvent {

     public static class CHOOSE_BANK_EVENT{

         private BankListBean bankListBean;

         public BankListBean getBankListBean() {
             return bankListBean;
         }

         public CHOOSE_BANK_EVENT(BankListBean bankListBean) {
             this.bankListBean = bankListBean;
         }
     }

     public static class LOGOUT_EVENT{}

}
