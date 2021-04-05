package com.huawei.mock.device;

import com.xq.xhttp.http.annotation.HttpApiScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@HttpApiScan("com.huawei.mock.device.http")
public class DeviceMockApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceMockApplication.class, args);
    }

}
