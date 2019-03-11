package com.jingna.shopapp.bean;

/**
 * Created by Administrator on 2019/3/11.
 */

public class ChoiceMenuSignBean {

    private String sign;
    private int isSelete;

    public ChoiceMenuSignBean(String sign, int isSelete) {
        this.sign = sign;
        this.isSelete = isSelete;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getIsSelete() {
        return isSelete;
    }

    public void setIsSelete(int isSelete) {
        this.isSelete = isSelete;
    }
}
