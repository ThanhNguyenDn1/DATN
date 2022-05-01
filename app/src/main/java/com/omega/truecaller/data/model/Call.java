package com.omega.truecaller.data.model;

public class Call {
    private String mNameUser;
    private String mNumberPhone;
    private String CallType;
    private String TimeCall;

    public Call(String mNameUser, String mNumberPhone, String callType, String timeCall) {
        this.mNameUser = mNameUser;
        this.mNumberPhone = mNumberPhone;
        CallType = callType;
        TimeCall = timeCall;
    }

    public String getCallType() {
        return CallType;
    }

    public void setCallType(String callType) {
        CallType = callType;
    }

    public String getTimeCall() {
        return TimeCall;
    }

    public void setTimeCall(String timeCall) {
        TimeCall = timeCall;
    }

    public String getmNameUser() {
        return mNameUser;
    }

    public void setmNameUser(String mNameUser) {
        this.mNameUser = mNameUser;
    }

    public String getmNumberPhone() {
        return mNumberPhone;
    }

    public void setmNumberPhone(String mNumberPhone) {
        this.mNumberPhone = mNumberPhone;
    }
}
