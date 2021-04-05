package com.huawei.mock.device.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PropertiesDTO {
    /**
     * property_name : temperature
     * required : true
     * data_type : decimal
     * min : 1
     * max : 100
     * max_length : 100
     * step : 0.1
     * unit : centigrade
     * method : RW
     * description : force
     * default_value : {"color":"red","size":1}
     */

    @JsonProperty("property_name")
    private String propertyName;
    @JsonProperty("required")
    private Boolean required;
    @JsonProperty("data_type")
    private String dataType;
    @JsonProperty("min")
    private String min;
    @JsonProperty("max")
    private String max;
    @JsonProperty("max_length")
    private Integer maxLength;
    @JsonProperty("step")
    private String step;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("method")
    private String method;
    @JsonProperty("description")
    private String description;
    @JsonProperty("enum_list")
    private List<String> enumList;
    @JsonProperty("default_value")
    private String defaultValue;

}