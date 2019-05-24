package com.als.entity;

public class LogRecord {
    String logTS;
    String msg;
    String deviceIP;

    public String getLogTS() {
        return logTS;
    }

    public void setLogTS(String logTS) {
        this.logTS = logTS;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDeviceIP() {
        return deviceIP;
    }

    public void setDeviceIP(String deviceIP) {
        this.deviceIP = deviceIP;
    }

    public LogRecord(String logTS, String msg, String deviceIP){
        this.logTS = logTS;
        this.msg = msg;
        this.deviceIP = deviceIP;
    }
}
