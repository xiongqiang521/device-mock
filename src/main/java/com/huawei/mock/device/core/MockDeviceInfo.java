package com.huawei.mock.device.core;

import java.util.List;

public class MockDeviceInfo {
    private final String deviceName;
    private final String serverUri;
    private final String deviceId;
    private final String secret;
    private final boolean autoReport;
    private final long autoReportTime;
    private final List<String> commandList;

    public MockDeviceInfo(String deviceName, String serverUri, String deviceId, String secret, boolean autoReport, long autoReportTime, List<String> commandList) {
        this.deviceName = deviceName;
        this.serverUri = serverUri;
        this.deviceId = deviceId;
        this.secret = secret;
        this.autoReport = autoReport;
        this.autoReportTime = autoReportTime;
        this.commandList = commandList;
    }

    @Override
    public String toString() {
        return "MockDeviceInfo{" +
                "deviceName='" + deviceName + '\'' +
                ", serverUri='" + serverUri + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", secret='" + secret + '\'' +
                ", autoReport=" + autoReport +
                ", autoReportTime=" + autoReportTime +
                ", commandList=" + commandList +
                '}';
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getServerUri() {
        return serverUri;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getSecret() {
        return secret;
    }

    public boolean isAutoReport() {
        return autoReport;
    }

    public long getAutoReportTime() {
        return autoReportTime;
    }

    public List<String> getCommandList() {
        return commandList;
    }
}
