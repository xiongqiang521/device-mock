package com.huawei.mock.device.controller;

import com.huawei.mock.device.core.MockDeviceInfo;
import com.huawei.mock.device.service.DeviceMockService;
import com.huawei.mock.device.util.WebUtil;
import com.huaweicloud.sdk.iot.device.client.requests.ServiceProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class DeviceMockController {

    @Autowired
    private DeviceMockService deviceMockService;

    @PostMapping("/token")
    public String getToken(String username, String pwd) {
        String token = deviceMockService.token(username, pwd);
        HttpSession session = WebUtil.getSession();
        session.setAttribute("token", token);
        return token;
    }

    /**
     * 获取设备模拟器管理的列表
     *
     * @return
     */
    @GetMapping("/mocks")
    public List<MockDeviceInfo> getList() {
        return deviceMockService.getList();
    }

    /**
     * 设备上线开启模拟器
     *
     * @param deviceId
     * @param secret
     * @return
     */
    @PostMapping("/mocks")
    public MockDeviceInfo connectMock(String deviceId, String secret) {
        return deviceMockService.connectMock(deviceId, secret);
    }

    @DeleteMapping("/mocks")
    public MockDeviceInfo stopMock(String deviceId) {
        return deviceMockService.stopMock(deviceId);
    }

    @GetMapping("/mocks/{deviceId}")
    public Map<String, Object> getMockDetail(@PathVariable String deviceId) {
        return deviceMockService.deviceDetail(deviceId);
    }

    @PostMapping("/mocks/{deviceId}/message")
    public void message(@PathVariable String deviceId, @RequestBody String message) {
        deviceMockService.message(deviceId, message);
    }

    @PostMapping("/mocks/{deviceId}/properties")
    public void reportProperties(@PathVariable String deviceId, @RequestBody List<ServiceProperty> propertyList) {
        deviceMockService.reportProperties(deviceId, propertyList);
    }

    /**
     * 开启自动上报
     *
     * @param deviceId
     */
    @PutMapping("/mocks/{deviceId}/properties")
    public void autoReportProperties(@PathVariable String deviceId) {
        deviceMockService.autoReportProperties(deviceId, 10);
    }

    /**
     * 关闭自动上报
     *
     * @param deviceId
     */
    @DeleteMapping("/mocks/{deviceId}/properties")
    public void stopReportProperties(@PathVariable String deviceId) {
        deviceMockService.stopReportProperties(deviceId);
    }

}
