package com.huawei.mock.device.http;

import com.xq.xhttp.http.annotation.Data;
import com.xq.xhttp.http.annotation.HttpApiHeader;
import com.xq.xhttp.http.annotation.HttpApiHost;
import com.xq.xhttp.http.annotation.HttpApiPath;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@HttpApiHost("https://iam.cn-north-4.myhuaweicloud.com")
public interface HuaweiToken {

    @HttpApiPath(path = "/v3/auth/tokens", method = HttpMethod.POST)
    @HttpApiHeader(key = "Content-Type", value = "application/json")
    public ResponseEntity<String> token(@Data HWUser user);

}
