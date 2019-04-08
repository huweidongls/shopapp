package com.jingna.shopapp.bean;

/**
 * Created by Administrator on 2019/4/8.
 */

public class WxPayBean {

    /**
     * appId : wx859554b4226ad6fa
     * mchId : 1530281181
     * timeStamp : 1554705868
     * nonceStr : Eq4fYFNnPoDxtxMN
     * prepayId : wx081446239178073430f980b80526737257
     * signType : MD5
     * paySign : 44638657D2A3587C08F5F948DB8ED4FC
     */

    private String appId;
    private String mchId;
    private String timeStamp;
    private String nonceStr;
    private String prepayId;
    private String signType;
    private String paySign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
