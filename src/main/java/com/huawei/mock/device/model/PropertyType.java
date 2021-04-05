package com.huawei.mock.device.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.mock.device.util.StringUtil;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public enum PropertyType implements PropertyTypeInterface {
    INT("int") {
        @Override
        public Integer random(PropertiesDTO propertiesDTO) {
            Integer min = StringUtil.parseInt(propertiesDTO.getMin(), 0);
            Integer max = StringUtil.parseInt(propertiesDTO.getMax(), 1);
            return new Random().nextInt(max - min) + min;
        }
    },
    DECIMAL("decimal") {
        @Override
        public Double random(PropertiesDTO propertiesDTO) {
            Integer min = StringUtil.parseInt(propertiesDTO.getMin(), 0);
            Integer max = StringUtil.parseInt(propertiesDTO.getMax(), 1);
            return new Random().nextDouble() * (max - min) + min;
        }
    },
    LONG("long") {
        @Override
        public Long random(PropertiesDTO propertiesDTO) {
            Long min = StringUtil.parseLong(propertiesDTO.getMin(), 0);
            Long max = StringUtil.parseLong(propertiesDTO.getMax(), 1);
            return (int) (new Random().nextFloat()) * (max - min) + min;
        }
    },
    ENUM("enum") {
        @Override
        public String random(PropertiesDTO propertiesDTO) {
            return propertiesDTO.getEnumList().stream()
                    .findAny()
                    .orElse("");
        }
    },
    BOOLEAN("boolean") {
        @Override
        public Boolean random(PropertiesDTO propertiesDTO) {
            return new Random().nextBoolean();
        }
    },
    STRING("string") {
        @Override
        public String random(PropertiesDTO propertiesDTO) {
            Integer maxLength = propertiesDTO.getMaxLength();
            maxLength = maxLength == null ? 0 : maxLength;
            return propertiesDTO.getPropertyName() + maxLength;
        }
    },
    DATE_TIME("DateTime") {
        @Override
        public String random(PropertiesDTO propertiesDTO) {
            return LocalDateTime.now().toString();
        }
    },
    JSON_OBJECT("jsonObject") {
        @Override
        public String random(PropertiesDTO propertiesDTO) {
            return "{}";
        }
    },
    STRING_LIST("string list") {
        // 暂时不做处理
        @Override
        public String random(PropertiesDTO propertiesDTO) {
            return "";
        }
    },

    ;


    private final String type;

    PropertyType(String type) {
        this.type = type;
    }

//    public static PropertyType getPropertyType(PropertiesDTO propertiesDTO) {
//        // int，long，decimal，string，DateTime，jsonObject，enum，boolean，string list
//        return Arrays.stream(PropertyType.values())
//                .filter(type -> type.type.equals(propertiesDTO.getDataType()))
//                .findAny()
//                .orElseThrow(() -> {
//                    throw new RuntimeException("property类型不匹配，" +
//                            propertiesDTO.getPropertyName() +
//                            "不在 [int，long，decimal，string，DateTime，jsonObject，enum，boolean，string list]中。");
//                });
//    }

    public static Object randomValue(PropertiesDTO propertiesDTO) {
        // int，long，decimal，string，DateTime，jsonObject，enum，boolean，string list
        PropertyType propertyType = Arrays.stream(PropertyType.values())
                .filter(type -> type.type.equals(propertiesDTO.getDataType()))
                .findAny()
                .orElseThrow(RuntimeException::new);

        return propertyType.random(propertiesDTO);
    }

}
