package com.huawei.mock.device.http;

import com.huawei.mock.device.model.DeviceDetailDTO;
import com.huawei.mock.device.model.ProductDetail;
import com.xq.xhttp.http.annotation.Header;
import com.xq.xhttp.http.annotation.HttpApiHost;
import com.xq.xhttp.http.annotation.HttpApiPath;
import com.xq.xhttp.http.annotation.PathVal;

@HttpApiHost("https://iotda.cn-north-4.myhuaweicloud.com")
public interface HuaweiApi {

    /**
     *
     * @param projectId
     * @param productId
     * @param token
     * @return
     */
    @HttpApiPath("/v5/iot/{project_id}/products/{product_id}")
    public ProductDetail productDetail(
            @PathVal("project_id") String projectId,
            @PathVal("product_id") String productId,
            @Header("X-Auth-Token") String token
    );

    /**
     * 查询设备详情
     * @param projectId
     * @param deviceId
     * @param token
     * @return
     */
    @HttpApiPath("/v5/iot/{project_id}/devices/{device_id}")
    public DeviceDetailDTO deviceDetail(
            @PathVal("project_id") String projectId,
            @PathVal("device_id") String deviceId,
            @Header("X-Auth-Token") String token
    );

}
