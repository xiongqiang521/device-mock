package com.huawei.mock.device.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CommandsDTO {
    /**
     * command_name : reboot
     * paras : [{"para_name":"force","required":false,"data_type":"string","min":"1","max":"100","max_length":100,"step":0.1,"unit":"km/h","description":"force"}]
     * responses : [{"response_name":"ACK","paras":[{"para_name":"force","required":false,"data_type":"string","min":"1","max":"100","max_length":100,"step":0.1,"unit":"km/h","description":"force"}]}]
     */

    @JsonProperty("command_name")
    private String commandName;
    @JsonProperty("paras")
    private List<ParasDTO> paras;
    @JsonProperty("responses")
    private List<ResponsesDTO> responses;

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

    @NoArgsConstructor
    @Data
    public static class ResponsesDTO {
        /**
         * response_name : ACK
         * paras : [{"para_name":"force","required":false,"data_type":"string","min":"1","max":"100","max_length":100,"step":0.1,"unit":"km/h","description":"force"}]
         */

        @JsonProperty("response_name")
        private String responseName;
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
}