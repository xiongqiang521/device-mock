package com.huawei.mock.device.dao;

import com.huawei.mock.device.core.DeviceMockExecutor;
import com.huawei.mock.device.core.MockDeviceInfo;
import com.huaweicloud.sdk.iot.device.client.requests.CommandRsp;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 管理模拟器的Map容器
 */
@Component
public class MockDeviceMap {
    private static final Map<String, DeviceMockExecutor> MAP = new HashMap<>();

    public List<MockDeviceInfo> getAll() {
        return MAP.values().stream()
                .map(DeviceMockExecutor::getStatus)
                .collect(Collectors.toList());
    }

    public MockDeviceInfo start(String deviceId, String secret) {
        return start(deviceId, secret, DeviceMockExecutor.DEFAULT_COMMAND_FUN);
    }

    public MockDeviceInfo start(String deviceId, String secret, Supplier<CommandRsp> commandFun) {
        DeviceMockExecutor deviceMockExecutor = new DeviceMockExecutor(deviceId, secret, commandFun);
        deviceMockExecutor.connect();
        MAP.put(deviceId, deviceMockExecutor);
        return deviceMockExecutor.getStatus();
    }

    public MockDeviceInfo stop(String deviceId) {
        DeviceMockExecutor deviceMockExecutor = MAP.get(deviceId);
        MockDeviceInfo status = deviceMockExecutor.getStatus();
        deviceMockExecutor.offline();
        return status;
    }

    public DeviceMockExecutor getExecutor(String deviceId) {
        return MAP.get(deviceId);
    }


    public MockDeviceInfo getStatus(String deviceId) {
        DeviceMockExecutor deviceMockExecutor = MAP.get(deviceId);
        return deviceMockExecutor.getStatus();
    }
}
