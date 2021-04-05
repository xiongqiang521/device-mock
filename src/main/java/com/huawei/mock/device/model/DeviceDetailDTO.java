package com.huawei.mock.device.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class DeviceDetailDTO {

    /**
     * app_id : jeQDJQZltU8iKgFFoW060F5SGZka
     * app_name : testAPP01
     * device_id : d4922d8a-6c8e-4396-852c-164aefa6638f
     * node_id : ABC123456789
     * gateway_id : d4922d8a-6c8e-4396-852c-164aefa6638f
     * device_name : dianadevice
     * node_type : ENDPOINT
     * description : watermeter device
     * fw_version : 1.1.0
     * sw_version : 1.1.0
     * auth_info : {"auth_type":"SECRET","secret":"3b935a250c50dc2c6d481d048cefdc3c","fingerprint":"dc0f1016f495157344ac5f1296335cff725ef22f","secure_access":true,"timeout":300}
     * product_id : b640f4c203b7910fc3cbd446ed437cbd
     * product_name : Thermometer
     * status : INACTIVE
     * create_time : 20190303T081011Z
     * tags : [{"tag_key":"testTagName","tag_value":"testTagValue"}]
     * extension_info : {"aaa":"xxx","bbb":0}
     */

    @JsonProperty("app_id")
    private String appId;
    @JsonProperty("app_name")
    private String appName;
    @JsonProperty("device_id")
    private String deviceId;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("gateway_id")
    private String gatewayId;
    @JsonProperty("device_name")
    private String deviceName;
    @JsonProperty("node_type")
    private String nodeType;
    @JsonProperty("description")
    private String description;
    @JsonProperty("fw_version")
    private String fwVersion;
    @JsonProperty("sw_version")
    private String swVersion;
    @JsonProperty("auth_info")
    private AuthInfoDTO authInfo;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("status")
    private String status;
    @JsonProperty("create_time")
    private String createTime;
    @JsonProperty("tags")
    private List<TagsDTO> tags;
    @JsonProperty("extension_info")
    private ExtensionInfoDTO extensionInfo;

    @NoArgsConstructor
    @Data
    public static class AuthInfoDTO {
        /**
         * auth_type : SECRET
         * secret : 3b935a250c50dc2c6d481d048cefdc3c
         * fingerprint : dc0f1016f495157344ac5f1296335cff725ef22f
         * secure_access : true
         * timeout : 300
         */

        @JsonProperty("auth_type")
        private String authType;
        @JsonProperty("secret")
        private String secret;
        @JsonProperty("fingerprint")
        private String fingerprint;
        @JsonProperty("secure_access")
        private Boolean secureAccess;
        @JsonProperty("timeout")
        private Integer timeout;
    }

    @NoArgsConstructor
    @Data
    public static class ExtensionInfoDTO {
        /**
         * aaa : xxx
         * bbb : 0
         */

        @JsonProperty("aaa")
        private String aaa;
        @JsonProperty("bbb")
        private Integer bbb;
    }

    @NoArgsConstructor
    @Data
    public static class TagsDTO {
        /**
         * tag_key : testTagName
         * tag_value : testTagValue
         */

        @JsonProperty("tag_key")
        private String tagKey;
        @JsonProperty("tag_value")
        private String tagValue;
    }
}
