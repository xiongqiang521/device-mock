package com.huawei.mock.device.service;

import com.huawei.mock.device.core.DeviceMockExecutor;
import com.huawei.mock.device.core.MockDeviceInfo;
import com.huawei.mock.device.dao.MockDeviceMap;
import com.huawei.mock.device.http.HWUser;
import com.huawei.mock.device.http.HuaweiApi;
import com.huawei.mock.device.http.HuaweiToken;
import com.huawei.mock.device.model.*;
import com.huawei.mock.device.util.WebUtil;
import com.huaweicloud.sdk.iot.device.client.requests.ServiceProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeviceMockService {
    private static final String PROJECT_ID = "087ed40a4f0025262fd4c00eacd1f836";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private HuaweiToken huaweiToken;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private HuaweiApi huaweiApi;
    @Autowired
    private MockDeviceMap mockDeviceMap;

    /**
     * 获取华为云token
     *
     * @param username
     * @param pwd
     * @return
     */
    public String token(String username, String pwd) {
        HWUser user = new HWUser();
        HWUser.Auth auth = new HWUser.Auth();
        HWUser.Identity identity = new HWUser.Identity();
        identity.setMethods(new ArrayList<String>() {{
            add("password");
        }});
        HWUser.Password password = new HWUser.Password();
        HWUser.User user1 = new HWUser.User();
        user1.setName(username);
        user1.setPassword(pwd);
        HWUser.Domain domain = new HWUser.Domain();
        domain.setName(username);
        user1.setDomain(domain);
        password.setUser(user1);
        identity.setPassword(password);
        auth.setIdentity(identity);
        user.setAuth(auth);
        ResponseEntity<String> token = huaweiToken.token(user);
        return token.getHeaders().getFirst("X-Subject-Token");
    }

    public List<MockDeviceInfo> getList() {
        return mockDeviceMap.getAll();
    }

    public MockDeviceInfo connectMock(String deviceId, String secret) {
        String token = (String) WebUtil.getSession().getAttribute("token");
        return mockDeviceMap.start(deviceId, secret);
    }

    public Map<String, Object> deviceDetail(String deviceId) {
        String token = (String) WebUtil.getSession().getAttribute("token");
        DeviceDetailDTO deviceDetailDTO = huaweiApi.deviceDetail(PROJECT_ID, deviceId, token);
        String productId = deviceDetailDTO.getProductId();
        ProductDetail productDetail = huaweiApi.productDetail(PROJECT_ID, productId, token);
        Map<String, Object> res = new HashMap<>();
        res.put("device", deviceDetailDTO);
        res.put("product", productDetail);
        res.put("status", this.getStatus(deviceId));
        return res;
    }

    public MockDeviceInfo getStatus(String deviceId) {
        return mockDeviceMap.getStatus(deviceId);
    }

    public MockDeviceInfo stopMock(String deviceId) {
        return mockDeviceMap.stop(deviceId);
    }

    public void message(String deviceId, String message) {
        // 获取模拟器的执行器
        DeviceMockExecutor executor = mockDeviceMap.getExecutor(deviceId);
        // 通过执行器发送消息
        executor.reportMessage(message);
    }

    public void reportProperties(String deviceId, List<ServiceProperty> propertyList) {
        // 获取模拟器的执行器
        DeviceMockExecutor executor = mockDeviceMap.getExecutor(deviceId);
        // 通过执行器发送消息
        executor.reportProperties(propertyList);
    }

    public void stopReportProperties(String deviceId){
        DeviceMockExecutor executor = mockDeviceMap.getExecutor(deviceId);
        executor.stopAutoReport();
    }

    public void autoReportProperties(String deviceId, long autoReportTime) {
        // 获取模拟器的执行器
        DeviceMockExecutor executor = mockDeviceMap.getExecutor(deviceId);
        Map<String, Object> deviceDetail = this.deviceDetail(deviceId);
        ProductDetail product = (ProductDetail) deviceDetail.get("product");
        List<ServiceCapabilitiesDTO> serviceCapabilities = product.getServiceCapabilities();
        List<ServiceProperty> collect = serviceCapabilities.stream().map(cap -> {
            ServiceProperty serviceProperty = new ServiceProperty();
            serviceProperty.setServiceId(cap.getServiceId());
            Map<String, Object> proMap = cap.getProperties().parallelStream()
                    .collect(Collectors.toMap(PropertiesDTO::getPropertyName, PropertyType::randomValue));
            serviceProperty.setProperties(proMap);
            return serviceProperty;
        }).collect(Collectors.toList());
        executor.enableAutoReport(() -> collect, autoReportTime);
    }


}
