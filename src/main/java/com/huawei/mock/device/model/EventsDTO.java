package com.huawei.mock.device.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class EventsDTO {
    /**
     * event_type : reboot
     * paras : [{"para_name":"force","required":false,"data_type":"string","min":"1","max":"100","max_length":100,"step":0.1,"unit":"km/h","description":"force"}]
     */

    @JsonProperty("event_type")
    private String eventType;
    @JsonProperty("paras")
    private List<ParasDTO> paras;

    @NoArgsConstructor
    @Data
    public static class ParasDTO {
        /**
         * para_name : force
         * required : false
         * data_type : string
         * min : 1
         * max : 100
         * max_length : 100
         * step : 0.1
         * unit : km/h
         * description : force
         */

        @JsonProperty("para_name")
        private String paraName;
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
        private Double step;
        @JsonProperty("unit")
        private String unit;
        @JsonProperty("description")
        private String description;
    }
}