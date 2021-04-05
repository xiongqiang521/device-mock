package com.huawei.mock.device.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProductDetail {

    /**
     * app_id : jeQDJQZltU8iKgFFoW060F5SGZka
     * app_name : testAPP01
     * product_id : 5ba24f5ebbe8f56f5a14f605
     * name : Thermometer
     * device_type : Thermometer
     * protocol_type : MQTT
     * data_format : json
     * manufacturer_name : ABC
     * industry : smartCity
     * description : this is a thermometer produced by Huawei
     * service_capabilities : [{"service_id":"temperature","service_type":"temperature","properties":[{"property_name":"temperature","required":true,"data_type":"decimal","min":"1","max":"100","max_length":100,"step":0.1,"unit":"centigrade","method":"RW","description":"force","default_value":{"color":"red","size":1}}],"commands":[{"command_name":"reboot","paras":[{"para_name":"force","required":false,"data_type":"string","min":"1","max":"100","max_length":100,"step":0.1,"unit":"km/h","description":"force"}],"responses":[{"response_name":"ACK","paras":[{"para_name":"force","required":false,"data_type":"string","min":"1","max":"100","max_length":100,"step":0.1,"unit":"km/h","description":"force"}]}]}],"events":[{"event_type":"reboot","paras":[{"para_name":"force","required":false,"data_type":"string","min":"1","max":"100","max_length":100,"step":0.1,"unit":"km/h","description":"force"}]}],"description":"temperature","option":"Mandatory"}]
     * create_time : 20190303T081011Z
     */

    @JsonProperty("app_id")
    private String appId;
    @JsonProperty("app_name")
    private String appName;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("device_type")
    private String deviceType;
    @JsonProperty("protocol_type")
    private String protocolType;
    @JsonProperty("data_format")
    private String dataFormat;
    @JsonProperty("manufacturer_name")
    private String manufacturerName;
    @JsonProperty("industry")
    private String industry;
    @JsonProperty("description")
    private String description;
    @JsonProperty("service_capabilities")
    private List<ServiceCapabilitiesDTO> serviceCapabilities;
    @JsonProperty("create_time")
    private String createTime;


}
